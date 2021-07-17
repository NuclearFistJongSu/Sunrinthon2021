package com.david0926.sunrinthon2021.view.main.main3.profile

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivityEditProfileBinding
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.base.MvvmActivity

class EditProfileActivity :
    MvvmActivity<ActivityEditProfileBinding, EditProfileViewModel>(R.layout.activity_edit_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        binding.viewModel = viewModel

        val user = UserCache.getUser(this)
        binding.user = user
        viewModel.introduce.value = user.information

        binding.btnEditProfilePhoto.setOnClickListener {

        }

        binding.btnEditProfileSave.setOnClickListener {
            //save profile
        }

        binding.toolbarEditProfile.setNavigationOnClickListener {
            finish()
        }
    }
}