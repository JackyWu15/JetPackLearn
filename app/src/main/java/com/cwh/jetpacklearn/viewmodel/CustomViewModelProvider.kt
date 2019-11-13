package com.cwh.jetpacklearn.viewmodel

import android.content.Context
import com.cwh.jetpacklearn.db.RepositoryProvider
import com.cwh.jetpacklearn.db.repository.UserRepository
import com.cwh.jetpacklearn.viewmodel.factory.LoginModelFactory
import com.cwh.jetpacklearn.viewmodel.factory.RegisterModelFactory
import com.cwh.jetpacklearn.viewmodel.factory.ShoeModelFactory

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能: 单例内容提供者
 */
object CustomViewModelProvider{
    fun providerRegisterModel(context: Context): RegisterModelFactory {
        val repository:UserRepository = RepositoryProvider.providerUserRepository(context)
        return RegisterModelFactory(repository)
    }

    fun providerLoginModel(context: Context):LoginModelFactory{
        val repository:UserRepository = RepositoryProvider.providerUserRepository(context)
        return LoginModelFactory(repository,context)
    }

    fun providerShoeModel(context: Context): ShoeModelFactory {
        val shoeRepository = RepositoryProvider.providerShoeRepository(context)
        return ShoeModelFactory(shoeRepository)
    }
}