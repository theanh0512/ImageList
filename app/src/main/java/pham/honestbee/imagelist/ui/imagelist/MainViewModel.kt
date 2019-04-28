package pham.honestbee.imagelist.ui.imagelist

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.*
import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import pham.honestbee.imagelist.repository.ImageListRepository
import pham.honestbee.imagelist.ui.detail.DetailActivity
import pham.honestbee.imagelist.ui.detail.DetailActivity.Companion.URL_STRING_EXTRA
import pham.honestbee.imagelist.vo.Image
import javax.inject.Inject

class MainViewModel @Inject constructor(val context: Context, val imageListRepository: ImageListRepository) : ViewModel(), Observable {
    val loading = ObservableBoolean(false)
    val loadSuccess = ObservableBoolean(false)
    val images = ObservableArrayList<Image>()
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }
    private val compositeDisposable = CompositeDisposable()
    private val imageAdapter = ImageAdapter(object : ImageAdapter.ItemClickListener {
        override fun onImageClick(image: Image?) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(URL_STRING_EXTRA, image?.url ?: "")
            context.startActivity(intent)
        }
    })

    init {
        loadImages()
    }

    // we use these here because the databinding lib is still having a timing bug
    // where calling notifyChanged() or notifyPropertyChanged(int) results in no action taking place
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    @Bindable
    fun getAdapter(): ImageAdapter {
        return this.imageAdapter
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun loadImages() {
        loading.set(true)
        compositeDisposable.add(imageListRepository.getImageList()
                .subscribe({ response ->
                    loading.set(false)
                    loadSuccess.set(true)
                    images.clear()
                    images.addAll(response)
                }, { throwable ->
                    throwable.printStackTrace()
                    loading.set(false)
                    loadSuccess.set(false)
                })
                { Log.d("Images", "Completed") })
    }
}
