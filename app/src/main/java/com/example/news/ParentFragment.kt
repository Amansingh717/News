package com.example.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

open class ParentFragment<VM : ParentViewModel> : Fragment() {
    private lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun getViewModel(): VM {
        return mViewModel
    }

    /**
     * This method sets up view model for the fragment
     */
    fun setupViewModel(viewModel: Class<VM>) {
        mViewModel = ViewModelProvider(this).get(viewModel)
    }
}