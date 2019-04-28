package pham.honestbee.imagelist.ui.detail

import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import pham.honestbee.imagelist.R
import pham.honestbee.imagelist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    var binding: ActivityDetailBinding? = null

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        if (intent != null) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_loading_ph)
                    .error(R.drawable.no_image)
                    .fitCenter()
            binding?.imageViewAvatar?.let {
                Glide.with(this)
                        .setDefaultRequestOptions(requestOptions)
                        .load(intent.getStringExtra(URL_STRING_EXTRA))
                        .into(it)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val URL_STRING_EXTRA = "URL"
    }
}
