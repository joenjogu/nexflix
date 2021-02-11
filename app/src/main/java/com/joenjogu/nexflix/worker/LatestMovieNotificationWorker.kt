package com.joenjogu.nexflix.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.joenjogu.nexflix.data.MoviesApiService

class LatestMovieNotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val apiService: MoviesApiService
) : CoroutineWorker(context, workerParams){

    override suspend fun doWork(): Result {

    }
}