package com.cwh.jetpacklearn.db.repository

import androidx.lifecycle.LiveData
import com.cwh.jetpacklearn.db.dao.UserDao
import com.cwh.jetpacklearn.db.data.User
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能:
 */
class UserRepository private constructor(private val userDao:UserDao){
    private val userIcon:String = "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E8%B0%B7%E6%AD%8C&step_word=&hs=0&pn=21&spn=0&di=154880&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1265359977%2C4232989747&os=123243233%2C326025667&simid=3477415014%2C414109628&adpicid=0&lpn=0&ln=912&fr=&fmq=1573461653524_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180118%2F22271e695f5f48a89795e2b9858f5008.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bf5i7_z%26e3Bv54AzdH3FwAzdH3Fd80nlnl0d_nd9m8c&gsm=&rpstart=0&rpnum=0&islist=&querylist=&force=undefined"
    //单例
    companion object{
        @Volatile
        private var instance:UserRepository?=null
        fun getInstance(userDao: UserDao):UserRepository = instance?: synchronized(this){
                instance?: UserRepository(userDao ).also {//also返回本身，let返回最后一行
                    instance = it
                }
        }
    }


    //登录
    fun login(account:String,pwd:String):LiveData<User?> = userDao.login(account,pwd)

    //注册
    suspend fun register(email:String,account: String,pwd: String):Long{
        return withContext(IO){
            userDao.insertUser(User(account,pwd,email,userIcon))
        }
    }
}