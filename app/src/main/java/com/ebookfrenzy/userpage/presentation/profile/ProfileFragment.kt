package com.ebookfrenzy.userpage.presentation.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ebookfrenzy.userpage.R
import com.ebookfrenzy.userpage.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        viewModel.profile.observe(viewLifecycleOwner){
            binding.profileLayout.visible = true
            binding.profileLayout.profile = it
        }

        viewModel.deleted.observe(viewLifecycleOwner){
            if (it){
                Toast.makeText(context, "Profile deleted", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        binding.apply {

            profileLayout.callButton.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                if (activity?.let { it1 -> callIntent.resolveActivity(it1.packageManager) } != null){
                    val phoneNumber = viewModel.profile.value?.phone
                    callIntent.data = (Uri.parse("tel:$phoneNumber"))
                    startActivity(callIntent)
                } else{
                    val message = getString(R.string.no_suitable_application)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }

            profileLayout.showOnMapButton.setOnClickListener{
                val coords = viewModel.profile.value?.location?.coordinates.toString()
                val uri = Uri.parse("geo:0,0?q=loc:$coords")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                if (activity?.let { it1 -> intent.resolveActivity(it1.packageManager) } != null){
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(intent)
                } else{
                    val message = getString(R.string.no_suitable_application)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }

            deleteButton.setOnClickListener {
                viewModel.deleteProfile()
            }

        }
    }


    private fun setOnClickListeners(){
        binding.profileLayout.callButton.setOnClickListener{
            val callIntent = Intent(Intent.ACTION_DIAL)
            startActivity(callIntent)
        }

        binding.profileLayout.showOnMapButton.setOnClickListener{
            val mapIntent = Intent(Intent.ACTION_VIEW)
            startActivity(mapIntent)
        }

    }

}