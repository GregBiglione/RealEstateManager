package com.openclassrooms.realestatemanager.ui.dialog_box

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText
import com.openclassrooms.realestatemanager.R

class AddHousePhotoDialog(context: Context): Dialog(context) {

    private lateinit var addHousePhotoDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_house_photo_dialog)
    }

    //----------------------------------------------------------------------------------------------
    //------------------- Hide keyboard ------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    private fun closeKeyboard(view: View){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}