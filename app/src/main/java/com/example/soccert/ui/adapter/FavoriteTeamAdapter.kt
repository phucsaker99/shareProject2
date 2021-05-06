package com.example.soccert.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.soccert.R
import com.example.soccert.base.BaseAdapter
import com.example.soccert.base.BaseViewHolder
import com.example.soccert.data.model.Team
import com.example.soccert.databinding.ItemFavoriteTeamBinding

class FavoriteTeamAdapter(
    private val onItemClicked: (Team) -> Unit
) : BaseAdapter<Team, FavoriteTeamAdapter.NewsViewHolder>(Team.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_favorite_team, parent, false
            ),
            onItemClicked
        )

    class NewsViewHolder(
        private val itemFavoriteTeamBinding: ItemFavoriteTeamBinding,
        onItemClicked: (Team) -> Unit
    ) : BaseViewHolder<Team>(itemFavoriteTeamBinding, onItemClicked) {

        override fun onBind(itemData: Team) {
            super.onBind(itemData)
            itemFavoriteTeamBinding.team = itemData
        }
    }
}
