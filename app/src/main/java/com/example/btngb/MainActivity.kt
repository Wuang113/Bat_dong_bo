package com.example.btngb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.*
import com.example.btngb.ui.theme.BấtĐồngBộTheme
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BấtĐồngBộTheme {
                val navController = rememberNavController()
                val selectedColor = remember { mutableStateOf(Color.White) }
                val scope = rememberCoroutineScope()

                // Đọc từ DataStore khi khởi động
                LaunchedEffect(true) {
                    ThemeDataStore.getColor(applicationContext).collectLatest { colorInt ->
                        colorInt?.let { selectedColor.value = Color(it) }
                    }
                }

                NavHost(navController = navController, startDestination = "select") {
                    composable("select") {
                        ThemeSelectorScreen(
                            selectedColor = selectedColor,
                            onApply = {
                                scope.launch {
                                    ThemeDataStore.saveColor(applicationContext, selectedColor.value.value)
                                    navController.navigate("applied")
                                }
                            }
                        )
                    }
                    composable("applied") {
                        AppliedScreen(
                            color = selectedColor.value,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
