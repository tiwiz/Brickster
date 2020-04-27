package com.rob.legopedia.domain

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

inline fun <reified VM : ViewModel> AppCompatActivity.viewModelFromProvider(crossinline provider: () -> Provider<VM>): Lazy<VM> {
    return viewModels {
        provider().factory()
    }
}

inline fun <reified VM : ViewModel> Provider<VM>.factory(): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T1 : ViewModel> create(aClass: Class<T1>): T1 {
            val viewModel = get()
            return viewModel as T1
        }
    }
}