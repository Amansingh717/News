package com.example.news.extensions

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.news.R

fun Fragment.navigateTo(id: Int, bundle: Bundle? = null) {
    this.findNavController().navigate(id, bundle)
}

fun Fragment.navigateTo(directions: NavDirections) {
    this.findNavController().navigate(directions)
}

fun Activity.navigateTo(id: Int, bundle: Bundle? = null) {
    this.findNavController(R.id.container).navigate(id, bundle)
}

fun Activity.navigateTo(directions: NavDirections) {
    this.findNavController(R.id.container).navigate(directions)
}