package com.pichurchyk.auratest.domian.usecase

import com.pichurchyk.auratest.data.database.BootDao
import com.pichurchyk.auratest.data.database.BootHistoryDBO

class SaveBootUseCase(
    private val dao: BootDao
) {
    suspend fun invoke(bootInfo: BootHistoryDBO) = dao.saveBootInfo(bootInfo)
}