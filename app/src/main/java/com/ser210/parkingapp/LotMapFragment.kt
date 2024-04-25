package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.databinding.FragmentLotMapBinding

class LotMapFragment : Fragment() {

    private val navigationArgs: LotMapFragmentArgs by navArgs()
    private val viewModel: ParkingViewModel by activityViewModels()


    lateinit var space: Space

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
        val studentId = LotMapFragmentArgs.fromBundle(requireArguments()).studentId
        val lotName = LotMapFragmentArgs.fromBundle(requireArguments()).lotName

        binding.LotnameText.text = lotName

        binding.ParkButton.setOnClickListener {
            val spaceId = binding.EnterSpaceEditText.text.toString().toInt()
            viewModel.parkInSpace(lotName, studentId, spaceId)
            val action = LotMapFragmentDirections.actionLotMapFragmentToParkedFragment(studentId, lotName, spaceId)
            navController.navigate(action)
        }


    }
}

