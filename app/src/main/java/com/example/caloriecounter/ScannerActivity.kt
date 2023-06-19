package com.example.caloriecounter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class ScannerActivity : ComponentActivity() {
    lateinit var button: Button
    lateinit var textview : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        button = findViewById(R.id.scanner)
        textview = findViewById(R.id.text)
        button.setOnClickListener{
            var intentIntegrator = IntentIntegrator(this)
            intentIntegrator.setOrientationLocked(true)
            intentIntegrator.setPrompt("Scan a QR code")
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            intentIntegrator.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var intentResult : IntentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(intentResult != null){
            var content = intentResult.contents
            if (content != null){
                textview.text = intentResult.contents
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)

        }
    }
}