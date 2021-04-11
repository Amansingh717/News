package com.example.news.topheadlines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.NewsApplication
import com.example.news.ParentFragment
import com.example.news.R
import com.example.news.databinding.FragmentTopHeadlinesBinding
import com.example.news.extensions.makeDebugToast
import com.example.news.extensions.navigateTo
import com.example.news.room.entities.Article
import com.example.news.topheadlines.adapter.TopHeadlinesAdapter
import com.example.news.topheadlines.vm.TopHeadlinesViewModel
import com.example.news.topheadlines.vm.TopHeadlinesViewModelFactory
import com.example.news.utility.ARG_SELECTED_ARTICLE

class TopHeadlinesFragment : ParentFragment<TopHeadlinesViewModel>() {

    private lateinit var mBinding: FragmentTopHeadlinesBinding
    private lateinit var mTopHeadlinesAdapter: TopHeadlinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::mBinding.isInitialized)
            mBinding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel(
            TopHeadlinesViewModel::class.java,
            TopHeadlinesViewModelFactory((requireActivity().application as NewsApplication).mainRepository)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel().getCachedTopHeadlines()
        initViews()
        setupRecyclerView()
        observeCachedData()
    }

    private fun initViews() {
        mBinding.fabRefreshTopHeadlineIndia.setOnClickListener {
            getViewModel().getTopHeadlines(resources.getString(R.string.country_code_in), resources.getString(R.string.api_key))
            requireContext().makeDebugToast("Refreshing data with selected country INDIA")
        }
        mBinding.fabRefreshTopHeadlineUs.setOnClickListener {
            getViewModel().getTopHeadlines(resources.getString(R.string.country_code_us), resources.getString(R.string.api_key))
            requireContext().makeDebugToast("Refreshing data with selected country UNITED STATES")
        }
    }

    private fun setupRecyclerView() {
        mTopHeadlinesAdapter = TopHeadlinesAdapter() { v: View?, i: Article? -> onRecyclerItemClicked(v, i) }
        with(mBinding.recyclerViewTopHeadlines) {
            adapter = mTopHeadlinesAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeCachedData() {
        getViewModel().topHeadlinesCachedData().observe(viewLifecycleOwner, { data ->
            if (!data.isNullOrEmpty()) {
                mTopHeadlinesAdapter.updateList(data)
            }
        })

        getViewModel().topHeadlinesResponse().observe(viewLifecycleOwner, {
            //do nothing just observe have to be implemented later to show loaders
        })
    }

    private fun onRecyclerItemClicked(v: View?, item: Article?) {
        when (v?.id) {
            R.id.cv_top_headlines_item -> {
                navigateTo(
                    R.id.action_topHeadlinesFragment_to_topHeadlinesItemDetailDialogFragment,
                    Bundle().apply { putParcelable(ARG_SELECTED_ARTICLE, item) }
                )
            }
        }
    }
}