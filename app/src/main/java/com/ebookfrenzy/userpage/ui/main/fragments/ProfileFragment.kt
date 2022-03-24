package com.ebookfrenzy.userpage.ui.main.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ebookfrenzy.userpage.MainActivity
import com.ebookfrenzy.userpage.databinding.FragmentProfileBinding
import com.ebookfrenzy.userpage.ui.main.UserPageViewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        binding.profileLayout.viewModel = viewModel

        setOnClickListeners()
    }


    private fun setOnClickListeners(){
        binding.profileLayout.callButton.setOnClickListener{
            val callIntent = Intent(Intent.ACTION_DIAL)
            val phoneNumber = viewModel.profile.value?.phone
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }

        binding.profileLayout.showOnMapButton.setOnClickListener{
            val mapIntent = Intent(Intent.ACTION_VIEW)
            val coords = viewModel.profile.value?.location?.coordinates
            mapIntent.data = Uri.parse("geo:$coords")
            startActivity(mapIntent)
        }

        binding.backButton.setOnClickListener{
            this.findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.endDisplayProfile()
    }
}