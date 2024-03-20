package com.example.latestcomponentpractice.Screens

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.latestcomponentpractice.R
import com.example.latestcomponentpractice.databinding.ActivityDrawerExampleBinding
import com.example.latestcomponentpractice.fragments.HomeFragment
import com.example.latestcomponentpractice.fragments.Page1Fragment

class DrawerActivityExample : AppCompatActivity() {

    private lateinit var binding : ActivityDrawerExampleBinding
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDrawerExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this@DrawerActivityExample,
            binding.drawerLayoutId,
            R.string.open,
            R.string.close)

        binding.drawerLayoutId.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationViewId.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.item1 -> {
                    replaceFragment(HomeFragment())
                }
                else -> {
                    replaceFragment(Page1Fragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.fragmentDrawerLayout.id, fragment)
        transaction.commit()

        binding.drawerLayoutId.closeDrawers()
        title = "Mohit"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}