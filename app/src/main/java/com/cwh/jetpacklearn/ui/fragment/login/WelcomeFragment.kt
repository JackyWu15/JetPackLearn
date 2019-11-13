package com.cwh.jetpacklearn.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.cwh.base.utils.SP
import com.cwh.jetpacklearn.R
import com.cwh.jetpacklearn.common.Constant

/**
 * Created by cwh on 2019/11/8 0008.
 * 功能:
 */
class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin = view.findViewById(R.id.btn_login)
        btnRegister = view.findViewById(R.id.btn_register)


        btnLogin.setOnClickListener {
            val navigation = navOptions {
                anim {
                    enter = R.anim.common_slide_in_right
                    exit = R.anim.common_slide_out_left
                    popEnter = R.anim.common_slide_in_left
                    popExit = R.anim.common_slide_out_right
                }
            }

            //获取上次登录账号做回显
            val name = SP.getString(Constant.SP_USER_NAME)
            val bundle = Bundle()
            bundle.putString(Constant.ARGS_NAME, name)
            findNavController().navigate(R.id.login, bundle, navigation)
        }


        btnRegister.setOnClickListener {
            val action = WelcomeFragmentDirections.actionWelcomeToRegister().setEMAIL("123@qq.com")
            findNavController().navigate(action)
        }
    }
}