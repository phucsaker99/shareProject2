package com.example.soccert.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Team
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoriteTeamViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> get() = _teams

    init {
        getFavoriteTeam()
    }

    private fun getFavoriteTeam() {
        soccerRepository.getTeamsFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _teams.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
