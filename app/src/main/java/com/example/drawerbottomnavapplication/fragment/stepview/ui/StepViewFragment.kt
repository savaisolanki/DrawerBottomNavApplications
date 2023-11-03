package com.example.drawerbottomnavapplication.fragment.stepview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.MenuHost
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.activity.ui.MainActivity
import com.example.drawerbottomnavapplication.databinding.FragmentStepViewBinding
import com.example.drawerbottomnavapplication.fragment.stepview.adapter.StepViewViewPagerAdapter
import com.example.drawerbottomnavapplication.utils.Utils


class StepViewFragment : Fragment() {

    private lateinit var binding: FragmentStepViewBinding
    private lateinit var basicDetailsFragment: BasicDetailsFragment
    private lateinit var addressDetailsFragment: AddressDetailsFragment
    private lateinit var paymentDetailsFragment: PaymentDetailsFragment
    private var currentStep = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "StepView"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = false,
            cameraIsVisible = false
        )
        setUpViewPager()
        updateTvBackground()

        binding.btnNext.setOnClickListener {

            when {
                binding.vpStepView.currentItem == 0 && basicDetailsFragment.validation() -> {
                    binding.vpStepView.currentItem += 1
                    currentStep = 1
                    updateTvBackground()
                }

                binding.vpStepView.currentItem == 1 && addressDetailsFragment.validation() -> {
                    binding.vpStepView.currentItem += 1
                    val name = "submit"
                    binding.btnNext.text = name
                    currentStep = 2
                    updateTvBackground()
                }

                binding.vpStepView.currentItem == 2 && paymentDetailsFragment.validation() -> {
                    binding.vpStepView.currentItem += 1
                    currentStep = 3

                    updateTvBackground()
                    findNavController().popBackStack()
                }
            }
        }

    }


    override fun onResume() {
        super.onResume()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportActionBar?.show()
        mainActivity.supportActionBar?.title = "StepView"
        mainActivity.binding.bottomNavigation.visibility = View.GONE
        mainActivity.lockDrawer()
        mainActivity.binding.tbToolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        mainActivity.binding.tbToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        Utils.menuHost(
            mainActivity as MenuHost,
            galleryIsVisible = false,
            imageIsVisible = false,
            cameraIsVisible = false
        )
    }

    private fun setUpViewPager() {

        basicDetailsFragment = BasicDetailsFragment()
        addressDetailsFragment = AddressDetailsFragment()
        paymentDetailsFragment = PaymentDetailsFragment()

        val arrayListFragments =
            arrayListOf(basicDetailsFragment, addressDetailsFragment, paymentDetailsFragment)
        binding.vpStepView.adapter = StepViewViewPagerAdapter(requireActivity(), arrayListFragments)
        binding.vpStepView.isUserInputEnabled = false

    }

    private fun updateTvBackground() {
        val selectedBackground = R.drawable.step_view_selected
        val unselectedBackground = R.drawable.step_view_un_selected

        val selectedInsideTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val dataSelectedOutSideTextColor = ContextCompat.getColor(requireContext(), R.color.orange)

        val dataUnSelectedInsideTextColor = ContextCompat.getColor(requireContext(), R.color.green)
        val unselectedTextOutSideColor = ContextCompat.getColor(requireContext(), R.color.green)

        when (currentStep) {
            0 -> {
                binding.tvOne.setBackgroundResource(selectedBackground)
                binding.tvOne.setTextColor(selectedInsideTextColor)
                binding.tvBasicDetails.setTextColor(dataSelectedOutSideTextColor)


                binding.tvTwo.setBackgroundResource(unselectedBackground)
                binding.tvTwo.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvAddress.setTextColor(unselectedTextOutSideColor)

                binding.tvThree.setBackgroundResource(unselectedBackground)
                binding.tvThree.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvPayment.setTextColor(unselectedTextOutSideColor)

            }

            1 -> {

                binding.tvOne.setBackgroundResource(unselectedBackground)
                binding.tvOne.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvBasicDetails.setTextColor(unselectedTextOutSideColor)

                binding.tvTwo.setBackgroundResource(selectedBackground)
                binding.tvTwo.setTextColor(selectedInsideTextColor)
                binding.tvAddress.setTextColor(dataSelectedOutSideTextColor)

                binding.tvPayment.setTextColor(unselectedTextOutSideColor)
                binding.tvThree.setBackgroundResource(unselectedBackground)
                binding.tvThree.setTextColor(dataUnSelectedInsideTextColor)
            }

            2 -> {

                binding.tvOne.setBackgroundResource(unselectedBackground)
                binding.tvOne.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvBasicDetails.setTextColor(unselectedTextOutSideColor)

                binding.tvTwo.setBackgroundResource(unselectedBackground)
                binding.tvTwo.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvAddress.setTextColor(unselectedTextOutSideColor)

                binding.tvPayment.setTextColor(dataSelectedOutSideTextColor)
                binding.tvThree.setBackgroundResource(selectedBackground)
                binding.tvThree.setTextColor(selectedInsideTextColor)
            }

            else -> {
                binding.tvOne.setBackgroundResource(unselectedBackground)
                binding.tvOne.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvBasicDetails.setTextColor(unselectedTextOutSideColor)

                binding.tvTwo.setBackgroundResource(unselectedBackground)
                binding.tvTwo.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvAddress.setTextColor(unselectedTextOutSideColor)


                binding.tvThree.setBackgroundResource(unselectedBackground)
                binding.tvThree.setTextColor(dataUnSelectedInsideTextColor)
                binding.tvPayment.setTextColor(unselectedTextOutSideColor)

            }
        }
    }




}