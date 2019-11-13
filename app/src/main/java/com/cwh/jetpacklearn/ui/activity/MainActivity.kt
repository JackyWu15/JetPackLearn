package com.cwh.jetpacklearn.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cwh.jetpacklearn.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var mToolbar: Toolbar
    lateinit var mCamera: ImageView
    lateinit var  host: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        bindBNV()
    }

    private fun initView() {
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bnv_view)
        mToolbar = findViewById(R.id.toolbar)
        mCamera = findViewById(R.id.iv_camera)
    }

    /**
     * fragment切换和bottomNavigationView进行绑定
     */
    private fun bindBNV() {
        host = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = host.navController
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            when(destination.id){
                R.id.meFragment->mCamera.visibility = View.VISIBLE
                else->mCamera.visibility = View.GONE
            }
        }
    }
}
