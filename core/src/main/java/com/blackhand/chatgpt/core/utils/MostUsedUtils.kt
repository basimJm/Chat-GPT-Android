package com.blackhand.chatgpt.core.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.FragmentActivity

object MostUsedUtils {
    fun editTextValidation(list: List<AppCompatEditText>): Boolean {
        var isValid = false
        list.forEach {
            if (it.text.toString().isEmpty()) {
                it.error = "${it.hint} is required"
                isValid = false
            } else {
                it.error = null
                isValid = true
            }
        }
        return isValid
    }

    fun hideKeyBoard(activity: FragmentActivity?, view: View) {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}