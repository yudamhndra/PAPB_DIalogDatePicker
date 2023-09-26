package com.example.dialogdatepicker

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dialogdatepicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var daySelected = -1
    private var monthSelected = -1
    private var yearSelected = -1
    private var hourSelected = -1
    private var minuteSelected = -1
    private val months = arrayOf(
        "Januari",
        "Febuari",
        "Maret",
        "April",
        "Juni",
        "Juli",
        "Agustus",
        "September",
        "Oktober",
        "November",
        "Desember"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val attendanceOptions = resources.getStringArray(R.array.attendanceOption)

        with(binding) {

            calendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
                daySelected = dayOfMonth
                monthSelected = month
                yearSelected = year
            }

            val attendanceAdapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                attendanceOptions
            )

            attendanceAdapter.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item
            )

            spinnerKehadiran.adapter = attendanceAdapter

            jam.setOnTimeChangedListener { _, hourOfDay, minute ->
                hourSelected = hourOfDay
                minuteSelected = minute
            }

            spinnerKehadiran.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val selectedOption = attendanceOptions[position]
                        if (position > 0) {
                            alasan.visibility = View.VISIBLE
                        } else {
                            alasan.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }

            btnSubmit.setOnClickListener {
                if (daySelected == -1 || monthSelected == -1 || yearSelected == -1) {
                    Toast.makeText(this@MainActivity, "Anda belum memilih tanggal", Toast.LENGTH_SHORT).show()
                } else if (hourSelected == -1 || minuteSelected == -1) {
                    Toast.makeText(this@MainActivity, "Anda belum memilih jam", Toast.LENGTH_SHORT).show()
                } else {
                    val selectedTime = String.format("%02d:%02d", hourSelected, minuteSelected)
                    val selectedMonth = months[monthSelected]
                    val selectedDate = "$daySelected $selectedMonth $yearSelected"
                    val message = "Presensi berhasil pada $selectedDate pukul $selectedTime"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
