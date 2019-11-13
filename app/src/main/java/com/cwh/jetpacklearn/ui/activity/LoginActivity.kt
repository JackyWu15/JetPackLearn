package com.cwh.jetpacklearn.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.cwh.jetpacklearn.R
import java.util.*

/**
 * Created by cwh on 2019/11/8 0008.
 * 功能: 登录界面
 */
class LoginActivity : AppCompatActivity() {
    private val KEY_PERMISSIONS_REQUEST_COUNT = "KEY_PERMISSIONS_REQUEST_COUNT"
    private var permissionRequestCount: Int = 0
    private val REQUEST_CODE_PERMISSIONS = 101
    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2
    private val permissions = Arrays.asList(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )

    private fun isPermissionGranted(permission: String) =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initEvent()
        savedInstanceState?.let {
            permissionRequestCount = it.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0)
        }
    }

    private fun initView() {
        setContentView(R.layout.activity_login)
    }

    private fun initEvent() {
        requestPermission()
    }

    private fun requestPermission() {
        if (!isCheckAllPermission()) {
            if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                permissionRequestCount += 1
                ActivityCompat.requestPermissions(
                    this,
                    permissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                Toast.makeText(
                    this,
                    R.string.set_permissions_in_settings,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun isCheckAllPermission(): Boolean {
        var hasPermission = true
        for (permission in permissions) {
            hasPermission = hasPermission and isPermissionGranted(permission)
        }
        return hasPermission;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermission()
        }
    }
}