package com.cwh.jetpacklearn.db.repository

import com.cwh.jetpacklearn.db.dao.ShoeDao
import com.cwh.jetpacklearn.db.data.Shoe

/**
 * Created by cwh on 2019/11/13 0013.
 * 功能:
 */
class ShoeRepository private constructor(private val shoeDao: ShoeDao) {
    companion object {
        @Volatile
        private var instance: ShoeRepository? = null

        fun getInstance(shoeDao: ShoeDao): ShoeRepository = instance ?: synchronized(this) {
            instance ?: ShoeRepository(shoeDao).also { instance = it }
        }
    }

    /**
     * 通过id的范围寻找鞋子
     */
    fun getPageShoes(startIndex:Long,endIndex:Long):List<Shoe> = shoeDao.findShoesByIndexRange(startIndex,endIndex)

    /**
     * 通过Id查询一双鞋
     */
    fun getShoeById(id:Long) = shoeDao.findShoeById(id)

    /**
     * 通过品牌查询鞋子
     */
    fun getShoesByBrand(brand:Array<String>) = shoeDao.findShoesByBrand(brand)

    /**
     * 查询用户收藏的鞋
     */
    fun getShoesByUserId(userId:Long) = shoeDao.findShoesByUserId(userId)


    /**
     * 插入鞋子的集合
     */
    fun insertShoes(shoes: List<Shoe>) = shoeDao.insertShoes(shoes)

}