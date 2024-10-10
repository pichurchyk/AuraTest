package com.pichurchyk.auratest.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.pichurchyk.auratest.data.database.BootHistoryDBO
import com.pichurchyk.auratest.domian.usecase.GetAllBootsUseCase
import com.pichurchyk.auratest.domian.usecase.SaveBootUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootReceiver : BroadcastReceiver(), KoinComponent {
    private val saveBootUseCase: SaveBootUseCase by inject()
    private val getAllBootsUseCase: GetAllBootsUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.d(LOG_TAG, "Boot completed")

            val timestamp = System.currentTimeMillis()

            CoroutineScope(Dispatchers.IO).launch {
                saveBootUseCase.invoke(BootHistoryDBO(timestamp = timestamp))

                val bootHistory = getAllBootsUseCase.invoke()
                Log.d(LOG_TAG, "Boot History: ${bootHistory.first()}")

                NotificationWorker.scheduleNotificationWorker(context)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "BOOT_RECEIVER"
    }
}