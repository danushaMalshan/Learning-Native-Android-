package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch:Chronometer
    var running=false
    var offset:Long=0


    //add key string for use with the bundle
    val OFFSET_KEY="offset"
    val RUNNING_KEY="running"
    val BASE_KEY="base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get a reference to the stopwatch
        stopwatch =findViewById<Chronometer>(R.id.stopwatch)

        //restore the previous state
        if(savedInstanceState!=null){
            offset=savedInstanceState.getLong(OFFSET_KEY)
            running=savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                stopwatch.base=savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            }else setBaseTime()
        }

        val startButton=findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if(!running){
                setBaseTime()
                stopwatch.start()
                running=true
            }
        }

        val pauseButton=findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if(running){
                saveOffset()
                stopwatch.stop()
                running=false
            }
        }

        val resetButton=findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset=0
            setBaseTime()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(OFFSET_KEY,offset)
        outState.putBoolean(RUNNING_KEY,running)
        outState.putLong(BASE_KEY,stopwatch.base)

        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        if(running){

            saveOffset()
            stopwatch.stop()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
        setBaseTime()
        stopwatch.start()
        offset=0
    }

    fun setBaseTime(){
        stopwatch.base=SystemClock.elapsedRealtime()-offset
    }

    fun saveOffset(){
        offset=SystemClock.elapsedRealtime()-stopwatch.base
    }
}