package com.vokrob.onlineshopapp_3.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vokrob.onlineshopapp_3.Domain.CategoryModel
import com.vokrob.onlineshopapp_3.Domain.SliderModel
import com.vokrob.onlineshopapp_3.Repository.MainRepository

class MainViewModel() : ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<SliderModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }
}























