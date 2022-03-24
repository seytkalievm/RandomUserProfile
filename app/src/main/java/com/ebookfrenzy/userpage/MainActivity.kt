package com.ebookfrenzy.userpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ebookfrenzy.userpage.databinding.MainActivityBinding
import com.ebookfrenzy.userpage.db.ProfilesDatabase
import com.ebookfrenzy.userpage.repository.ProfileRepository
import com.ebookfrenzy.userpage.ui.main.UserPageViewModel
import com.ebookfrenzy.userpage.ui.main.UserPageViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: UserPageViewModel
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationMenu.setupWithNavController(navController)

        val db = ProfilesDatabase.getInstance(application)
        val profileRepository =  ProfileRepository(db)
        val viewModelProviderFactory = UserPageViewModelProviderFactory(application, profileRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[UserPageViewModel::class.java]

    }
}