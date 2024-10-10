package com.pichurchyk.auratest.domian.usecase

import com.pichurchyk.auratest.data.database.BootDao
import com.pichurchyk.auratest.data.database.BootHistoryDBO

class GetAllBootsUseCase(
    private val dao: BootDao
) {
    fun invoke() = dao.getAllBoots()
}