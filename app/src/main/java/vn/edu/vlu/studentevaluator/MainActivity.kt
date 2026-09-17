package vn.edu.vlu.studentevaluator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Map UI components to Kotlin values
        val edtStudentName = findViewById<EditText>(R.id.edtStudentName)
        val edtScore = findViewById<EditText>(R.id.edtScore)
        val btnEvaluate = findViewById<Button>(R.id.btnEvaluate)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        // 2. Set up the button click event
        btnEvaluate.setOnClickListener {
            // Read inputs
            val name = edtStudentName.text.toString().trim()
            val scoreText = edtScore.text.toString().trim()

            // 3. Input Validation using Kotlin's 'isEmpty()'
            if (name.isEmpty() || scoreText.isEmpty()) {
                Toast.makeText(this, "Please enter both name and score!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit the listener early
            }

            // 4. Apply Null Safety and Type Conversion
            val score = scoreText.toDoubleOrNull() ?: -1.0

            // Validate score range
            if (score !in 0.0..10.0) {
                edtScore.error = "Score must be between 0 and 10"
                return@setOnClickListener
            }

            // 5. Apply the 'when' expression to determine the grade
            val letterGrade = when {
                score >= 8.5 -> "Excellent (A)"
                score >= 7.0 -> "Good (B)"
                score >= 5.5 -> "Average (C)"
                score >= 4.0 -> "Poor (D)"
                else -> "Failed (F)"
            }

            // 6. Output the result using String Templates
            tvResult.text = "Student: $name\nScore: $score\nClassification: $letterGrade"
        }
    }
}