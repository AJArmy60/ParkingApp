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
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.data.SpaceRoomDatabase
import com.ser210.parkingapp.databinding.FragmentSignInBinding

import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    //private val navigationArgs: SignInFragmentArgs by navArgs()

    private val viewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory(
            (activity?.application as ParkingApplication).database.spaceDao()
        )
    }

    lateinit var space: Space
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        var studentId = 0
        val studentIdEditText = binding.EnterIDEditText
        val navController = findNavController()


        binding.Enterbutton.setOnClickListener {

            if (studentIdEditText.text.toString().isEmpty()) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (studentIdEditText.text.toString().toInt() <= 0) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (studentIdEditText.text.toString().toInt() > 9999999) {
                Toast.makeText(context, "Please enter a valid student ID", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {

                viewModel.deleteAllSpaces()
                addNewSpaces()
                studentId = binding.EnterIDEditText.text.toString().toInt()
                val action =
                    SignInFragmentDirections.actionSignInFragmentToLotSelectionFragment(studentId)
                navController.navigate(action)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addNewSpaces() {
        for (i in 1..32) {
            viewModel.addNewSpace("Hilltop", 0, i)
        }
        for (i in 1..32) {
            viewModel.addNewSpace("Hogan", 0, i)
        }
        for (i in 1..32) {
            viewModel.addNewSpace("North", 0, i)
        }
    }

}