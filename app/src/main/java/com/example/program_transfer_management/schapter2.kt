package com.example.program_transfer_management

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.barteksc.pdfviewer.PDFView

class schapter2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schapter2)
        var pdfview=findViewById<PDFView>(R.id.pdfview)
        pdfview.fromAsset("schapter2.pdf").load()
    }
}