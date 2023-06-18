package com.example.design.activity

import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.adapters.CardAdapter
import com.example.design.databinding.ActivityRecyclerViewKtBinding
import com.example.design.helper.hideKeyboardOnTouchOutside
import com.example.design.model.CardData
import com.example.design.model.CardImageData
import com.example.design.model.CardSpinnerData
import com.google.android.material.snackbar.Snackbar

interface CardDataSetupInterface {

    fun updateCardAt(cardList: ArrayList<CardData>, cardPosition: Int)
    fun addCard()
    fun deleteCard(position: Int)
    fun pickImage(position: Int)
    fun addImage()
    fun deleteImage(cardPosition: Int, imagePosition: Int)
    fun addSpinner(currentItemPosition: Int)
    fun deleteSpinner(cardPosition: Int, spinnerPosition: Int)
}

class RecyclerViewKtActivity : AppCompatActivity(), CardDataSetupInterface {

    private lateinit var binding: ActivityRecyclerViewKtBinding
    private var cardDataList: ArrayList<CardData> = arrayListOf(CardData())
    private lateinit var cardAdapter: CardAdapter
    private lateinit var imageUri: Uri
    private var currentItemPosition = 0

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imageUri = uri
                addImage()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCardRecyclerView()
    }

    //Function to setup card recyclerView
    private fun setupCardRecyclerView() {
        cardAdapter = CardAdapter(this)
        cardAdapter.submitList(cardDataList)
        binding.cardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewKtActivity)
            adapter = cardAdapter
        }
    }

    //override dispatchTouchEvent function to hide keyboard on touch outside of editText
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            this.hideKeyboardOnTouchOutside()
        }
        return super.dispatchTouchEvent(event)
    }

    //Function to add card in recyclerView
    override fun addCard() {
        cardDataList.add(CardData())
        updateCardAt(cardDataList, cardDataList.lastIndex - 1)
        binding.cardRecyclerView.scrollToPosition(cardDataList.lastIndex)
    }

    /**Function to delete card at given position in recyclerView
     * @param position- to get the current card position in recyclerView
     * */
    override fun deleteCard(position: Int) {
        cardDataList.removeAt(position)
        updateCardAt(cardDataList, cardDataList.lastIndex)
        cardAdapter.notifyItemRemoved(position)
    }

    /** Function which will show image picker
     * @param position- to get the current card position in recyclerView
     * */
    override fun pickImage(position: Int) {
        currentItemPosition = position
        if (cardDataList[position].imageList.count() < 10) {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            Snackbar.make(binding.root, "Max image number reached!", Snackbar.LENGTH_SHORT).show()
        }
    }

    //Function to add image in card
    override fun addImage() {
        cardDataList[currentItemPosition].imageList.add(CardImageData(imageUri))
        updateCardAt(cardDataList, currentItemPosition)
    }

    /**Function to delete image at given position in card
     * @param cardPosition- to get the current card position in recyclerView
     * @param imagePosition- to get the position of image to delete in imageList
     */

    override fun deleteImage(cardPosition: Int, imagePosition: Int) {
        cardDataList[cardPosition].imageList.removeAt(imagePosition)
        updateCardAt(cardDataList, cardPosition)
    }

    /**Function to add spinner in card
     * @param currentItemPosition- to get the current card position in recyclerView
     * */
    override fun addSpinner(currentItemPosition: Int) {
        cardDataList[currentItemPosition].spinnerNumberList.add(CardSpinnerData(0))
        updateCardAt(cardDataList, currentItemPosition)
    }

    /**Function to delete spinner at given position in card
     * @param cardPosition- to get the current card position in recyclerView
     * @param spinnerPosition- to get the current spinner position in spinnerNumberList
     */
    override fun deleteSpinner(cardPosition: Int, spinnerPosition: Int) {
        cardDataList[cardPosition].spinnerNumberList.removeAt(spinnerPosition)
        updateCardAt(cardDataList, cardPosition)
    }

    /**Function to update changes in cardList of recyclerView
     * @param cardList- to get the updated cardList
     * @param cardPosition- to get the position where we want to notify item change
     */
    override fun updateCardAt(cardList: ArrayList<CardData>, cardPosition: Int) {
        cardAdapter.submitList(cardList)
        cardAdapter.notifyItemChanged(cardPosition)
    }
}