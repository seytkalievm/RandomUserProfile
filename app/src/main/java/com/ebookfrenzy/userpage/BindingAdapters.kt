package com.ebookfrenzy.userpage

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ebookfrenzy.userpage.adapters.ProfilePreviewAdapter
import com.ebookfrenzy.userpage.entities.ProfileEntity


@BindingAdapter("imageUrl")
fun ImageView.bindImage(imageUrl: String?){
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imageUri)
            .into(this)
    }
}

@BindingAdapter("profiles")
fun RecyclerView.bindProfilesList(profiles: List<ProfileEntity>?){
    val adapter = this.adapter as ProfilePreviewAdapter
    adapter.submitList(profiles)
}