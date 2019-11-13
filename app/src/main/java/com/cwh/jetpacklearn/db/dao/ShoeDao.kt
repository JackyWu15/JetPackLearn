package com.cwh.jetpacklearn.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.cwh.jetpacklearn.db.data.Shoe

/**
 * Created by cwh on 2019/11/13 0013.
 * 功能:
 */
@Dao
interface ShoeDao {

    /**
     * 查找
     */
    @Query("SELECT * FROM shoe")
    fun getAllShoes(): DataSource.Factory<Int,Shoe>

    @Query("SELECT * FROM shoe WHERE id between :startIndex AND :endIndex ORDER BY id ASC")
    fun findShoesByIndexRange(startIndex: Long, endIndex: Long): List<Shoe>

    @Query("SELECT *FROM shoe WHERE id=:id")
    fun findShoeById(id:Long):LiveData<Shoe>

    @Query("SELECT * FROM shoe WHERE shoe_brand IN (:brand)")
    fun findShoesByBrand(brand: Array<String>): DataSource.Factory<Int, Shoe>

    @Query("SELECT * FROM shoe WHERE id=:userId")
    fun findShoesByUserId(userId: Long): LiveData<List<Shoe>>


    /**
     * 添加
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoes(shoes: List<Shoe>)

    /**
     * 删除
     */
    @Delete
    fun deleteShoe(shoe: Shoe)

    @Delete
    fun deleteShoes(shoes: List<Shoe>)


    /**
     * 更新
     */
    @Update
    fun updateShoe(shoe: Shoe)

    @Update
    fun updateShoes(shoes: List<Shoe>)


}