package com.ebookfrenzy.userpage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebookfrenzy.userpage.bindImage
import com.ebookfrenzy.userpage.databinding.ProfilePreviewBinding
import com.ebookfrenzy.userpage.entities.ProfileEntity

class ProfilePreviewAdapter(private val onClickListener: OnClickListener): ListAdapter<ProfileEntity, ProfilePreviewAdapter.PreviewViewHolder>(DiffCallback) {
    class PreviewViewHolder(private var binding: ProfilePreviewBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(profile: ProfileEntity){
                binding.fullName.text = profile.name.toString()
                binding.phoneNumber.text = profile.phone
                binding.profilePicture.bindImage(profile.picture.url)
            }

        }

    companion object DiffCallback: DiffUtil.ItemCallback<ProfileEntity>(){
        override fun areItemsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProfileEntity, newItem: ProfileEntity): Boolean {
            val birthDay = oldItem.dob.date == newItem.dob.date
            val name = oldItem.name.toString() == newItem.name.toString()
            val coordinates = oldItem.location.toString() == newItem.location.toString()
            val phone = oldItem.phone == newItem.phone
            val id = oldItem.id == newItem.id
            val picture = oldItem.picture.url == oldItem.picture.url

            return birthDay && name && coordinates && phone && id && picture
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        return PreviewViewHolder(ProfilePreviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        val profile = getItem(position)

        holder.itemView.setOnClickListener{
            onClickListener.onClick(profile)
        }

        holder.bind(profile)
    }

    class OnClickListener(val clickListener: (profile: ProfileEntity) -> Unit){
        fun onClick(profile: ProfileEntity) = clickListener(profile)
    }
}