package com.ushatech.porter.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ushatech.porter.R
import com.ushatech.porter.databinding.ActivityPickItemsBinding
import com.ushatech.porter.presentation.BaseActivity
import com.ushatech.porter.ui.fragment.*
import com.ushatech.porter.utils.FragmentUtils

class   PickItemsActivity : BaseActivity() {

    private lateinit var binding:ActivityPickItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickItemsBinding.inflate(layoutInflater)
        initClicks()
        setupInitialFragment()
        setContentView(binding.root)
    }

    private fun setupInitialFragment() {
        FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.stepsContainer,ItemStepFragment(),ItemCartFragment::class.java.simpleName,true)
    }

    override fun onBackPressed() {
        // Step 1 Fragment back means finish the activity.
        if(supportFragmentManager.backStackEntryCount==1){
            finish()
        }
        super.onBackPressed()
    }

    private fun initClicks() {
        binding.CommonTitleBar.ivClose.setOnClickListener {
            finish()
        }
        binding.ivBackFragment.setOnClickListener {
//            showToast(supportFragmentManager.backStackEntryCount.toString())
            if(supportFragmentManager.backStackEntryCount==2){
                binding.tvStepCounter.setText("Steps(1/4)")
                binding.tvStepTitle.setText("Select items")
                binding.btnProceed.setText("Proceed")

                supportFragmentManager.popBackStack()

            }else if(supportFragmentManager.backStackEntryCount==1){
                finish()
            }else if(supportFragmentManager.backStackEntryCount==3){
                binding.tvStepCounter.setText("Steps(2/4)")
                binding.tvStepTitle.setText("Location details")
                binding.btnProceed.setText("Proceed")

                supportFragmentManager.popBackStack()
            }else if(supportFragmentManager.backStackEntryCount==4){
                binding.tvStepCounter.setText("Steps(3/4)")
                binding.tvStepTitle.setText("What type of service do you need?")
                binding.btnProceed.setText("Proceed")

                supportFragmentManager.popBackStack()
            }

        }

        binding.btnProceed.setOnClickListener {
            // Manage the step 4 fragment navigation here.


            if(supportFragmentManager.backStackEntryCount==1){
                binding.tvStepCounter.setText("Steps(2/4)")
                binding.btnProceed.setText("Proceed")

                binding.tvStepTitle.setText("Location details")
                FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.stepsContainer,SelectLocationFragment(),SelectLocationFragment::class.java.simpleName,true)
            }else if(supportFragmentManager.backStackEntryCount==2){
                binding.tvStepCounter.setText("Steps(3/4)")
                binding.btnProceed.setText("Proceed")
                binding.tvStepTitle.setText("What type of service do you need?")
                FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.stepsContainer,ServiceTypeFragment(),ServiceTypeFragment::class.java.simpleName,true)
            }else if(supportFragmentManager.backStackEntryCount==3){
                binding.tvStepCounter.setText("Steps(4/4)")
                binding.tvStepTitle.setText("Confirm Booking")
                binding.btnProceed.setText("Confirm Booking")
                FragmentUtils().addFragmentBackStack(supportFragmentManager,R.id.stepsContainer,OrderSummaryFragment(),OrderSummaryFragment::class.java.simpleName,true)
            }

        }



    }
}