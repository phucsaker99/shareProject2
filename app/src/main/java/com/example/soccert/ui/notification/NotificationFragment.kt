package com.example.soccert.ui.notification

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.soccert.R
import com.example.soccert.base.BaseFragment
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.FragmentNotificationBinding
import com.example.soccert.ui.adapter.NotificationEventAdapter
import kotlinx.android.synthetic.main.fragment_notification.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationFragment : BaseFragment<FragmentNotificationBinding>() {
    private val notificationEventAdapter = NotificationEventAdapter(this::itemSelectedNotification)

    override val layoutResource get() = R.layout.fragment_notification
    override val viewModel by viewModel<NotificationViewModel>()

    override fun initViews() {
        initToolbar()
    }

    private fun initToolbar() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(toolbarNotification)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            notificationVM = viewModel
            recyclerNotification.adapter = notificationEventAdapter
        }
    }

    override fun initActions() {

    }

    private fun itemSelectedNotification(event: Event) {
        val action =
            NotificationFragmentDirections.actionNotificationFragmentToDetailMatchFragment(event.matchID.toInt())
        findNavController().navigate(action)
    }
}
