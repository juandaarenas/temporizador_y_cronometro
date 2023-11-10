package com.example.tempocrono

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.tempocrono.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var  binding: ActivityMainBinding
    lateinit var time: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pantalla_Completa()
        Cronometro()
        binding.btnVentanaTempo.setOnClickListener {
           startActivity(Intent(this,MainActivity2::class.java))
        }
    }
    fun pantalla_Completa(){
        WindowCompat.setDecorFitsSystemWindows(window,false)
        WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.systemBars())
    }
    fun Cronometro(){
        binding.apply {
            var count =0
            var minuts=0
            var hours=0
            var segund:Long = 1000 * 72000
            time = object : CountDownTimer(segund,1000) {
                override fun onTick(millisUntilFinished: Long) {
                    if (count==0||count>0) {
                        count++
                    }
                    if (count<10){
                        textCronometro.text = "00:00:0$count"
                    }else{
                        textCronometro.text = "00:00:$count"
                    }
                    if (count>59){
                        count=0
                        minuts++
                    }
                    if (minuts>0&&minuts<10&&count<10){
                        textCronometro.text = "00:0$minuts:0$count"
                    }
                    if (minuts>0&&minuts<10&&count>9){
                        textCronometro.text = "00:0$minuts:$count"
                    }
                    if (minuts>0&&minuts>10&&count<9){
                        textCronometro.text = "00:$minuts:0$count"
                    }
                    if (minuts>10&&count>9){
                        textCronometro.text = "00:$minuts:$count"
                    }
                    if (minuts==60){
                        minuts=0
                        hours++
                    }
                    if (hours>0&&hours<10&&minuts>0&&minuts<10&&count<10){
                        textCronometro.text = "0$hours:0$minuts:0$count"
                    }
                    if (hours>0&&hours<10&&minuts>0&&minuts<10&&count>9){
                        textCronometro.text = "0$hours:0$minuts:$count"
                    }
                    if (hours>0&&hours>10&&minuts>0&&minuts<10&&count<9){
                        textCronometro.text = "$hours:0$minuts:0$count"
                    }
                    if (hours>0&&hours>10&&minuts>0&&minuts>10&&count<9){
                        textCronometro.text = "$hours:$minuts:0$count"
                    }
                    if (hours>0&&hours>10&&minuts>10&&count>9){
                        textCronometro.text = "$hours:$minuts:$count"
                    }

                }

                override fun onFinish() {

                }
            }
            var iniciar = false
            btnPlayStop.setOnClickListener {
                if (iniciar==false){
                    time.start()
                    iniciar = true
                    btnPlayStop.text = "Detener"
                }else{
                    time.cancel()
                    btnPlayStop.text = "Iniciar"
                    iniciar = false

                }
            }
            btnReStart.setOnClickListener {
                textCronometro.text ="00:00:00"
                time.cancel()
                count=1
                minuts=0
                hours=0
                btnPlayStop.text = "Iniciar"
                iniciar = false
            }
        }
    }
}