package com.ebookfrenzy.userpage.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebookfrenzy.userpage.R

class LoadingDialog(private val activity: Activity){

    private lateinit var alertDialog: AlertDialog


    init {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.getLayoutInflater()
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