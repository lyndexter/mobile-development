package com.lyndexter.androiddevelopment.presentation.user_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lyndexter.androiddevelopment.data.UserRepository
import com.lyndexter.androiddevelopment.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    private var disposable: Disposable? = null

    fun getUsers() {
        disposable = repository.getEntities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val userList = mutableListOf<User>()
                for (user in response.results) {
                    userList.add(User(user.name.first, user.name.last, user.picture.medium))
                }
                return@map userList
            }
            .subscribe({ results ->
                userList.value = results
            }, { error -> errorMessage.value = error.message })
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("cleared")
        disposable?.dispose()
    }
}
