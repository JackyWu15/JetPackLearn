package com.cwh.jetpacklearn.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cwh.jetpacklearn.db.RepositoryProvider
import com.cwh.jetpacklearn.db.data.Shoe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

/**
 * 读取本地json数据
 */
class ShoeWorker(context: Context,workerParameters: WorkerParameters): CoroutineWorker(context,workerParameters) {
    private val TAG by lazy {
        ShoeWorker::class.java.simpleName
    }
    override val coroutineContext: CoroutineDispatcher get() = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {
        try {
            //读取json数据
            applicationContext.assets.open("shoes.json").use {

                JsonReader(it.reader()).use{
                    val shoeType = object :TypeToken<List<Shoe>>(){}.type
                    val shoeList: List<Shoe> = Gson().fromJson(it, shoeType)
                    //加入数据库
                    val shoeDao = RepositoryProvider.providerShoeRepository(applicationContext)
                    shoeDao.insertShoes(shoeList)

                    Log.i(TAG, "Error seeding database$shoeList")

                    for(i in 0..2){
                        for(shoe in shoeList){
                            shoe.id += shoeList.size
                        }
                        shoeDao.insertShoes(shoeList)
                    }
                    Result.success()

                }
            }
        } catch (ex: Exception) {
            Result.failure()
        }
    }


}
