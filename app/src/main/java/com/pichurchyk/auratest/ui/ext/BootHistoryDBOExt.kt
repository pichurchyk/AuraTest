package com.pichurchyk.auratest.ui.ext

import com.pichurchyk.auratest.data.database.BootHistoryDBO
import com.pichurchyk.auratest.utils.DateUtils

fun BootHistoryDBO.getPrettyDate(): String {
    return DateUtils.formatDate(timestamp)
}