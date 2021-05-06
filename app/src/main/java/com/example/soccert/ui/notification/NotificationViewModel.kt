package com.example.soccert.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccert.base.RxViewModel
import com.example.soccert.data.model.Event
import com.example.soccert.data.repository.SoccerRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class NotificationViewModel(
    private val soccerRepository: SoccerRepository
) : RxViewModel() {

    private val _notifications = MutableLiveData<List<Event>>()
    val notifications: LiveData<List<Event>> get() = _notifications

    init {
        getNotifications()
    }

    private fun getNotifications() {
        soccerRepository.getEventNotifications()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _notifications.value = it
            }, {
                _error.value = it.message.toString()
            }).addTo(disposables)
    }
}
