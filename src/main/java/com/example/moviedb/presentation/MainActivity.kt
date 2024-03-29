package com.example.moviedb.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviedb.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.movieListFragment,
                R.id.favoriteFragment,
                R.id.profileFragment,
                R.id.cinemaFragment,
                R.id.movieDetailsFragment,
                R.id.detailFavoriteFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

//    override fun onBackPressed() {
//            val builder = AlertDialog.Builder(this)
//            builder.setTitle("Logout?")
//            builder.setPositiveButton("Yes",
//                DialogInterface.OnClickListener { dialogInterface, i ->
//                    val intent = Intent(
//                        this,
//                        LoginActivity::class.java
//                    )
//                    this.startActivity(intent)
//                    this.finish()
//                })
//            builder.setNegativeButton("No",
//                DialogInterface.OnClickListener { dialogInterface, i -> })
//            val ad = builder.create()
//            ad.show()
//    }
}
