package com.mandrykevich.testtask

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mandrykevich.testtask.Adapter.OnVacancyClickListener
import com.mandrykevich.testtask.Adapter.Vacancy
import com.mandrykevich.testtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnVacancyClickListener {

    lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = ViewModelProvider(this, VMfactory(this))
            .get(ViewModel::class.java)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.navigation_frame, EntranceFragment())
            .commit()

        viewModel.messageForActivity.observe(this) { message ->
            val fragment = when(message){
                "ShowAcceptFragment" -> GmailAcceptFragment()
                "GmailAcceptFragment" -> VacancyFragment()
                "ShowVacancyFragment" -> AboutVacancyFragment()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.navigation_frame, it)
                    .commit()
            }
        }

        binding.mainBottomNav.setOnItemSelectedListener { item ->
            val fragment = when(item.itemId) {
                R.id.item_search -> VacancyFragment()
                R.id.item_favorite -> FavoriteFragment()
                R.id.item_feedback -> EmptyFragment()
                R.id.item_massenger -> EmptyFragment()
                R.id.item_profile -> EmptyFragment()
                else -> null
            }

            fragment?.let {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.navigation_frame)
                if (currentFragment == null || !currentFragment::class.java.isInstance(fragment)) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.navigation_frame, fragment)
                        .commit()
                }
            }
            true
        }
    }

    override fun onVacancyClick(vacancy: Vacancy) {
        val fragment = AboutVacancyFragment.newInstance(vacancy)
        supportFragmentManager.beginTransaction()
            .replace(R.id.navigation_frame, fragment)
            .addToBackStack(null)
            .commit()
    }
}