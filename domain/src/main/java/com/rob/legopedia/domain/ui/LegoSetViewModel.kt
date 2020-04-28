package com.rob.legopedia.domain.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rob.legopedia.domain.network.LegoService
import com.rob.legopedia.domain.ui.LCE.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LegoSetViewModel @Inject constructor(private val legoService: LegoService) : ViewModel() {

    private val mutableLegoSets = MutableLiveData<LegoSetLCE>()

    val legoSets: LiveData<LegoSetLCE>
        get() = mutableLegoSets

    fun searchSet(searchKey: String) {
        mutableLegoSets.postValue(Loading)

        viewModelScope.launch {
            try {
                val results = legoService.searchSet(searchKey).toLegoSets()
                mutableLegoSets.postValue(Complete(results))
            } catch(e: Exception) {
                mutableLegoSets.postValue(Error(e))
            }
        }
    }
}