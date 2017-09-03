package com.martin.basic.library.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.martin.basic.library.R
import com.martin.basic.library.ex.dp2px

/**
 * Created by Martin on 2017/8/19.
 * Hello World
 */
object DialogUtil {


    fun showLoadingDialog(context: Context, message: String = "正在加载..."
                          , cancelable: Boolean = true, dismissListener: DialogInterface.OnDismissListener? = null): AlertDialog {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_common_loading,null)
        val dialog: AlertDialog = AlertDialog.Builder(context, 0)
                .setView(view)
                .setCancelable(cancelable)
                .create()
        val textView = view.findViewById<TextView>(R.id.text)
        textView?.text = message
        dialog.setOnDismissListener(dismissListener)
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window.attributes)
        lp.width = context.dp2px(140f)
        lp.height = context.dp2px(140f)
        dialog.show()
        dialog.window.attributes = lp
        return dialog
    }

    fun hide(dialog: Dialog?) {
        dialog?.let {
            if (it.isShowing) it.dismiss()
        }
    }

}