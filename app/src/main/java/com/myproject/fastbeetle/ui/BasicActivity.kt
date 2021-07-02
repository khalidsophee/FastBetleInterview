package com.myproject.fastbeetle.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.myproject.fastbeetle.di.Injectable
import dagger.android.AndroidInjection


abstract class BasicActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        if (this is Injectable) AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setupFocusOutside(findViewById(android.R.id.content))
    }


    @SuppressLint("ClickableViewAccessibility")
    private fun setupFocusOutside(view: View) {
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                // hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupFocusOutside(innerView)
            }
        }
    }


}