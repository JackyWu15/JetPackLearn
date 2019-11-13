package com.cwh.jetpacklearn.db

import android.content.Context
import com.cwh.jetpacklearn.db.repository.ShoeRepository
import com.cwh.jetpacklearn.db.repository.UserRepository

/**
 * Created by cwh on 2019/11/11 0011.
 * 功能:
 */
object RepositoryProvider{
    fun providerUserRepository(context: Context):UserRepository{
        return UserRepository.getInstance(AppDataBase.getInstance(context).userDao())
    }

    fun providerShoeRepository(context: Context): ShoeRepository {
        return ShoeRepository.getInstance(AppDataBase.getInstance(context).shoeDao())
    }
}