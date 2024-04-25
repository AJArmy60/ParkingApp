package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.databinding.FragmentLotSelectionBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LotSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LotSelectionFragment : Fragment() {

    private var studentId: Int = 0;

    private var _binding : FragmentLotSelectionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLotSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val studentId = LotSelectionFragmentArgs.fromBundle(requireArguments()).studentId

        binding.hilltopButton.setOnClickListener {

            val action = LotSelectionFragmentDirections.actionLotSelectionFragmentToLotMapFragment(studentId, "Hilltop")
            navController.navigate(action)

        }

        binding.hoganButton.setOnClickListener {
            val action = LotSelectionFragmentDirections.actionLotSelectionFragmentToLotMapFragment(studentId, "Hogan")
            navController.navigate(action)

        }

        binding.northButton.setOnClickListener {
            val action = LotSelectionFragmentDirections.actionLotSelectionFragmentToLotMapFragment(studentId, "North")
            navController.navigate(action)

        }

        binding.signOutButton.setOnClickListener {
            val action = LotSelectionFragmentDirections.actionLotSelectionFragmentToSignInFragment()
            navController.navigate(action)
        }


    }
}