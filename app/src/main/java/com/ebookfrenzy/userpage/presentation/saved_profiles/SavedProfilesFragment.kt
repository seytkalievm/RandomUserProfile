package com.ebookfrenzy.userpage.presentation.saved_profiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ebookfrenzy.userpage.databinding.FragmentSavedProfilesBinding
import com.ebookfrenzy.userpage.presentation.ProfilePreviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedProfilesFragment : Fragment() {

    private lateinit var binding: FragmentSavedProfilesBinding
    private val viewModel: SavedProfilesViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedProfilesBinding.inflate(inflater, container, false)
        navController = this.findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProfilePreviewAdapter(
            ProfilePreviewAdapter.OnClickListener{
                val action = SavedProfilesFragmentDirections
                    .actionSavedProfilesFragmentToProfileFragment(it.phone)

                navController.navigate(action)
            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.profiles.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }
}