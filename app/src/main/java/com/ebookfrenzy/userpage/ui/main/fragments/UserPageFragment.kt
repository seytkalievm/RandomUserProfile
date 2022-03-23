package com.ebookfrenzy.userpage.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebookfrenzy.userpage.MainActivity
import com.ebookfrenzy.userpage.databinding.RandomUserFragmentBinding


class UserPageFragment : Fragment() {

    companion object {
        fun newInstance() = UserPageFragment()
    }

    private lateinit var binding: RandomUserFragmentBinding
    private lateinit var viewModel: UserPageViewModel
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RandomUserFragmentBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialog(requireActivity())

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =(activity as MainActivity).viewModel
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.userProfile.viewModel = viewModel

        setOnClickListeners()

        viewModel.loadingStatus.observe(viewLifecycleOwner) {
            when (it) {
                "loading" -> {
                    loadingDialog.showDialog()
                    binding.userProfile.constraintLayout.visibility = View.GONE
                    binding.updateButton.visibility = View.GONE
                    binding.errorTextView.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                }
                "success" -> {
                    binding.userProfile.constraintLayout.visibility = View.VISIBLE
                    binding.updateButton.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                    loadingDialog.cancelDialog()
                } else -> {
                    binding.userProfile.constraintLayout.visibility = View.GONE
                    binding.updateButton.visibility = View.GONE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                    loadingDialog.cancelDialog()
                }
            }
        }
    }


    private fun setOnClickListeners(){
        binding.updateButton.setOnClickListener{
            viewModel.updateProfile()
        }

        binding.userProfile.callButton.setOnClickListener(){
            val callIntent = Intent(Intent.ACTION_DIAL)
            val phoneNumber = viewModel.profile.value?.phone
            callIntent.setData(Uri.parse("tel:$phoneNumber"))
            startActivity(callIntent)
        }

        binding.userProfile.showOnMapButton.setOnClickListener{
            val mapIntent = Intent(Intent.ACTION_VIEW)
            val coords = viewModel.profile.value?.location?.coordinates
            mapIntent.setData(Uri.parse("geo:$coords"))
            startActivity(mapIntent)
        }
        binding.retryButton.setOnClickListener{
            viewModel.updateProfile()
        }
    }


}