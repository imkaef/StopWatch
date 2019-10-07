package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_stopwatch.*
import java.util.*


class StopwatchActivity : AppCompatActivity() {


    private var seconds: Int = 0
    private var running: Boolean = false
    private var wasRunnung: Boolean = false

    final val handler = Handler()
    private fun runTimer (){
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                time_view.setText(time)
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        if (savedInstanceState!=null){
            seconds=savedInstanceState.getInt("seconds")
            running=savedInstanceState.getBoolean("running")
            wasRunnung=savedInstanceState.getBoolean("wasRunnung")
        }
        runTimer()

        start_button.setOnClickListener {
            running=true
        }
        stop_button.setOnClickListener {
            running=false
        }
        reset_button.setOnClickListener {

            running=false
            seconds = 0
        }
    }

   /* override fun onStop() {
        super.onStop()
        wasRunnung=running
        running=false
    }*/
    override fun onPause(){
       super.onPause()
       wasRunnung=running
       running=false
   }

   /* override fun onStart() {
        super.onStart()
        if (wasRunnung){
            running=true
        }
    }
    */
    override fun onResume(){
       super.onResume()
       if (wasRunnung)
           running=true
   }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("seconds",seconds)
        outState.putBoolean("running",running)
        outState.putBoolean("wasRunnung",wasRunnung)
        super.onSaveInstanceState(outState)
    }
}
