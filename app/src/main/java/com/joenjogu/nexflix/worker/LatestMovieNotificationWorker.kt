package com.joenjogu.nexflix.worker

import android.accounts.NetworkErrorException
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.joenjogu.nexflix.data.MoviesApiService
import com.joenjogu.nexflix.utils.toTrendingDomain

class LatestMovieNotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val apiService: MoviesApiService
) : CoroutineWorker(context, workerParams){

    override suspend fun doWork(): Result {
        try {
            val response = apiService.getTrendingMovies()
            if (response.isSuccessful) {
                val moviesJson = response.body()
                if (moviesJson != null) {
                    val latestMovie = moviesJson.trendingResults[0].toTrendingDomain()

                    // Add notification handling
                    return Result.success()
                }
            } else {
                val error = response.code().toString() + response.errorBody()
                Log.d(TAG, "doWork: $error")
            }
        } catch (exception : NetworkErrorException) {
            Log.d(TAG, "doWork: $exception")
        }
        return Result.failure()
    }

    companion object {
        val TAG: String = LatestMovieNotificationWorker::class.java.name
    }
}