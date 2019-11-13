package com.cwh.jetpacklearn.viewmodel.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.cwh.base.common.createPagerList
import com.cwh.jetpacklearn.db.data.Shoe
import com.cwh.jetpacklearn.db.datasource.CustomPageDataSourceFactory
import com.cwh.jetpacklearn.db.repository.ShoeRepository

/**
 * Created by cwh on 2019/11/13 0013.
 * 功能:
 */
class ShoeModel constructor(shoeRepository: ShoeRepository) : ViewModel() {

    companion object {
        const val ALL = "All"
        const val NIKE = "Nike"
        const val ADIDAS = "Adidas"
        const val OTHER = "Other"
    }

    private val brand = MutableLiveData<String>().apply {
        value = ALL
    }

    //根据类型观察数据变化，返回List数据
    val shoesList: LiveData<PagedList<Shoe>> = brand.switchMap {
        // Room数据库查询，只要知道返回的是LiveData<List<Shoe>>即可
        if (it == ALL) {
            LivePagedListBuilder<Int,Shoe>(
                //DataSourceFactory
                CustomPageDataSourceFactory(shoeRepository),
                //加载配置
            PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)//预加载数量
                .setPageSize(10)//一页数量
                .setPrefetchDistance(2)//距离最后还有2个时加载
                .setEnablePlaceholders(false)//item为空是否用Placeholders显示
                .build()
            ).build()
        } else {
            val  array:Array<String> =
                when(it){
                NIKE-> arrayOf("Nike","Air jordan")
                ADIDAS -> arrayOf("Adidas")
                else -> arrayOf("Converse", "UA", "ANTA" )
            }

            shoeRepository.getShoesByBrand(array).createPagerList(10, 10)
        }
    }


    fun setBrand(brand:String){
        this.brand.value = brand

    }

}