package com.pichurchyk.auratest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pichurchyk.auratest.data.database.BootHistoryDBO
import com.pichurchyk.auratest.domian.usecase.GetAllBootsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getAllBootsUseCase: GetAllBootsUseCase
): ViewModel() {

    private val _bootsHistory: MutableStateFlow<List<BootHistoryDBO>> = MutableStateFlow(emptyList())
    val bootsHistory: StateFlow<List<BootHistoryDBO>> = _bootsHistory.asStateFlow()

    init {
        viewModelScope.launch {
            getAllBootsUseCase.invoke().collect {
                _bootsHistory.value = it
            }
        }
    }

}