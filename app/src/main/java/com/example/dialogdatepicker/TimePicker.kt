package com.example.dialogdatepicker

import android.app.Dialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar


class TimePicker: DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireActivity(),
            activity as OnTimeSetListener,
            currentHour,
            currentMinute,
            android.text.format.DateFormat.is24HourFormat(activity)
        )

        return timePickerDialog
    }
}