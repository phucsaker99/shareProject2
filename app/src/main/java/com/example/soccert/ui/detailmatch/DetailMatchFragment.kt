package com.example.soccert.ui.detailmatch

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.data.model.Statistic
import com.example.soccert.databinding.FragmentDetailMatchBinding
import com.example.soccert.ui.adapter.LineupAdapter
import com.example.soccert.ui.adapter.StatisticAdapter
import com.example.soccert.utils.loadImageRectangle
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding>() {
    private val args: DetailMatchFragmentArgs by navArgs()
    private val statisticAdapter = StatisticAdapter(this::itemClickedStatistic)
    private val lineupAdapter = LineupAdapter(this::itemClickedEvent)

    override val layoutResource get() = R.layout.fragment_detail_match
    override val viewModel by viewModel<DetailMatchViewModel>()

    override fun initViews() {
        viewModel.getEvent(args.eventID)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            detailMatchVM = viewModel
            recyclerStatistics.adapter = statisticAdapter
            binding.viewLineups.adapter = lineupAdapter
        }
    }

    override fun initActions() {
        viewModel.event.observe(viewLifecycleOwner, Observer {
            viewModel.getTeams(it)
            lineupAdapter.submitList(listOf(it, it))
        })

        binding.viewLineups.registerOnPageChangeCallback(callbackViewPager)

        binding.imageHomeTeam.setOnClickListener {
            viewModel.teamHome.value?.let {
                val action =
                    DetailMatchFragmentDirections.actionDetailMatchFragmentToDetailTeamFragment(it)
                findNavController().navigate(action)
            }
        }

        binding.imageAwayTeam.setOnClickListener {
            viewModel.teamAway.value?.let {
                val action =
                    DetailMatchFragmentDirections.actionDetailMatchFragmentToDetailTeamFragment(it)
                findNavController().navigate(action)
            }
        }
    }

    private val callbackViewPager = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            binding.imageChooseLogo.apply {
                when (position) {
                    VIEWPAGER_POSITION_0 -> viewModel.event.value?.let { loadImageRectangle(it.teamHomeBadge) }
                    VIEWPAGER_POSITION_1 -> viewModel.event.value?.let { loadImageRectangle(it.teamAwayBadge) }
                }
            }
        }
    }

    private fun itemClickedStatistic(statistic: Statistic) {}

    private fun itemClickedEvent(event: Event) {}

    override fun onStop() {
        super.onStop()
        binding.viewLineups.unregisterOnPageChangeCallback(callbackViewPager)
    }

    companion object {
        const val VIEWPAGER_POSITION_0 = 0
        const val VIEWPAGER_POSITION_1 = 1
    }
}
