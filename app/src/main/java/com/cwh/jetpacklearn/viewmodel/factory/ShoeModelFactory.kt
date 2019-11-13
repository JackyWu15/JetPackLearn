package com.cwh.jetpacklearn.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cwh.jetpacklearn.db.repository.ShoeRepository
import com.cwh.jetpacklearn.viewmodel.model.ShoeModel

class ShoeModelFactory(
    private val repository: ShoeRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoeModel(repository) as T
    }
}