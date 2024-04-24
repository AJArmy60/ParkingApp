package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.databinding.FragmentParkedBinding


class ParkedFragment : Fragment() {

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
        val studentId = LotSelectionFragmentArgs.fromBundle(requireArguments()).studentId

        binding.LeaveSpacebutton.setOnClickListener {
            val action = ParkedFragmentDirections.actionParkedFragmentToLeftFragment(studentId)
            navController.navigate(action)
        }
    }
}