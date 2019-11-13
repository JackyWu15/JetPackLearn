package com.cwh.jetpacklearn.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cwh.base.utils.SP
import com.cwh.jetpacklearn.common.Constant
import com.cwh.jetpacklearn.databinding.LoginFragmentBinding
import com.cwh.jetpacklearn.ui.activity.MainActivity
import com.cwh.jetpacklearn.viewmodel.CustomViewModelProvider
import com.cwh.jetpacklearn.viewmodel.model.LoginModel

/**
 * Created by cwh on 2019/11/8 0008.
 * 功能:
 */
class LoginFragment : Fragment() {
    //查询数据库
    private val loginModel: LoginModel by viewModels {
        CustomViewModelProvider.providerLoginModel(requireContext())
    }
    private var isEnable: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //布局和Fragment之间的辅助类
//        val binding: LoginFragmentBinding =
//            DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        val binding: LoginFragmentBinding = LoginFragmentBinding.inflate(inflater,container,false)
        onSubscribeUi(binding)
        return binding.root
    }

    /**
     * 将数据赋值给辅助类，布局里的data便能获取到
     */
    private fun initData(binding: LoginFragmentBinding) {
        binding.model = loginModel
        binding.isEnable = isEnable
        binding.activity= activity
    }

    private fun onSubscribeUi(binding: LoginFragmentBinding) {
        initData(binding)


        //回显上次登录账号
        val name = arguments?.getString(Constant.ARGS_NAME)
        if(!TextUtils.isEmpty(name))
            loginModel.name.value = name!!

        //DataBinging事件点击
        binding.btnLogin.setOnClickListener{
            //查数据库，如果不为空，则登录
            loginModel.login()?.observe(this, Observer {user->
                user?.let {
                    SP.putLong(Constant.SP_USER_ID,it.id)
                    SP.putString(Constant.SP_USER_NAME,it.account)
                    val intent = Intent(context,MainActivity::class.java)
                    context!!.startActivity(intent)
                    Toast.makeText(context,"登录成功",Toast.LENGTH_LONG).show()
                }
            })
        }

        //密码为空不能登录
        loginModel.pwd.observe(viewLifecycleOwner, Observer {
            binding.isEnable = it.isNotEmpty()&&loginModel.name.value!!.isNotEmpty()
        })
    }
}