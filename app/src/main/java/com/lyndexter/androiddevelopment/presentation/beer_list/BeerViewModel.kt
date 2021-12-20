package com.lyndexter.androiddevelopment.presentation.beer_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lyndexter.androiddevelopment.data.entities.Response
import com.lyndexter.androiddevelopment.domain.Beer
import com.lyndexter.androiddevelopment.domain.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class BeerViewModel(private val repository: Repository<Response>) : ViewModel() {

    val beerList = MutableLiveData<List<Beer>>()
    val errorMessage = MutableLiveData<String>()
    val beerItemPosition = MutableLiveData<Int>()

    private var disposable: Disposable? = null

    fun getBeers() {
        disposable = repository.getEntities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                Timber.d("---- Beer %s", response)
                val beerList = mutableListOf<Beer>()
                for (resultItem in response) {
                    beerList.add(
                        Beer(
                            resultItem.name,
                            resultItem.description,
                            resultItem.image_url
                        )
                    )
                    Timber.d("---- Beer %s", resultItem.name)
                }
                return@map beerList
            }
            .subscribe({ results ->
                beerList.value = results
            }, { error -> errorMessage.value = error.message })
    }

    fun getBeer(): Beer? {
        Timber.i("-------------------- Beer item info - %s", beerItemPosition.value)
        return beerItemPosition.value?.let { beerList.value?.get(it) }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("cleared")
        disposable?.dispose()
    }
}
