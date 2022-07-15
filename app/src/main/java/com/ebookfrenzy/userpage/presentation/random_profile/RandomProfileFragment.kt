package com.ebookfrenzy.userpage.presentation.random_profile


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ebookfrenzy.userpage.R
import com.ebookfrenzy.userpage.databinding.RandomUserFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RandomProfileFragment : Fragment() {

    private lateinit var binding: RandomUserFragmentBinding
    private val viewModel: RandomProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RandomUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var toastShown = true

        binding.apply{
            updateButton.setOnClickListener { viewModel.getProfile() }

            saveButton.setOnClickListener {
                toastShown = false
                viewModel.saveProfile()
            }

            userProfile.callButton.setOnClickListener {
                val callIntent = Intent(Intent.ACTION_DIAL)
                if (activity?.let { it1 -> callIntent.resolveActivity(it1.packageManager) } != null){
                    val phoneNumber = viewModel.state.value?.profile?.phone
                    callIntent.data = (Uri.parse("tel:$phoneNumber"))
                    startActivity(callIntent)
                } else{
                    val message = getString(R.string.no_suitable_application)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }

            userProfile.showOnMapButton.setOnClickListener{
                val coords = viewModel.state.value?.profile?.location?.coordinates.toString()
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

        }

        viewModel.state.observe(viewLifecycleOwner){state ->
            when{
                state.isLoading -> binding.apply {
                    progressBar.visibility = View.VISIBLE
                    userProfile.visible = false
                    updateButton.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    saveButton.visibility = View.GONE
                }
                state.profile != null -> binding.apply {
                    progressBar.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    userProfile.visible = true
                    userProfile.profile = state.profile
                    updateButton.visibility = View.VISIBLE
                    errorTextView.visibility = View.VISIBLE
                    saveButton.visibility = View.VISIBLE
                }
                state.error != null -> binding.apply {
                    errorTextView.text = state.error
                    errorTextView.visibility = View.VISIBLE
                    updateButton.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    userProfile.visible = false
                    errorTextView.visibility = View.GONE
                    saveButton.visibility = View.GONE
                }
            }
        }

        viewModel.saveToDbResult.observe(viewLifecycleOwner){
            val message = getString(it)
            if (!toastShown) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }



}