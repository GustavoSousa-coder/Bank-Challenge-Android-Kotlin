package com.example.bankchellengemobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.ui.card.VirtualCardManagementActivity
import com.example.bankchellengemobileapp.ui.pix.PaymentPixActivity
import com.example.bankchellengemobileapp.ui.pix.PixKeyManagementActivity
import com.example.bankchellengemobileapp.ui.pix.RegisterPixKeyActivity
import com.example.bankchellengemobileapp.ui.transactions.TransactionsActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnTransfer: Button = findViewById(R.id.btnTransferir)
        btnTransfer.setOnClickListener {
            startActivity(Intent(this, TransactionsActivity::class.java))
        }

        val btnPix: Button = findViewById(R.id.btnPix)
        btnPix.setOnClickListener {
            startActivity(Intent(this, PixKeyManagementActivity::class.java))
        }

        val btnAddressKey: Button = findViewById(R.id.btnAddressKey)
        btnAddressKey.setOnClickListener {
            startActivity(Intent(this, RegisterPixKeyActivity::class.java))
        }

        val btnCardVirtual: Button = findViewById(R.id.btnCardVirtual)
        btnCardVirtual.setOnClickListener {



            startActivity(Intent(this, VirtualCardManagementActivity::class.java))
        }

    }
}