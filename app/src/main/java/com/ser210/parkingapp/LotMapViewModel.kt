package com.ser210.parkingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ser210.parkingapp.data.SpaceDao


class LotMapViewModel(private  val spaceDao: SpaceDao): ViewModel(){

}
class LotMapViewModelFactory(private val spaceDao: SpaceDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LotMapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LotMapViewModel(spaceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}