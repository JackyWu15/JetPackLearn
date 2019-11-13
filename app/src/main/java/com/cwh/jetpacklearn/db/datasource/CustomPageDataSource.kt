package com.cwh.jetpacklearn.db.datasource

import androidx.paging.PageKeyedDataSource
import com.cwh.jetpacklearn.common.Constant
import com.cwh.jetpacklearn.db.data.Shoe
import com.cwh.jetpacklearn.db.repository.ShoeRepository

/**
 * Created by cwh on 2019/11/13 0013.
 * 功能: 根据数据库，自定义DataSource
 */
class CustomPageDataSource (private val shoeRepository: ShoeRepository):PageKeyedDataSource<Int, Shoe>(){

    /**
     * 首次加载调用
     */
    override fun loadInitial(params: LoadInitialParams<Int>,callback: LoadInitialCallback<Int, Shoe>) {
        val startIndex = 0L
        val endIndex = 0L + params.requestedLoadSize
        val shoes = shoeRepository.getPageShoes(startIndex,endIndex )
        //通知adapter数据刷新
        callback.onResult(shoes,null,2)

    }


    /**
     *  分页加载调用
     */
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        val startPage = params.key
        val startIndex = ((startPage-1)*Constant.SINGLE_PAGE_SIZE).toLong()+1
        val endIndex = startIndex + params.requestedLoadSize - 1
        val shoes = shoeRepository.getPageShoes(startIndex,endIndex )

        callback.onResult(shoes, params.key + 1)
    }

    /**
     * 加载前调用
     */
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Shoe>) {
        val endPage = params.key
        val endIndex = ((endPage - 1) * Constant.SINGLE_PAGE_SIZE).toLong() + 1
        var startIndex = endIndex - params.requestedLoadSize
        startIndex = if (startIndex < 0) 0L else startIndex //角标小于0

        val shoes = shoeRepository.getPageShoes(startIndex, endIndex)

        callback.onResult(shoes, params.key + 1)
    }







}