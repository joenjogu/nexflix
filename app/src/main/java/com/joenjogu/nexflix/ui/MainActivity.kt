package com.joenjogu.nexflix.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.work.*
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.worker.LatestMovieNotificationWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_fragment)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val latestNotificationWorker = PeriodicWorkRequestBuilder<LatestMovieNotificationWorker>(
            600, TimeUnit.SECONDS
        ).setConstraints(constraints).build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "LatestMovieNotificationWorker",
            ExistingPeriodicWorkPolicy.REPLACE,
            latestNotificationWorker
        )
    }
}
