package pham.honestbee.imagelist.api

import io.reactivex.Observable
import pham.honestbee.imagelist.vo.Image
import pham.honestbee.imagelist.vo.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageListService {
    @GET("photos")
    fun getImages(): Observable<List<Image>>
}