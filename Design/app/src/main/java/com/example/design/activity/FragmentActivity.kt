package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityFragmentBinding
import com.example.design.fragments.BottomSheetFragment
import com.example.design.fragments.PopUpFragment
import com.example.design.fragments.TabLayoutFragment1

class FragmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.setFragmentResultListener("fragment2", this) { _, bundle: Bundle ->
            val result = bundle.getString("Message")
            binding.etMessage.setText(result)
        }

        binding.btnAddFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(binding.fragmentHolder3.id, TabLayoutFragment1(),"1")
                //addToBackStack(null)
                commit()
            }
        }
        binding.btnRemoveFragment.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                supportFragmentManager.findFragmentByTag("1")?.let { it1 -> remove(it1) }
                commit()
            }
        }

        binding.btnPopUpFragment.setOnClickListener {
            val popUpFragment = PopUpFragment()
            popUpFragment.show(supportFragmentManager,"popUp")
        }

        binding.btnBottomSheetFragment.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "bottomSheet")
        }

        binding.btnSubmit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("Message",binding.etMessage.text.toString())
            supportFragmentManager.setFragmentResult("hostActivity",bundle)
            binding.etMessage.text.clear()
        }
    }
}