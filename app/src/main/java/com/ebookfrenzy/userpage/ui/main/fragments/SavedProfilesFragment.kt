package com.ebookfrenzy.userpage.ui.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ebookfrenzy.userpage.MainActivity
import com.ebookfrenzy.userpage.R
import com.ebookfrenzy.userpage.adapters.ProfilePreviewAdapter
import com.ebookfrenzy.userpage.databinding.FragmentSavedProfilesBinding
import com.ebookfrenzy.userpage.ui.main.UserPageViewModel


class SavedProfilesFragment : Fragment() {

    private lateinit var binding: FragmentSavedProfilesBinding
    private lateinit var viewModel: UserPageViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedProfilesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        navController = this.findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        binding.recyclerView.adapter = ProfilePreviewAdapter(ProfilePreviewAdapter.OnClickListener{
            viewModel.startDisplayProfile(it)
            navController.navigate(
                R.id.action_savedProfilesFragment_to_profileFragment
            )
        })
        binding.viewModel = viewModel

    }
}