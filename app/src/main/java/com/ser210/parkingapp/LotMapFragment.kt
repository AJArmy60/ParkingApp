package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.databinding.FragmentLotMapBinding

class LotMapFragment : Fragment() {


    private var _binding: FragmentLotMapBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLotMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (super.onViewCreated(view, savedInstanceState))
        val navController = findNavController()
        val studentId = LotSelectionFragmentArgs.fromBundle(requireArguments()).studentId


        // bind views
        val lotName = binding.LotnameText
        lotName.text = LotMapFragmentArgs.fromBundle(requireArguments()).lotName

        binding.ParkButton.setOnClickListener {
            val action = LotMapFragmentDirections.actionLotMapFragmentToParkedFragment(studentId)
            navController.navigate(action)
        }

    }
}

