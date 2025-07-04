package com.example.btngb

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeSelectorScreen(
    selectedColor: MutableState<Color>,
    onApply: () -> Unit
) {
    val colorOptions = listOf(
        Color(0xFF2196F3) to "Xanh Biển",
        Color(0xFF9C27B0) to "Tím",
        Color(0xFF000000) to "Đen"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(selectedColor.value),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Chọn chủ đề",
                fontSize = 24.sp,
                color = if (selectedColor.value == Color.Black) Color.White else Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                colorOptions.forEach { (color, label) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            selectedColor.value = color
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(color, CircleShape)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = label,
                            color = if (selectedColor.value == Color.Black) Color.White else Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onApply) {
                Text("Apply")
            }
        }
    }
}
