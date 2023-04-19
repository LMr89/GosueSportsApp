package com.g4.dev.gosuesprortsapp.util.alertsDialog

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.g4.dev.gosuesprortsapp.R
import java.util.Calendar

class TimePickerDialogFragment(val listener : (String) -> Unit):DialogFragment(),TimePickerDialog.OnTimeSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = TimePickerDialog(activity as Context,
            R.style.TimePickerDialogStyle,
            this,
            9, 0,
            true)
        isCancelable=false
        return  dialog
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val modHour = if (hourOfDay<10) "0${hourOfDay}" else hourOfDay
        listener("${modHour}:${minute}")

    }
}