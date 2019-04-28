package pham.honestbee.imagelist.ui.imagelist

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.TextUtils
import pham.honestbee.imagelist.vo.Image

/**
 * Created by Pham on 23/1/2019.
 */
class ImageItemViewModel(val image: Image) : BaseObservable() {
    @Bindable
    fun getThumbnail(): String? {
        return if (!TextUtils.isEmpty(image.thumbnailUrl)) image.thumbnailUrl else ""
    }

    @Bindable
    fun getUrl(): String? {
        return if (!TextUtils.isEmpty(image.url)) image.url else ""
    }
}