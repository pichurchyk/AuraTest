package com.pichurchyk.auratest.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pichurchyk.auratest.R
import com.pichurchyk.auratest.data.database.BootDatabase
import com.pichurchyk.auratest.domian.usecase.GetAllBootsUseCase
import com.pichurchyk.auratest.domian.usecase.GetLastTwoBootsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams), KoinComponent {
    private val getAllBootsUseCase: GetLastTwoBootsUseCase by inject()

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        CoroutineScope(Dispatchers.IO).launch {
            val lastBoots = getAllBootsUseCase.invoke()

            val notificationBody = when {
                lastBoots.isEmpty() -> "No boots detected"
                lastBoots.size == 1 -> {
                    "The boot was detected = ${DateUtils.formatDate(lastBoots[0].timestamp)}"
                }

                else -> {
                    val lastBootTime = lastBoots[0].timestamp
                    val penultimateBootTime = lastBoots[1].timestamp
                    val timeDelta = lastBootTime - penultimateBootTime
                    "Last boots time delta = ${DateUtils.formatTimeDelta(timeDelta)}"
                }
            }

            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannelId = NOTIFICATION_CHANNEL_ID

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    notificationChannelId,
                    "Boot Events",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(applicationContext, notificationChannelId)
                .setContentTitle("Boot Event")
                .setContentText(notificationBody)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build()

            notificationManager.notify(1, notification)
        }
    }

    companion object {
        fun scheduleNotificationWorker(context: Context?) {
            val workManager = WorkManager.getInstance(context!!)
            val workRequest = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .addTag(NOTIFICATIONS_TAG)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

            workManager.enqueueUniquePeriodicWork(
                "boot_notification_worker",
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                workRequest
            )
        }

        private const val NOTIFICATION_CHANNEL_ID = "BOOT_NOTIFICATION_CHANNEL"
        private const val NOTIFICATIONS_TAG = "BOOT_NOTIFICATION"
    }
}