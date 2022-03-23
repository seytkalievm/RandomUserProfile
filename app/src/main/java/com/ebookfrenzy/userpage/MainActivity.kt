package com.ebookfrenzy.userpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.ebookfrenzy.userpage.db.ProfilesDatabase
import com.ebookfrenzy.userpage.repository.ProfileRepository
import com.ebookfrenzy.userpage.ui.main.LoadingDialog
import com.ebookfrenzy.userpage.ui.main.UserPageFragment
import com.ebookfrenzy.userpage.ui.main.UserPageViewModel
import com.ebookfrenzy.userpage.ui.main.UserPageViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: UserPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val db = ProfilesDatabase.getInstance(application)
        val profileRepository =  ProfileRepository(db)
        val viewModelProviderFactory = UserPageViewModelProviderFactory(application, profileRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[UserPageViewModel::class.java]
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserPageFragment.newInstance())
                .commitNow()
        }
    }
}