package com.pichurchyk.auratest.di

import com.pichurchyk.auratest.data.database.BootDao
import com.pichurchyk.auratest.data.database.BootDatabase
import com.pichurchyk.auratest.domian.usecase.GetAllBootsUseCase
import com.pichurchyk.auratest.domian.usecase.GetLastTwoBootsUseCase
import com.pichurchyk.auratest.domian.usecase.SaveBootUseCase
import com.pichurchyk.auratest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { BootDatabase(get()) }

    single { SaveBootUseCase(dao = provideDao(get())) }
    single { GetAllBootsUseCase(dao = provideDao(get())) }
    single { GetLastTwoBootsUseCase(dao = provideDao(get())) }

    viewModel { MainViewModel(get()) }
}

private fun provideDao(database: BootDatabase): BootDao = database.dao()