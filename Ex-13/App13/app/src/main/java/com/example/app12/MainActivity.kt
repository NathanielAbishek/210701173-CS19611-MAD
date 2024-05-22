package com.example.app12

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app12.databinding.ActivityMainBinding
import com.example.app12.ui.theme.App12Theme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {

            val address = binding.etToAddress.text.toString()
            val subject = binding.etSubject.text.toString()
            val message = binding.etMessage.text.toString()

            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // Only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }

            try {
                // Check if there is an email client to handle the intent
                if (emailIntent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(emailIntent, "Send email using:"))
                } else {
                    Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("EmailIntent", "Error in sending email: ${e.message}")
                Toast.makeText(this, "Error in sending email", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnClear.setOnClickListener {
            binding.etToAddress.text.clear()
            binding.etSubject.text.clear()
            binding.etMessage.text.clear()
        }
    }
}