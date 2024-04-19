package com.ser210.parkingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ser210.parkingapp.data.SpaceDao

class ParkedViewModel(private val spaceDao: SpaceDao): ViewModel() {

}
class ParkedViewModelFactory(private val spaceDao: SpaceDao): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if(modelClass.isAssignableFrom(ParkedViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ParkedViewModel(spaceDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}