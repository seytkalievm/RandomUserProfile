package com.ebookfrenzy.userpage.ui.main

import android.app.Activity
import android.app.AlertDialog
import com.ebookfrenzy.userpage.R

class LoadingDialog(activity: Activity){

    private var alertDialog: AlertDialog

    init {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)
        alertDialog = builder.create()
    }

    fun showDialog(){
        alertDialog.show()
    }

    fun cancelDialog(){
        alertDialog.cancel()
    }

}