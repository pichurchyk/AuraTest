package com.pichurchyk.auratest.domian.usecase

import com.pichurchyk.auratest.data.database.BootDao
import com.pichurchyk.auratest.data.database.BootHistoryDBO

class GetLastTwoBootsUseCase(
    private val dao: BootDao
) {
    suspend fun invoke() = dao.getLastTwoBoots()
}