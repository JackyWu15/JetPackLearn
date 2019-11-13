package com.cwh.jetpacklearn.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.cwh.jetpacklearn.db.data.Shoe

/**
 * Created by cwh on 2019/11/12 0012.
 * 功能:
 */
class ShoeDiffCallBack :DiffUtil.ItemCallback<Shoe>(){
    override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem == newItem

    }

    override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
        return oldItem.id == newItem.id

    }

}