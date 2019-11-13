package com.cwh.jetpacklearn.viewmodel.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cwh.jetpacklearn.db.repository.UserRepository
import kotlinx.coroutines.launch

/**
 * Created by cwh on 2019/11/12 0012.
 * 功能:
 */
class RegisterModel constructor(private val repository: UserRepository):ViewModel(){
    val name = MutableLiveData<String>("")
    val pwd = MutableLiveData<String>("")
    val mail = MutableLiveData<String>("")

    /**
     * 用户名改变回调的函数
     */
    fun onNameChanged(s: CharSequence) {
        name.value = s.toString()
    }

    /**
     * 邮箱改变的时候
     */
    fun onEmailChanged(s: CharSequence) {
        mail.value = s.toString()
    }

    /**
     * 密码改变的回调函数
     */
    fun onPwdChanged(s: CharSequence) {
        pwd.value = s.toString()
    }


    /**
     * 协程向数据库注册
     */
    fun register(){
        viewModelScope.launch {
            repository.register(mail.value!!,name.value!!,pwd.value!!)
        }
    }
}