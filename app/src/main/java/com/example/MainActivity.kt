package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.PasswordRepository
import com.example.ui.AppNavigation
import com.example.ui.PasswordViewModel
import com.example.ui.PasswordViewModelFactory
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "stoicpass-db"
        ).build()
        val repository = PasswordRepository(db.passwordDao())
        
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel: PasswordViewModel = viewModel(
                        factory = PasswordViewModelFactory(repository)
                    )
                    AppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}
