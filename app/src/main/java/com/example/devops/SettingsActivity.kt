package com.example.devops

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private val prefs by lazy { getSharedPreferences("settings", MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val ipEditText = findViewById<EditText>(R.id.ipEditText)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        // Pre-populate with saved IP
        ipEditText.setText(prefs.getString("backend_ip", ""))

        saveBtn.setOnClickListener {
            val newIp = ipEditText.text.toString().trim()

            if (newIp.isNotEmpty()) {
                prefs.edit().putString("backend_ip", newIp).apply()
                Toast.makeText(this, "Backend IP saved: $newIp", Toast.LENGTH_SHORT).show()
                finish() // return to MainActivity
            } else {
                Toast.makeText(this, "Please enter a valid IP", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
