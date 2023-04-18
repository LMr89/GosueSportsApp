package com.g4.dev.gosuesprortsapp.ui.mainFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.adapter.NewsAdapter
import com.g4.dev.gosuesprortsapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {
lateinit var mainBinding: FragmentMainBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(inflater, container,false)
        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setUpRecycler()
        initObservers()

    }

    override fun onStart() {
        super.onStart()
        loadGameNews()

    }
    private fun setUpRecycler(){
        with(mainBinding.recNews){
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        }
    }
    private fun initObservers(){
        viewModel.newsList.observe(viewLifecycleOwner){
            mainBinding.recNews.adapter = NewsAdapter(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            mainBinding.pgLoadNews.isVisible = it
        }
    }

    private  fun loadGameNews(){
        viewModel.getNews()

    }

}