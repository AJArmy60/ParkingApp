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
import androidx.core.view.marginStart
import androidx.core.view.setPadding
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ser210.parkingapp.data.Space
import com.ser210.parkingapp.databinding.FragmentLotMapBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        val space01 = binding.Space01
        val space02 = binding.Space02
        val space03 = binding.Space03
        val space04 = binding.Space04
        val space05 = binding.Space05
        val space06 = binding.Space06
        val space07 = binding.Space07
        val space08 = binding.Space08
        val space09 = binding.Space09
        val space10 = binding.Space10
        val space11 = binding.Space11
        val space12 = binding.Space12
        val space13 = binding.Space13
        val space14 = binding.Space14
        val space15 = binding.Space15
        val space16 = binding.Space16
        val space17 = binding.Space17
        val space18 = binding.Space18
        val space19 = binding.Space19
        val space20 = binding.Space20
        val space21 = binding.Space21
        val space22 = binding.Space22
        val space23 = binding.Space23
        val space24 = binding.Space24
        val space25 = binding.Space25
        val space26 = binding.Space26
        val space27 = binding.Space27
        val space28 = binding.Space28
        val space29 = binding.Space29
        val space30 = binding.Space30
        val space31 = binding.Space31
        val space32 = binding.Space32

        val spaceList = listOf(
            space01, space02, space03, space04, space05, space06, space07, space08, space09, space10,
            space11, space12, space13, space14, space15, space16, space17, space18, space19, space20,
            space21, space22, space23, space24, space25, space26, space27, space28, space29, space30,
            space31, space32
        )

        val spaces = viewModel.getAllSpacesInLot(lotName)

        binding.LotnameText.text = lotName


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

