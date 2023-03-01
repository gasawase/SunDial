package com.sundial.v1001

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundial.v1001.dto.Twilight
import com.sundial.v1001.service.ITwilightService
import com.sundial.v1001.service.TwilightService
import kotlinx.coroutines.launch

class MainViewModel (var twilightService : ITwilightService = TwilightService()) : ViewModel() {
    var twilight : MutableLiveData<List<Twilight>> = MutableLiveData<List<Twilight>>()

    fun fetchData(){
        viewModelScope.launch{
            var innerTwilight = twilightService.fetchData()
            twilight.postValue(innerTwilight)
        }
    }
}