package com.ser210.parkingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ser210.parkingapp.databinding.FragmentLeftBinding
import com.ser210.parkingapp.databinding.FragmentParkedBinding

class LeftFragment : Fragment() {

    private var _binding: FragmentLeftBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLeftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val studentId = LotSelectionFragmentArgs.fromBundle(requireArguments()).studentId

        binding.SignOutbutton.setOnClickListener {
            val action = LeftFragmentDirections.actionLeftFragmentToSignInFragment()
            navController.navigate(action)
        }

        binding.BackToLotButton.setOnClickListener {
            val action = LeftFragmentDirections.actionLeftFragmentToLotSelectionFragment(studentId)
            navController.navigate(action)
        }

    }
}