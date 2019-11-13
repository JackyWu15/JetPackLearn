package com.cwh.jetpacklearn.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cwh.jetpacklearn.db.repository.UserRepository
import com.cwh.jetpacklearn.viewmodel.model.LoginModel

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 登录ViewModel工厂
 */

class LoginModelFactory(private val repository: UserRepository,private val context:Context):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginModel(repository) as T
    }
}
