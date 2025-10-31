package edu.farmingdale.pizzapartybottomnavbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ToDo 4: Match the UI as in drawable gpa_design.png. Use the following hints:
// - The background color should be Color.Cyan
// - Fix padding, alignment, and keypad type

// ToDo 5:  Add the GpaAppScreen composable button that clears the input fields when clicked


@Composable
fun GpaAppScreen() {

    var grade1 by remember { mutableStateOf("") }
    var grade2 by remember { mutableStateOf("") }
    var grade3 by remember { mutableStateOf("") }


    // Declare variables for GPA result and background color
    var gpa by remember { mutableStateOf("") }
    var backColor by remember { mutableStateOf(Color.White) }
    var btnLabel by remember { mutableStateOf("Compute GPA") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(24.dp)
        ,verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {

     TextField(
            value = grade1,
            onValueChange = { grade1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),

            label = { Text("Course 1 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )


        TextField(
            value = grade2,
            onValueChange = { grade2 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Course 2 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )




        TextField(
            value = grade3,
            onValueChange = { grade3 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            label = { Text("Course 3 Grade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )



        Button( onClick = {
            if (btnLabel == "Compute GPA") {
                val gpaVal = calGPA(grade1, grade2, grade3)
                if (gpaVal != null) {
                    gpa = String.format("%.2f", gpaVal)

                    // Change background color based on GPA
                    backColor = when {
                        gpaVal < 60 -> Color.Red
                        gpaVal in 60.0..79.0 -> Color.Yellow
                        else -> Color.Green
                    }
                    btnLabel = "Clear"
                } else {
                    gpa = "Invalid input"
                }
            } else {
                // Clear all fields
                grade1 = ""
                grade2 = ""
                grade3 = ""
                gpa = ""
                backColor = Color.White
                btnLabel = "Compute GPA"
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(btnLabel, fontSize = 18.sp)
        }



        if (gpa.isNotEmpty()) {
            Text(
                text = "GPA: $gpa",
                fontSize = 24.sp,

                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(backColor)
                    .padding(16.dp)
            )
        }
    }
}


fun calGPA(grade1: String, grade2: String, grade3: String): Double {
    val grades = listOf(grade1.toDouble(), grade2.toDouble(), grade3.toDouble())
    return grades.average()
}

