package com.example.drawerbottomnavapplication.activity.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.drawerbottomnavapplication.R
import com.example.drawerbottomnavapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment

        navController = navHostFragment.navController
        bottomNavigationView = binding.bottomNavigation
        navigationView = binding.navigationView
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        NavigationUI.setupWithNavController(navigationView, navController!!)
        NavigationUI.setupWithNavController(bottomNavigationView, navController!!)


        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {


                R.id.mnAddToCart -> {
                    showBottomNavigation()
                    unlockDrawer()
                    showToolbar()
                }

                R.id.mnProduct -> {
                    showBottomNavigation()
                    unlockDrawer()
                    showToolbar()
                }

                R.id.mnTimeSlot -> {
                    showBottomNavigation()
                    unlockDrawer()
                    showToolbar()
                }


                else -> {
                    hideBottomNavigation()
                }
            }
        }


        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mnCamera -> {
                    navController?.navigate(R.id.mnCamera)
                    binding.drawerLayout.close()
                }

                R.id.mnGallery -> {
                    navController?.navigate(R.id.mnGallery)
                    binding.drawerLayout.close()
                }

                R.id.mnImage -> {
                    navController?.navigate(R.id.mnImage)
                    binding.drawerLayout.close()
                }

                R.id.mnAddToCart -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    navController!!.navigate(R.id.mnAddToCart)
                    binding.drawerLayout.close()
                }

                R.id.mnProduct -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    navController!!.navigate(R.id.mnProduct)
                    binding.drawerLayout.close()
                }

                R.id.mnTimeSlot -> {
                    navController!!.navigate(R.id.mnTimeSlot)
                    binding.drawerLayout.close()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                }
            }
            true
        }


        initSupportActionBar()
        initDrawerToggle()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.drawerLayout.isOpen) {
                    binding.drawerLayout.close()
                } else {
                    when (navController!!.currentDestination?.id) {
                        R.id.mnGallery, R.id.mnImage, R.id.mnCamera,
                        R.id.documentScannerFragment,
                        R.id.homeFragment,
                        R.id.accountFragment2,
                        R.id.viewPager2Fragment, R.id.stepViewFragment, R.id.tabLayoutFragment, R.id.onBoardingFragment, R.id.loginFragment -> {
                            navController?.popBackStack()
                        }

                        R.id.mnProduct, R.id.mnTimeSlot -> {
                            navController?.navigate(R.id.mnAddToCart)
                            navController?.popBackStack(R.id.mnAddToCart, false)
                        }

                        R.id.mnAddToCart -> {
                            finish()
                        }

                        else -> {
                            isEnabled=false
                            onBackPressedDispatcher.onBackPressed()
                        }
                    }
                }
            }
        }


        onBackPressedDispatcher.addCallback(this, callback)


    }

    private fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.inside_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnGallery -> {
                navController?.navigate(R.id.mnGallery)

            }

            R.id.mnImage -> {
                navController?.navigate(R.id.mnImage)

            }

            R.id.mnCamera -> {
                navController?.navigate(R.id.mnCamera)

            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun initSupportActionBar() {
        setSupportActionBar(binding.tbToolBar)
        binding.tbToolBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        unlockDrawer()


    }

    fun lockDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }


    fun unlockDrawer() {
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun initDrawerToggle() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        unlockDrawer()

    }

    private fun hideToolbar() {
        supportActionBar?.hide()
    }

    private fun showToolbar() {
        supportActionBar?.show()
    }


    /*val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            when (navHostFragment.childFragmentManager.fragments[0]) {
                is ProductFragment, is TimeSlotFragment -> {
                    if (binding.drawerLayout.isOpen) binding.drawerLayout.close() else {
                        navController!!.navigate(R.id.mnAddToCart)
                        navController!!.clearBackStack(R.id.mnAddToCart)
                    }
                }

                is AddToCartFragment -> {
                    if (binding.drawerLayout.isOpen) binding.drawerLayout.close() else finish()

                }
            }
        }
    }*/



}