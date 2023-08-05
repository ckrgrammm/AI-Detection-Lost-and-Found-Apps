package com.example.kleine.fragments.partnership

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kleine.R
import com.example.kleine.databinding.FragmentPartnershipViewMaterialBinding
import com.example.kleine.databinding.FragmentViewPartnershipBinding

/**
 * A simple [Fragment] subclass.
 * Use the [PartnershipViewMaterialFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PartnershipViewMaterialFragment : Fragment() {

    val TAG = "PartnershipViewMaterialFragment"
    private lateinit var binding: FragmentPartnershipViewMaterialBinding

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPartnershipViewMaterialBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewMaterialClick()

        val threeDotsImageView = view.findViewById<ImageView>(R.id.three_dots_image)
        val threeDotsImageView2 = view.findViewById<ImageView>(R.id.three_dots_image2)

        setupPopupMenu(threeDotsImageView)
        setupPopupMenu(threeDotsImageView2)
    }

    private fun onViewMaterialClick() {
        binding.materialViewData.setOnClickListener {
            findNavController().navigate(R.id.action_partnershipViewMaterialFragment_to_partnershipViewMaterialDetailFragment)
        }
        binding.materialViewData2.setOnClickListener {
            findNavController().navigate(R.id.action_partnershipViewMaterialFragment_to_partnershipViewMaterialDetailFragment)
        }
    }

    private fun setupPopupMenu(threeDotsImageView: ImageView) {
        threeDotsImageView.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.menuInflater.inflate(R.menu.popup, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.edit_material -> {
                        // Start EditMaterialActivity when Edit Material is clicked
//                        val editIntent = Intent(requireContext(), EditMaterialActivity::class.java)
//                        startActivity(editIntent)
                        true
                    }
                    R.id.disable_material -> {
                        // Show confirmation dialog when Make it Disabled is clicked
                        showConfirmationDialog()
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Disable Material")
            .setMessage("Are you sure you want to disable this material?")
            .setPositiveButton(android.R.string.yes) { dialog, which ->
                //TODO: Implement your logic to disable the material here.
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

}