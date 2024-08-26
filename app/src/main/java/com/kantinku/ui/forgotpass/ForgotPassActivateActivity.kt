package com.kantinku.ui.forgotpass

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.kantinku.R
import com.kantinku.databinding.ActivityForgotPassActivateBinding

class ForgotPassActivateActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityForgotPassActivateBinding
    private val binding get() = _binding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPassActivateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupTextWatcher()
    }
    
    
//    Thanks to Jamshid Sobirov
    private val textInputLayouts: List<TextInputLayout> by lazy {
        listOf(
            findViewById(R.id.til1),
            findViewById(R.id.til2),
            findViewById(R.id.til3),
            findViewById(R.id.til4),
            findViewById(R.id.til5),
            findViewById(R.id.til6)
        )
    }
    
    private val textInputEditTexts: List<TextInputEditText> by lazy {
        listOf(
            findViewById(R.id.et1),
            findViewById(R.id.et2),
            findViewById(R.id.et3),
            findViewById(R.id.et4),
            findViewById(R.id.et5),
            findViewById(R.id.et6)
        )
    }
    
    private fun setupTextWatcher() {
        for (i in textInputLayouts.indices) {
            val currentEditText = textInputEditTexts[i]
            
            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                    // No implementation needed
                }
                
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1) {
                        // Move focus to the next TextInputEditText
                        moveFocusToNext(i)
                    }
                }
                
                override fun afterTextChanged(s: Editable?) {
                    if (i == textInputEditTexts.size - 1) {
                        var otp = ""
                        for (et in textInputEditTexts) {
                            otp += (et.text)
                        }
                        
                        if (otp.length == textInputEditTexts.size) {
                            currentEditText.clearFocus()
                            hideKeyboard(currentEditText)
                            
                            //you can use otp now anywhere!
                        }
                    }
                }
            })
            
            if (i != 0) {
                currentEditText.setOnKeyListener { _, keyCode, event ->
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                        if (!currentEditText.text.isNullOrEmpty()) {
                            //if the editText is not empty, it clears it
                            false
                        } else {
                            moveFocusToPreviousAndClear(i)
                            true
                        }
                        
                    } else {
                        false
                    }
                }
                
            }
        }
    }
    
    private fun moveFocusToNext(index: Int) {
        if (index < textInputEditTexts.size - 1) {
            textInputEditTexts[index + 1].requestFocus()
        }
    }
    
    private fun moveFocusToPreviousAndClear(index: Int) {
        if (index > 0) {
            val previousEditText = textInputEditTexts[index - 1]
            previousEditText.requestFocus()
            previousEditText.text?.clear()
        }
    }
    
    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}