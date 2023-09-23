package com.example.kleine.fragments.admin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.kleine.R
import com.example.kleine.databinding.FragmentAdminAddRewardBinding
import com.example.kleine.databinding.FragmentAdminUpdateRewardBinding
import com.example.kleine.viewmodel.admin.AdminAddRewardViewModel
import com.example.kleine.viewmodel.admin.AdminUpdateRewardViewModel

class AdminUpdateRewardFragment : Fragment() {

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 1234
    }

    private lateinit var binding: FragmentAdminUpdateRewardBinding
    private lateinit var viewModel: AdminUpdateRewardViewModel
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_update_reward, container, false)
        viewModel = ViewModelProvider(this).get(AdminUpdateRewardViewModel::class.java)
        binding.adminUpdateRewardViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val documentId = arguments?.getString("documentId")
        viewModel.loadRewardDetails(documentId)

        viewModel.imageUrl.observe(viewLifecycleOwner, Observer { url ->
            url?.let {
                val imageView: ImageView = binding.imgRewardPreview
                Glide.with(this@AdminUpdateRewardFragment)
                    .load(it)
                    .into(imageView)
            }
        })

        binding.imgRewardPreview.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }

        binding.btnUpdate.setOnClickListener {
            viewModel.updateRewardDetailsWithImage(documentId, selectedImageUri)
        }

        viewModel.updateResult.observe(viewLifecycleOwner, Observer { result ->
            if (result) {
                Toast.makeText(context, "Reward updated successfully!", Toast.LENGTH_SHORT).show()
                // Navigate back to AdminViewRewardFragment
                findNavController().navigateUp()
            }
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            selectedImageUri?.let {
                binding.imgRewardPreview.setImageURI(it)
            }
        }
    }

}
