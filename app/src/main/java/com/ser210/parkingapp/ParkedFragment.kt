package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.databinding.FragmentParkedBinding


class ParkedFragment : Fragment() {

    private val viewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory(
            (activity?.application as ParkingApplication).database.spaceDao()
        )
    }

    lateinit var space: Space

    private var _binding: FragmentParkedBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentParkedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        (super.onViewCreated(view, savedInstanceState))
        val navController = findNavController()

        val spaceId = ParkedFragmentArgs.fromBundle(requireArguments()).spaceId
        val lotName = ParkedFragmentArgs.fromBundle(requireArguments()).lotName
        val studentId = LotSelectionFragmentArgs.fromBundle(requireArguments()).studentId

        binding.LotInText.text = lotName
        binding.SpaceNumberText.text = spaceId.toString()
        binding.studentIdParkedTextView.text = studentId.toString()

        binding.LeaveSpacebutton.setOnClickListener {
            viewModel.leaveSpace(lotName, spaceId)
            val action = ParkedFragmentDirections.actionParkedFragmentToLotSelectionFragment(studentId)
            navController.navigate(action)
        }
    }




}