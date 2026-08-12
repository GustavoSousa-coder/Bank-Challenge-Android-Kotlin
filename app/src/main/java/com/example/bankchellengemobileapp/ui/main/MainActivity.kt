package com.example.bankchellengemobileapp.ui.main

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bankchellengemobileapp.R
import com.example.bankchellengemobileapp.ui.card.CardFragment
import com.example.bankchellengemobileapp.ui.home.HomeFragment
import com.example.bankchellengemobileapp.ui.profile.ProfileFragment
import com.example.bankchellengemobileapp.ui.statement.StatementFragment

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val statementFragment = StatementFragment()
    private val cardFragment = CardFragment()
    private val profileFragment = ProfileFragment()

    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHome: LinearLayout = findViewById(R.id.nav_home)
        val navStatement: LinearLayout = findViewById(R.id.nav_statement)
        val navCards: LinearLayout = findViewById(R.id.nav_cards)
        val navProfile: LinearLayout = findViewById(R.id.nav_profile)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, profileFragment, "profile").hide(profileFragment)
                .add(R.id.fragment_container, cardFragment, "cards").hide(cardFragment)
                .add(R.id.fragment_container, statementFragment, "statement").hide(statementFragment)
                .add(R.id.fragment_container, homeFragment, "home")
                .commit()

            updateSelected(R.id.nav_home)
        }

        navHome.setOnClickListener {
            updateSelected(R.id.nav_home)
            trocarFragment(homeFragment)
        }

        navStatement.setOnClickListener {
            updateSelected(R.id.nav_statement)
            trocarFragment(statementFragment)
        }

        navCards.setOnClickListener {
            updateSelected(R.id.nav_cards)
            trocarFragment(cardFragment)
        }

        navProfile.setOnClickListener {
            updateSelected(R.id.nav_profile)
            trocarFragment(profileFragment)
        }
    }

    private fun trocarFragment(fragment: Fragment) {
        if (fragment == activeFragment) return

        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()

        activeFragment = fragment
    }

    private fun updateSelected(idSelected: Int) {
        val ids = listOf(R.id.nav_home, R.id.nav_statement, R.id.nav_cards, R.id.nav_profile)
        ids.forEach { id ->
            findViewById<LinearLayout>(id).isSelected = (id == idSelected)
        }
    }
}