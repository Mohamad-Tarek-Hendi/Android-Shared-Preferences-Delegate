package com.example.androidsharedpreferencesdelegate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidsharedpreferencesdelegate.ui.theme.AndroidSharedPreferencesDelegateTheme

class MainActivity : ComponentActivity() {

    // Get reference by key
    private var token by sharedPreferences("token", "")
    private var numberId by sharedPreferences("numberId", 0)
    private var isOnline by sharedPreferences("isOnline", false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Edit value
        token = "Tarek Hendi"
        numberId = 33
        isOnline = true

        setContent {
            AndroidSharedPreferencesDelegateTheme {
                println(token)
                println(numberId.toString())
                println(isOnline.toString())
            }
        }
    }
}

