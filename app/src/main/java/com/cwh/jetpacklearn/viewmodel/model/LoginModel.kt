package com.cwh.jetpacklearn.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cwh.jetpacklearn.db.data.User
import com.cwh.jetpacklearn.db.repository.UserRepository

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 查数据库逻辑在ViewModel中执行
 */

class LoginModel constructor(private val repository: UserRepository) : ViewModel() {
    val name = MutableLiveData<String>("")
    val pwd = MutableLiveData<String>("")

    fun onPwdChanged(s:CharSequence){
        pwd.value = s.toString()
    }

    fun onNameChanged(s:CharSequence){
        name.value = s.toString()
    }


    /**
     * 登录方法
     */
    fun login(): LiveData<User?>? {
        val account = name.value!!
        val pwd = pwd.value!!
        return repository.login(account, pwd)
    }



}
