package com.example.design.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.service.autofill.OnClickAction
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.MovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.design.databinding.ActivityTextViewBinding

class TextViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        val spannableStr = SpannableString("Red Blue(Bold) Green")
        val blue = ForegroundColorSpan(Color.BLUE)
        val green = ForegroundColorSpan(Color.GREEN)
        val bold = StyleSpan(Typeface.BOLD)
        val underLine = UnderlineSpan()
        val lineThrough = StrikethroughSpan()
        val clickable = object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(applicationContext, "Tapped", Toast.LENGTH_SHORT).show()
            }
            override fun updateDrawState(drawState: TextPaint) {
                super.updateDrawState(drawState)
                drawState.color = Color.RED
                drawState.isUnderlineText = false
            }
        }
        spannableStr.setSpan(clickable, 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableStr.setSpan(blue, 4, 14, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableStr.setSpan(bold, 4, 14, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableStr.setSpan(green, 15, 20, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableStr.setSpan(lineThrough, 15, 20, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        spannableStr.setSpan(underLine, 15, 20, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        binding.spanText.movementMethod = LinkMovementMethod.getInstance()
        binding.spanText.text = spannableStr

        binding.clickableText.setOnClickListener {
            binding.clickableText.setTextColor(Color.GREEN)
        }

        binding.longPressText.setOnLongClickListener {
            Toast.makeText(applicationContext, "Long pressed", Toast.LENGTH_SHORT).show()
            binding.longPressText.setTextColor(Color.MAGENTA)
            true
        }
    }
}