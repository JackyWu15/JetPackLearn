package com.cwh.jetpacklearn.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 用户表
 */
@Entity(tableName = "user")//声明一张用户表
data class User(
    @ColumnInfo(name = "user_account") val account:String, //账号字段
    @ColumnInfo(name = "user_pwd") val pwd:String,//密码
    @ColumnInfo(name = "user_name") val name:String,//用户名
    @ColumnInfo(name="user_url") var headIcon:String//头像
){
    @PrimaryKey(autoGenerate = true)//主键，并自动创建
    @ColumnInfo(name = "id") var id:Long = 0

}