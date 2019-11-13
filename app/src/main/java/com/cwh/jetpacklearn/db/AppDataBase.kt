package com.cwh.jetpacklearn.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.cwh.jetpacklearn.db.dao.ShoeDao
import com.cwh.jetpacklearn.db.dao.UserDao
import com.cwh.jetpacklearn.db.data.FavouriteShoe
import com.cwh.jetpacklearn.db.data.Shoe
import com.cwh.jetpacklearn.db.data.User
import com.cwh.jetpacklearn.worker.ShoeWorker

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 数据库文件
 */

@Database(entities = [User::class, Shoe::class,FavouriteShoe::class],version = 1,exportSchema = false)//指定数据库类型和版本
@TypeConverters(Converters::class)//类型转换
abstract class AppDataBase:RoomDatabase(){
    companion object{
        @Volatile
        private var instance:AppDataBase?=null
        fun getInstance(context:Context):AppDataBase{
            return instance?: synchronized(this){
                instance?:buildDataBase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDataBase(context: Context):AppDataBase{
            return Room
                .databaseBuilder(context,AppDataBase::class.java,"jetPack-database")
                .addCallback(object :RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //读取数据
                        val request = OneTimeWorkRequestBuilder<ShoeWorker>().build()
                        //加入队列
                        WorkManager.getInstance().enqueue(request)
                    }
                }).build()
        }
    }

    abstract fun userDao():UserDao
    abstract fun shoeDao():ShoeDao




}