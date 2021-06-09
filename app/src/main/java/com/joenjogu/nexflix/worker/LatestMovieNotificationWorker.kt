package com.joenjogu.nexflix.worker

import android.accounts.NetworkErrorException
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.data.MoviesApiService
import com.joenjogu.nexflix.models.Movie
import com.joenjogu.nexflix.ui.MainActivity
import com.joenjogu.nexflix.utils.toTrendingDomain
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*
import java.util.concurrent.CountDownLatch

@KoinApiExtension
class LatestMovieNotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams), KoinComponent {

    private val apiService: MoviesApiService by inject()

    companion object {
        val TAG: String = LatestMovieNotificationWorker::class.java.name
        const val NOTIFICATION_CHANNEL_ID = "Latest Movie Notification ID"
    }

    override suspend fun doWork(): Result {
        try {
            val response = apiService.getTrendingMovies()
            if (response.isSuccessful) {
                val moviesJson = response.body()
                if (moviesJson != null) {
                    val latestMovie = moviesJson.trendingResults[0].toTrendingDomain()
                    createNotification(latestMovie)
                    return Result.success()
                }
            } else {
                val error = response.code().toString() + response.errorBody()
                Log.d(TAG, "doWork: $error")
            }
        } catch (exception: NetworkErrorException) {
            Log.d(TAG, "doWork: $exception")
        }
        return Result.failure()
    }

    private fun createNotification(movie: Movie) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Latest Movie Notification"
            val description = "Get notified about the latest trending movies"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, importance)
            channel.description = description

            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val notificationBitmap = getPosterBitmap(movie.posterUrl)

        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_tv_24)
            .setContentTitle("Checkout The Latest Movie")
            .setContentText(movie.title.toUpperCase(Locale.ROOT))
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(notificationBitmap).bigLargeIcon(null))
            .setLargeIcon(notificationBitmap)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(1, builder.build())
    }

    private fun getPosterBitmap(imageUrl: String): Bitmap? {
        var imageBitmap: Bitmap? = null
        val latch = CountDownLatch(1)
        runBlocking {
            Glide.with(context).asBitmap().load(imageUrl).placeholder(R.drawable.loading_placeholder).into(
                object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        latch.countDown()
                        Log.d(TAG, "onResourceReady: ${resource.width}")
                        imageBitmap = resource
                        return
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                }
            )
        }
        latch.await()
        Log.d(TAG, "getPosterBitmap: $imageBitmap")
        return imageBitmap
    }
}
