package com.example.pedroscott.features.main.viewmodel

import com.example.pedroscott.features.UserUiModel

sealed interface MainViewSate {
    object Loading : MainViewSate
    data class Success(val items: List<UserUiModel>) : MainViewSate
    object Error : MainViewSate
}

