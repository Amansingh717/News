package com.example.news.topheadlines.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.news.databinding.FragmentTopHeadlinesItemDetailDialogBinding
import com.example.news.room.entities.Article
import com.example.news.utility.ARG_SELECTED_ARTICLE

class TopHeadlinesItemDetailDialogFragment : DialogFragment() {

    private lateinit var mBinding: FragmentTopHeadlinesItemDetailDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::mBinding.isInitialized)
            mBinding = FragmentTopHeadlinesItemDetailDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val item = arguments?.getParcelable(ARG_SELECTED_ARTICLE) as? Article

        Glide.with(this)
            .load(item?.urlToImage)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mBinding.imageView)
        mBinding.modelItem = item
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = requireActivity().resources.displayMetrics
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        dialog?.window?.setLayout(width, height)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}