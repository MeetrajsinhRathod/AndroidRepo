package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityCoughMedicineBinding
import com.example.design.databinding.LayoutMedicineBinding
import com.example.modal.Medicine

class CoughMedicineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoughMedicineBinding
    private val medicineList = arrayListOf(
        Medicine("Bronchital", "Syrup", "100ml", R.drawable.syrup, "$2.95", "$0"),
        Medicine("Mucinex DM", "Tab", "68 count", R.drawable.tablet, "$3.49", "$0"),
        Medicine("Bronchital", "Syrup", "100ml", R.drawable.syrup, "$2.95", "$0"),
        Medicine("Mucinex DM", "Tab", "68 count", R.drawable.tablet, "$3.49", "$0"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoughMedicineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            tabLayout.getTabAt(3)?.select()
            medicineTextInputLayout.setEndIconOnClickListener {
                etMedicine.text?.clear()
            }
        }
        setUpMedicineData(medicineList[0], binding.medicine1)
        setUpMedicineData(medicineList[1], binding.medicine2)
        setUpMedicineData(medicineList[2], binding.medicine3)
        setUpMedicineData(medicineList[3], binding.medicine4)
    }

    private fun setUpMedicineData(medicine: Medicine, layout: LayoutMedicineBinding) {
        layout.apply {
            tvMedicineName.text = medicine.name
            medicineImageView.setImageResource(medicine.imageId)
            tvMedicineType.text = medicine.type
            tvMedicineQuantity.text = medicine.quantity
            tvBeforeDeductibleAmount.text = medicine.beforeDeductibleAmount
            tvDeductibleMetAmount.text = medicine.deductibleAmountMet
        }
    }
}