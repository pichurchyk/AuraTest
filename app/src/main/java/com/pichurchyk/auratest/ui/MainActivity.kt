package com.pichurchyk.auratest.ui

import com.pichurchyk.auratest.databinding.ActivityMainBinding
import com.pichurchyk.auratest.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViews() {

    }
}