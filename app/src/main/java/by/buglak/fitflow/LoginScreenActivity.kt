package by.buglak.fitflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class LoginScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        CoroutineScope(Dispatchers.IO).launch {
            goFromLogToTrainingScreen()
        }
    }

    suspend fun goFromLogToTrainingScreen() {
        delay(3000)
        withContext(Dispatchers.Main) {
            startActivity(Intent(this@LoginScreenActivity, MainActivity::class.java))
        }
    }
}
