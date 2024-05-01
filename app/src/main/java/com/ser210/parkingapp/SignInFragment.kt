package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private val viewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory(
            (activity?.application as ParkingApplication).database.spaceDao()
        )
    }

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        var studentId: Int
        val studentIdEditText = binding.EnterIDEditText
        val navController = findNavController()


        // Listener for the enter button
        binding.Enterbutton.setOnClickListener {

            // Get the student ID from the EditText
            studentId = binding.EnterIDEditText.text.toString().toInt()

            // Initialize the spaces if it's the first time the app is run
            viewModel.initializeSpaces()

            // Check if the student ID is valid
            if (studentIdEditText.text.toString().isEmpty()) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (studentId <= 0) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (studentId > 9999999) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener

            // Check if the student is already parked
            } else {
                viewModel.getAllSpacesAsLiveData().observe(viewLifecycleOwner) { spaces ->
                    spaces.forEachIndexed { index, space ->
                        // if the student is already parked, navigate to the parked fragment
                        if (space.studentId == studentId) {
                            val action =
                                SignInFragmentDirections.actionSignInFragmentToParkedFragment(
                                    studentId,
                                    space.lotName, space.spaceId
                                )
                            navController.navigate(action)
                            return@observe
                        }
                    }
                    // if the student is not parked, navigate to the lot selection fragment
                    val action =
                        SignInFragmentDirections.actionSignInFragmentToLotSelectionFragment(
                            studentId
                        )
                    navController.navigate(action)
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