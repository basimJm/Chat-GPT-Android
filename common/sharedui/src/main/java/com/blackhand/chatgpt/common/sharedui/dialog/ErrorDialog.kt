package com.blackhand.chatgpt.common.sharedui.dialog

import android.app.AlertDialog
import android.content.Context

object ErrorDialog {
     fun showErrorDialog(
        context: Context,
        title: String?,
        desc: String?,
        positiveBtnTitle: String?,
    ) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle(title)
        dialog.setMessage(desc)
            .setCancelable(false)
        dialog.setPositiveButton(positiveBtnTitle) { it, _ ->
            it.dismiss()
        }
        dialog.show()
    }
}