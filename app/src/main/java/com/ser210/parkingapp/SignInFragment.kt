package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ser210.parkingapp.databinding.FragmentSignInBinding
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpaceViewModel by activityViewModels {
        SpaceViewModelFactory((activity?.application as ParkingApplication).database.spaceDao())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            val studentId = binding.studentIdEditText.text.toString().toInt()
            lifecycleScope.launch {
                val spaces = viewModel.getSpacesByStudent(studentId)
                if (spaces==null) {
                    // Student ID found in the Parking database
                    // Navigate to the next screen or show a success message

                    Toast.makeText(context, "Student ID found in the Parking database", Toast.LENGTH_SHORT).show()
                } else {
                    // Student ID not found in the Parking database
                    Toast.makeText(context, "Student ID not found in the Parking database", Toast.LENGTH_SHORT).show()
                    // Show an error message
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}