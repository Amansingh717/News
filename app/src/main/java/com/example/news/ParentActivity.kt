package com.example.news

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class ParentActivity<ActivityVM : ViewModel> : AppCompatActivity() {
    private lateinit var mActivityViewModel: ActivityVM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun getActivityViewModel(): ActivityVM {
        return mActivityViewModel
    }

    /**
     * This method sets up view model for the Activity
     */
    fun setupActivityViewModel(viewModel: Class<ActivityVM>) {
        mActivityViewModel = ViewModelProvider(this).get(viewModel)
    }
}