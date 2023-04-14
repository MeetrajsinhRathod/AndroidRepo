package com.example.design

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapters.CardAdapter
import com.example.design.databinding.ActivityRecyclerViewKtBinding
import com.example.model.CardData
import com.example.model.CardImageData
import com.example.model.CardSpinnerData


class RecyclerViewKtActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewKtBinding
    private var cardDataList: ArrayList<CardData> = arrayListOf(CardData(0,0,arrayListOf(), arrayListOf()))
    private lateinit var adapter: CardAdapter
    private lateinit var imageUri: Uri
    private var currentItemPosition = 0

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            imageUri = uri
            addImage()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CardAdapter(this::addCard, this::deleteCard, this::pickImage, this::deleteImage, this::addSpinner, this::deleteSpinner)
        adapter.submitList(cardDataList)
        binding.rvRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.rvRecyclerView.adapter = adapter
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view = currentFocus
            if (view is EditText) {
                view.clearFocus()
                val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun addCard() {
        cardDataList.add(CardData(0,0, arrayListOf(), arrayListOf()))
        print(cardDataList)
        adapter.submitList(cardDataList)
        adapter.notifyItemChanged(cardDataList.lastIndex-1)
    }

    private fun deleteCard(position: Int) {
        cardDataList.removeAt(position)
        adapter.submitList(cardDataList)
        adapter.notifyItemChanged(cardDataList.lastIndex)
    }

    private fun pickImage(position: Int) {
        currentItemPosition = position
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun addImage() {
        cardDataList[currentItemPosition].imageList.add(CardImageData(imageUri))
        adapter.notifyItemChanged(currentItemPosition)
        adapter.submitList(cardDataList)
    }

    private fun deleteImage(cardPosition: Int, imagePosition: Int) {
        cardDataList[cardPosition].imageList.removeAt(imagePosition)
        adapter.notifyItemChanged(cardPosition)
        adapter.submitList(cardDataList)
    }

    private fun addSpinner(currentItemPosition: Int) {
        cardDataList[currentItemPosition].spinnerNumberList.add(CardSpinnerData(0))
        adapter.notifyItemChanged(currentItemPosition)
        adapter.submitList(cardDataList)
    }

    private fun deleteSpinner(cardPosition: Int, spinnerPosition: Int) {
        cardDataList[cardPosition].spinnerNumberList.removeAt(spinnerPosition)
        adapter.notifyItemChanged(cardPosition)
        adapter.submitList(cardDataList)
    }
}