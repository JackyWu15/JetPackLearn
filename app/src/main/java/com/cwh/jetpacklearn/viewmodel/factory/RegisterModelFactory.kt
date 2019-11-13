package com.cwh.jetpacklearn.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cwh.jetpacklearn.db.repository.UserRepository
import com.cwh.jetpacklearn.viewmodel.model.RegisterModel


class RegisterModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterModel(repository) as T
    }
}