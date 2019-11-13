package com.cwh.jetpacklearn.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cwh.jetpacklearn.databinding.ShoeRecyclerItemBinding
import com.cwh.jetpacklearn.db.data.Shoe
import kotlinx.android.synthetic.main.shoe_recycler_item.view.*

/**
 * Created by cwh on 2019/11/12 0012.
 * 功能:
 */
class ShoeAdapter constructor(val context: Context):PagedListAdapter<Shoe,ShoeAdapter.ViewHolder>(ShoeDiffCallBack()){
    /**
     * 创建ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ShoeRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //获取数据
        val shoe = getItem(position)
        //ViewHolder设置数据和监听
        holder.apply {
            val listener = onCreateListener(shoe!!.id)
            bind(listener,shoe)
            itemView.tag = shoe
            Glide.with(context).load(shoe.imageUrl).into(itemView.iv_image)
        }
    }

    /**
     *  创建监听对象
     */
    private fun onCreateListener(id:Long): View.OnClickListener{
        return View.OnClickListener {
//            val intent = Intent(context,DetailActivity::class.java)
//            intent.putExtra(Constant.DETAIL_SHOE_ID,id)
//            context.startActivity(intent)
        }
    }


    class ViewHolder(private val binding:ShoeRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(listener: View.OnClickListener,item: Shoe){

            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }
    }
}