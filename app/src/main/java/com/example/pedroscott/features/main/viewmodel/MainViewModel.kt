package com.example.pedroscott.features.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entities.DomainResults
import com.example.domain.usecases.GetUsersUseCase
import com.example.pedroscott.features.UiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapper: UiMapper,
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var uiState by mutableStateOf<MainViewSate>(MainViewSate.Loading)
        private set

    fun loadData() {
        viewModelScope.launch(loadDataErrorHandler) {
            uiState = when (val result = getUsersUseCase()) {
                is DomainResults.Error -> MainViewSate.Error
                is DomainResults.Success -> MainViewSate.Success(items = mapper.mapItems(result.value))
            }
        }
    }

    private val loadDataErrorHandler = CoroutineExceptionHandler { _, _ ->
        uiState = MainViewSate.Error
    }
}

