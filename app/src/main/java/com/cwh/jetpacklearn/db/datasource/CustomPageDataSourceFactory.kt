package com.cwh.jetpacklearn.db.datasource

import androidx.paging.DataSource
import com.cwh.jetpacklearn.db.data.Shoe
import com.cwh.jetpacklearn.db.repository.ShoeRepository

/**
 * 构建CustomPageDataSource的工厂
 */
class CustomPageDataSourceFactory(private val shoeRepository: ShoeRepository):DataSource.Factory<Int, Shoe>() {
    override fun create(): DataSource<Int, Shoe> {
        return CustomPageDataSource(shoeRepository)
    }

}