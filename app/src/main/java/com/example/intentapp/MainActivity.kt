package com.example.intentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var userText: EditText
    private lateinit var phoneButton: RadioButton
    private lateinit var browserButton: RadioButton
    private lateinit var geolocationButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        clickButton.setOnClickListener(magicListener)
    }

    private var magicListener: View.OnClickListener = View.OnClickListener {
        var intent: Intent
        val text: String = userText.text.toString()
        val res = text.intOrString()
        val check : Boolean = " " in text

    /*
        else {
            intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "${text}"))
            startActivity(intent)
        }*/

        if(browserButton.isChecked && text.isEmpty()){
            val url = "https://istu.edu"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        else if(geolocationButton.isChecked && text.isEmpty()){
            val i = Intent(Intent.ACTION_VIEW)
            val uri = "geo:52.262697,104.261906"
            i.data = Uri.parse(uri)
            startActivity(i)
        }
      else  if(phoneButton.isChecked && text.isEmpty()){
            intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:11111"))
            startActivity(intent)
        }
        else if(!res && !check){
            val url = "https://${text}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        else{
            val i = Intent(Intent.ACTION_VIEW)
            val uri = "geo:${text}"
            i.data = Uri.parse(uri)
            startActivity(i)
        }


        /*   else{
               val i = Intent(Intent.ACTION_VIEW)
               val uri = "geo:${text}"
               i.data = Uri.parse(uri)
               startActivity(i)
           }*/
    }

    fun String.intOrString(): Boolean {
        val v = toIntOrNull()
        return when(v) {
            null -> false
            else -> true
        }
    }

    private fun init(){
        clickButton = findViewById(R.id.magicButton)
        userText = findViewById(R.id.userText)
        phoneButton = findViewById(R.id.phoneButton)
        browserButton = findViewById(R.id.browserButton)
        geolocationButton = findViewById(R.id.geolocationButton)
    }
}