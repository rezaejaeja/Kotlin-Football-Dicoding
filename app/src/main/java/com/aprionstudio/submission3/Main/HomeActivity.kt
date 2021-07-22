package com.aprionstudio.submission3.Main

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.aprionstudio.submission3.MainView
import com.aprionstudio.submission3.R
import com.aprionstudio.submission3.R.id.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // testing eight PR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navigation_past -> {
                   loadEventFragment(savedInstanceState)
                }
                navigation_next -> {
                    loadNextEventFragment(savedInstanceState)
                }
                navigation_favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = navigation_past

    }

    private fun loadEventFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, EventFragment(), EventFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadNextEventFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextEventFragment(), EventFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }


}