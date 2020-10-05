package com.example.news.main.ui

import android.os.Bundle
import com.example.news.ParentActivity
import com.example.news.databinding.ActivityMainBinding
import com.example.news.main.vm.MainActivityViewModel

class MainActivity : ParentActivity<MainActivityViewModel>() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupActivityViewModel(MainActivityViewModel::class.java)
    }
}