package com.example.greetings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

     lateinit var hello:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         hello=findViewById<TextView>(R.id.hello)
         if(savedInstanceState!=null){
             hello.text=savedInstanceState.getString("name")
         }
        var name =findViewById<EditText>(R.id.name)

         var button=findViewById<Button>(R.id.button)
        button.setOnClickListener {
            hello.text="hello ${name.text.toString()}"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("name",hello.text.toString())

        super.onSaveInstanceState(outState)
    }
}