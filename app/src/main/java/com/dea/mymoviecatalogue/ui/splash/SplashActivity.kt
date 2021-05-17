package com.dea.mymoviecatalogue.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.dea.mymoviecatalogue.R
import com.dea.mymoviecatalogue.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val lottieAnimation: LottieAnimationView = findViewById(R.id.lottie_animation)

        lottieAnimation.alpha = 0f
        lottieAnimation.animate().setDuration(3000).alpha(1f).withEndAction{
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}