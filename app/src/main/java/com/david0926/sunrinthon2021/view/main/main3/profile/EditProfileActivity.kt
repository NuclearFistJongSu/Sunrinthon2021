package com.david0926.sunrinthon2021.view.main.main3.profile

import android.app.Activity
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivityEditProfileBinding
import com.david0926.sunrinthon2021.network.StockerRetrofit
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.dialog.LoadingDialog
import com.david0926.sunrinthon2021.view.main.MainActivity
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URI


class EditProfileActivity :
    MvvmActivity<ActivityEditProfileBinding, EditProfileViewModel>(R.layout.activity_edit_profile) {

    var mProfileUri: Uri? = null

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data


                mProfileUri = fileUri
                binding.circleImageView.setImageURI(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditProfileViewModel::class.java)
        binding.viewModel = viewModel

        val user = UserCache.getUser(this)
        if(mProfileUri == null)
            binding.user = user
        viewModel.introduce.value = user.information


        binding.btnEditProfilePhoto.setOnClickListener {
            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }


        }

        binding.btnEditProfileSave.setOnClickListener {

            var photoBody: MultipartBody.Part? = null
            if (mProfileUri != null) {
                val photoFile = File(mProfileUri!!.path!!)
                val photoFileBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoFile)
                photoBody =
                    MultipartBody.Part.createFormData("photo", photoFile.name, photoFileBody)
                val authManager =  AuthManager(this)
                authManager.updateProfilePhoto(photoBody, {
                    finish()
                }, {
                    it.printStackTrace()
                })

            }


        }

        binding.toolbarEditProfile.setNavigationOnClickListener {
            finish()
        }
    }

}