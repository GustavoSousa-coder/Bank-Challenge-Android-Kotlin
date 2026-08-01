package com.example.bankchellengemobileapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.ui.card.VirtualCardManagementActivity
import com.example.bankchellengemobileapp.ui.pix.PaymentPixActivity
import com.example.bankchellengemobileapp.ui.pix.PixKeyManagementActivity
import com.example.bankchellengemobileapp.ui.statistics.StatisticsActivity
import com.example.bankchellengemobileapp.ui.transactions.TransactionsActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnTransferir: Button = findViewById(R.id.btnTransferir)
        btnTransferir.setOnClickListener {
            startActivity(Intent(this, TransactionsActivity::class.java))
        }

        val btnMinhasChaves: Button = findViewById(R.id.btnMinhasChaves)
        btnMinhasChaves.setOnClickListener {
            startActivity(Intent(this, PixKeyManagementActivity::class.java))
        }

        val btnPagarComPix: Button = findViewById(R.id.btnPagarComPix)
        btnPagarComPix.setOnClickListener {
            startActivity(Intent(this, PaymentPixActivity::class.java))
        }

        val btnMinhasEstatisticas: Button = findViewById(R.id.btnMinhasEstatisticas)
        btnMinhasEstatisticas.setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
        }

        val btnMeusCartoes: Button = findViewById(R.id.btnMeusCartoes)
        btnMeusCartoes.setOnClickListener {
            startActivity(Intent(this, VirtualCardManagementActivity::class.java))
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val btnMenu: ImageButton = findViewById(R.id.btnMenu)

        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

    }
}