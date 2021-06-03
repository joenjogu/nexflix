package com.joenjogu.nexflix.utils

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.palette.graphics.Palette
import com.bosphere.fadingedgelayout.FadingEdgeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.joenjogu.nexflix.R

object GlideUtil {

    fun getScrimPalette(
        fragmentActivity: FragmentActivity,
        imageUrl: String,
        imageView: ImageView,
        fadingEdgeLayout: FadingEdgeLayout,
        collapsingToolbar: CollapsingToolbarLayout
    ) {
        Glide.with(fragmentActivity)
            .load(imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("Glide Util", "onLoadFailed: Scrim Image Loading Failed")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = resource as BitmapDrawable
                    val bitmap = drawable.bitmap

                    Palette.Builder(bitmap).maximumColorCount(32).generate { palette ->
                        val scrimColor =palette?.getVibrantColor(
                            ContextCompat.getColor(fragmentActivity, R.color.design_default_color_on_primary)
                        )
                        scrimColor?.let { color ->
                            fadingEdgeLayout.setBackgroundColor(color)
                            collapsingToolbar.setContentScrimColor(color)
                            fragmentActivity.window.statusBarColor = color
                            Log.d("Glide Util", "onResourceReady: $color")
                        }
                    }

                    return false
                }

            }).into(imageView)

    }
}