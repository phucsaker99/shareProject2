package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Event
import com.example.soccert.databinding.ItemNotificationBinding

class NotificationEventAdapter(
    private val onItemClicked: (Event) -> Unit
) : BaseAdapter<Event, NotificationEventAdapter.MatchEventViewHolder>(Event.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MatchEventViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_notification, parent, false
            ),
            onItemClicked
        )

    class MatchEventViewHolder(
        private val itemNotificationBinding: ItemNotificationBinding,
        onItemClicked: (Event) -> Unit
    ) : BaseViewHolder<Event>(itemNotificationBinding, onItemClicked) {

        override fun onBind(itemData: Event) {
            super.onBind(itemData)
            itemNotificationBinding.event = itemData
        }
    }
}
