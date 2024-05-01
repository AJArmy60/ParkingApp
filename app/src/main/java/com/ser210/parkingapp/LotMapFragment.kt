package com.ser210.parkingapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.databinding.FragmentLotMapBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.observe
import kotlinx.coroutines.flow.Flow

class LotMapFragment : Fragment() {

    private val navigationArgs: LotMapFragmentArgs by navArgs()
    private val viewModel: ParkingViewModel by activityViewModels {
        ParkingViewModelFactory(
            (activity?.application as ParkingApplication).database.spaceDao()
        )
    }


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

        val red = ContextCompat.getColor(requireContext(), R.color.red)
        val green = ContextCompat.getColor(requireContext(), R.color.green)

        binding.LotnameText.text = lotName

        val spaceList = listOf(
            binding.Space01, binding.Space02, binding.Space03, binding.Space04, binding.Space05, binding.Space06, binding.Space07, binding.Space08, binding.Space09, binding.Space10,
            binding.Space11, binding.Space12, binding.Space13, binding.Space14, binding.Space15, binding.Space16, binding.Space17, binding.Space18, binding.Space19, binding.Space20,
            binding.Space21, binding.Space22, binding.Space23, binding.Space24, binding.Space25, binding.Space26, binding.Space27, binding.Space28, binding.Space29, binding.Space30,
            binding.Space31, binding.Space32
        )

        viewModel.getAllSpacesInLotAsLiveData(lotName).observe(viewLifecycleOwner) { spaces ->
            spaces.forEachIndexed { index, space ->
                if (space.studentId != 0) {
                    spaceList[index].setBackgroundColor(red)
                } else {
                    spaceList[index].setBackgroundColor(green)
                }
            }
        }


        binding.ParkButton.setOnClickListener {
            val spaceId = binding.EnterSpaceEditText.text.toString().toInt()
            viewModel.enterSpace(lotName, studentId, spaceId)
            val action = LotMapFragmentDirections.actionLotMapFragmentToParkedFragment(
                studentId,
                lotName,
                spaceId
            )
            navController.navigate(action)
        }


    }

    fun createGrid(gridLayout: GridLayout) {


    }
}

