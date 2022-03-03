package com.example.intentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
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
        if(!browserButton.isChecked && !phoneButton.isChecked && !geolocationButton.isChecked && text.isEmpty())
        {
            Toast.makeText(applicationContext, "Строка пустая, RadioButton не выбран:(", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }

        if(browserButton.isChecked && text.isEmpty()){
            val url = "https://istu.edu"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            cleanAll()
        }
        else if(geolocationButton.isChecked && text.isEmpty()){
            val i = Intent(Intent.ACTION_VIEW)
            val uri = "geo:52.262697,104.261906"
            i.data = Uri.parse(uri)
            startActivity(i)
            cleanAll()
        }
        else  if(phoneButton.isChecked && text.isEmpty()){
            intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+11111"))
            startActivity(intent)
            cleanAll()
        }
        else if(res){
            intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "${text}"))
            startActivity(intent)
            cleanAll()
        }
        else if(!res && !check){
            val url = "https://${text}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            cleanAll()
        }
        else{
            val i = Intent(Intent.ACTION_VIEW)
            val uri = "geo:${text}"
            i.data = Uri.parse(uri)
            startActivity(i)
            cleanAll()
        }
    }

    fun String.intOrString(): Boolean {
        val v = toIntOrNull()
        return when(v) {
            null -> false
            else -> true
        }
    }

    private fun cleanAll(){
        geolocationButton.isChecked = false
        phoneButton.isChecked = false
        browserButton.isChecked = false
        userText.setText("")
    }

    private fun init(){
        clickButton = findViewById(R.id.magicButton)
        userText = findViewById(R.id.userText)
        phoneButton = findViewById(R.id.phoneButton)
        browserButton = findViewById(R.id.browserButton)
        geolocationButton = findViewById(R.id.geolocationButton)
    }
}