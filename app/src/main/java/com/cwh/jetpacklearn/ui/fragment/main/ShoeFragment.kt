package com.cwh.jetpacklearn.ui.fragment.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cwh.base.utils.UiUtils
import com.cwh.jetpacklearn.databinding.ShoeFragmentBinding
import com.cwh.jetpacklearn.ui.adapter.ShoeAdapter
import com.cwh.jetpacklearn.viewmodel.CustomViewModelProvider
import com.cwh.jetpacklearn.viewmodel.model.ShoeModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Created by cwh on 2019/11/12 0012.
 * 功能:
 */
class ShoeFragment :Fragment(){
    private lateinit var mShoe: FloatingActionButton
    private lateinit var mNike: FloatingActionButton
    private lateinit var mAdi: FloatingActionButton
    private lateinit var mOther: FloatingActionButton
    private lateinit var nikeGroup: Group
    private lateinit var adiGroup: Group
    private lateinit var otherGroup: Group
    private val shoeModel:ShoeModel by viewModels {
        CustomViewModelProvider.providerShoeModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ShoeFragmentBinding = ShoeFragmentBinding.inflate(inflater, container, false)
        //空返回
        context?:return binding.root
        //不为空设置adapter
        onSubscribeUi(binding)
        return binding.root
    }

    private fun onSubscribeUi(binding: ShoeFragmentBinding) {
        val adapter = ShoeAdapter(context!!)
        binding.rlv.adapter = adapter

        shoeModel.shoesList.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                adapter.submitList(it)//设置List
            }
        })

        mShoe = binding.fabShoe
        mNike = binding.fabNike
        mAdi = binding.fabAdidas
        mOther = binding.fabOther
        nikeGroup = binding.gpLike
        adiGroup = binding.gpWrite
        otherGroup = binding.gpTop

        mNike.setOnClickListener{
            shoeModel.setBrand(ShoeModel.NIKE)
            shoeAnimation()
        }

        mAdi.setOnClickListener {
            shoeModel.setBrand(ShoeModel.ADIDAS)
            shoeAnimation()
        }

        mOther.setOnClickListener {
            shoeModel.setBrand(ShoeModel.OTHER)
            shoeAnimation()
        }

        initFAB()
    }

    private fun initFAB() {
        mShoe.setOnClickListener {
            shoeAnimation()
        }
        setViewVisible(false)

    }

    override fun onResume() {
        super.onResume()

        mShoe.post {
            width = mShoe.measuredWidth
        }
        radius = UiUtils.dp2px(context!!, 80f)
    }


    private var animatorSet: AnimatorSet? = null
    private fun shoeAnimation() {
        //播放时不能点击
        if(animatorSet!=null&&animatorSet!!.isRunning)return

        if(nikeGroup.visibility!=View.VISIBLE){
            animatorSet = AnimatorSet()
            val likeAnimator = getValueAnimator(mNike, false, nikeGroup, 0)
            val writeAnimator = getValueAnimator(mAdi, false, adiGroup, 45)
            val topAnimator = getValueAnimator(mOther, false, otherGroup, 90)
            animatorSet!!.playSequentially(likeAnimator, writeAnimator, topAnimator)
            animatorSet!!.start()
        }else{
            animatorSet = AnimatorSet()
            val likeAnimator = getValueAnimator(mNike, true, nikeGroup, 0)
            val writeAnimator = getValueAnimator(mAdi, true, adiGroup, 45)
            val topAnimator = getValueAnimator(mOther, true, otherGroup, 90)
            animatorSet!!.playSequentially(topAnimator, writeAnimator, likeAnimator)
            animatorSet!!.start()
        }

    }


    private var radius: Int = 0
    private var width: Int = 0
    private fun getValueAnimator(
        button: FloatingActionButton,
        reverse: Boolean,
        group: Group,
        angle: Int
    ): ValueAnimator {
        val animator: ValueAnimator
        if (reverse)
            animator = ValueAnimator.ofFloat(1f, 0f)
        else
            animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { animation ->
            val v = animation.animatedValue as Float
            val params = button.layoutParams as ConstraintLayout.LayoutParams
            params.circleRadius = (radius.toFloat() * v).toInt()
            params.circleAngle = 270f + angle * v
            params.width = (width.toFloat() * v).toInt()
            params.height = (width.toFloat() * v).toInt()
            button.layoutParams = params

            if (group == nikeGroup) {
                Log.i(TAG, "cirRadius:${params.circleRadius},angle:${params.circleAngle},width:${params.width}")
            }
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {

                group.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator?) {
                if (group === nikeGroup && reverse) {
                    setViewVisible(false)
                }
            }
            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }


        })

        animator.duration = 300
        animator.interpolator = DecelerateInterpolator() as TimeInterpolator?
        return animator
    }

    private fun setViewVisible(isShow: Boolean) {
        nikeGroup.visibility = if (isShow) View.VISIBLE else View.GONE
        adiGroup.visibility = if (isShow) View.VISIBLE else View.GONE
        otherGroup.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}