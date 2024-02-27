package com.blackhand.chatgpt.core.utils

import android.app.AlertDialog
import android.content.Context

object DialogUtils {

    fun showErrorDialog(
        context: Context,
        title: String?,
        desc: String?,
        positiveBtnTitle: String?,
        shouldShow: Boolean = false
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(desc)
            .setCancelable(false)
        dialog.setPositiveButton(positiveBtnTitle) { click, _ ->
            click.dismiss()
        }
        if (shouldShow) {
            dialog.show()
        }
    }
}