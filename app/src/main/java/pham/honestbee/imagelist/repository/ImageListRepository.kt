package pham.honestbee.imagelist.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pham.honestbee.imagelist.api.ImageListService
import pham.honestbee.imagelist.vo.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageListRepository @Inject constructor(val imageListService: ImageListService) {
    fun getImageList(): Observable<List<Image>> {
        return imageListService.getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}