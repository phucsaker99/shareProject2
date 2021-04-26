package com.example.soccert.ui.home.teams

import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.databinding.FragmentHomeBinding
import com.example.soccert.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TeamsFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutResource get() = R.layout.fragment_teams

    override val viewModel by sharedViewModel<HomeViewModel>()

    override fun initViews() {

    }

    override fun initData() {

    }

    override fun initActions() {

    }
}