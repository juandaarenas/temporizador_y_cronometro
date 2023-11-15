package com.example.tempocrono

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.tempocrono.databinding.ActivityMain2Binding
import com.example.tempocrono.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {
    lateinit var  binding: ActivityMain2Binding
    lateinit var timer: CountDownTimer
    var count:Long = 0
    var minuts:Long = 0
    var hours:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        Temporizador()
        binding.btnVentanaCrono.setOnClickListener {
            startActivity(Intent(this@MainActivity2, MainActivity::class.java))
        }
    }
    fun Temporizador(){
        binding.apply {
            var iniciar = false
            btnPlayStop.setOnClickListener {
                if (iniciar== false) {
                    count = textSegundos.text.toString().toLong()
                    minuts = textMinutos.text.toString().toLong()
                    hours = textHoras.text.toString().toLong()
                    if (textHoras.text.isNullOrEmpty()) {
                        hours = 0
                    }
                    if (textMinutos.text.isNullOrEmpty()) {
                        minuts = 0
                    }
                    if (textSegundos.text.isNullOrEmpty()) {
                        count = 0
                    }
                    var segundosTotales = TimeUnit.HOURS.toMillis(hours) + TimeUnit.MINUTES.toMillis(minuts) + TimeUnit.SECONDS.toMillis(count)
                    timer = object : CountDownTimer(segundosTotales, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            hours=(millisUntilFinished / 3600000)
                            minuts=((millisUntilFinished % 3600000)/60000)
                            count=((millisUntilFinished % 60000)/1000)
                            textCronometro.text="$hours:$minuts:$count"

                        }
                        override fun onFinish() {

                        }
                    }
                    timer.start()
                    iniciar = true
                    textSegundos.isEnabled=false
                    textMinutos.isEnabled=false
                    textHoras.isEnabled=false
                    imgState.setImageDrawable(getDrawable(R.drawable.temprun))
                    btnPlayStop.text = "Detener"
                } else {
                    timer.cancel()
                    imgState.setImageDrawable(getDrawable(R.drawable.tempstop))
                    btnPlayStop.text = "Iniciar"
                    iniciar = false

                }
            }
            btnReStart.setOnClickListener {
                textCronometro.text = "00:00:00"
                timer.cancel()
                count = 0
                minuts = 0
                hours = 0
                textSegundos.isEnabled=true
                textMinutos.isEnabled=true
                textHoras.isEnabled=true
                imgState.setImageDrawable(getDrawable(R.drawable.tempstar))
                textHoras.setText("")
                textMinutos.setText("")
                textSegundos.setText("")
                btnPlayStop.text = "Iniciar"
                iniciar = false
            }
        }
    }
}