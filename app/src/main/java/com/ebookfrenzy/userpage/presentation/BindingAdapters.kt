package com.ebookfrenzy.userpage.presentation

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebookfrenzy.userpage.domain.models.Profile

private const val TAG = "BindingAdapter"
@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?){
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imageUri)
            .into(this)
    }
}

@BindingAdapter("profiles")
fun RecyclerView.bindProfilesList(profiles: List<Profile>?){
    val adapter = this.adapter as ProfilePreviewAdapter
    adapter.submitList(profiles)
}