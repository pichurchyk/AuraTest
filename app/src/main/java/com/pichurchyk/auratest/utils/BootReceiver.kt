package com.pichurchyk.auratest.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.pichurchyk.auratest.data.database.BootDatabase
import com.pichurchyk.auratest.data.database.BootHistoryDBO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Log.d(LOG_TAG, "Boot completed")

            val database = BootDatabase.getDatabase(context)
            val bootDao = database.bootDao()

            val timestamp = System.currentTimeMillis()

            CoroutineScope(Dispatchers.IO).launch {
                bootDao.saveBootInfo(BootHistoryDBO(timestamp = timestamp))

                val bootHistory = bootDao.getAllBoots()
                Log.d(LOG_TAG, "Boot History: $bootHistory")

                NotificationWorker.scheduleNotificationWorker(context)
            }
        }
    }

    companion object {
        private const val LOG_TAG = "BOOT_RECEIVER"
    }
}