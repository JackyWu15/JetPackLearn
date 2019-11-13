package com.cwh.jetpacklearn.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cwh.jetpacklearn.R
import com.cwh.jetpacklearn.common.Constant
import com.cwh.jetpacklearn.databinding.RegisterFragmentBinding
import com.cwh.jetpacklearn.viewmodel.CustomViewModelProvider
import com.cwh.jetpacklearn.viewmodel.model.RegisterModel

/**
 * Created by cwh on 2019/11/8 0008.
 * 功能: 注册界面
 */
class RegisterFragment:Fragment(){
    private val registerModel: RegisterModel by viewModels {
        CustomViewModelProvider.providerRegisterModel(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:RegisterFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment,container,false)
        onSubscribeUi(binding)
        return binding.root
    }

    private var isEnable:Boolean = false
    private fun initData(binding: RegisterFragmentBinding) {
        binding.model = registerModel
        binding.isEnable = isEnable
        binding.activity= activity
    }

    private fun onSubscribeUi(binding: RegisterFragmentBinding) {
        initData(binding)

        //监听是否为空
        registerModel.pwd.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()&&registerModel.name.value!!.isNotEmpty()&&registerModel.mail.value!!.isNotEmpty()
        })

        registerModel.name.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()&&registerModel.pwd.value!!.isNotEmpty()&&registerModel.mail.value!!.isNotEmpty()
        })

        registerModel.mail.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()&&registerModel.pwd.value!!.isNotEmpty()&&registerModel.name.value!!.isNotEmpty()
        })


        //注册
        binding.btnRegister.setOnClickListener{
            registerModel.register()
            val bundle = Bundle()
            bundle.putString(Constant.ARGS_NAME,registerModel.name.value)
            findNavController().navigate(R.id.login,bundle,null)
        }



    }
}