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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        var studentId = 0
        val navController = findNavController()


        binding.Enterbutton.setOnClickListener {

            val action = SignInFragmentDirections.actionSignInFragmentToLotSelectionFragment()
            navController.navigate(action)

            studentId = binding.EnterIDEditText.text.toString().toInt()

            //lifecycleScope.launch {

            //}
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}