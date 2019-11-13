package com.cwh.jetpacklearn.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cwh.jetpacklearn.db.data.User

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 数据库Dao类
 */
@Dao//声明为数据库dao
interface UserDao{
    //查
    @Query("SELECT*FROM user WHERE user_account = :account AND user_pwd=:pwd")
    fun login(account:String, pwd:String):LiveData<User?>

    @Query("SELECT*FROM user WHERE id=:id")
    fun findUserById(id:Long):LiveData<User?>

    @Query("SELECT*FROM user")
    fun getAllUsers():LiveData<User?>

    //增
    @Insert
    fun insertUser(user: User):Long

    //删
    @Delete
    fun deleteUser(user: User)

    //改
    @Update
    fun updateUser(user: User)
}