package com.example.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mockk
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpView()
        setListeners()
        handleNextPhrase()
    }

    private fun setUpView() {
        handleUserName()
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.purple_700))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.purple_700))
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.purple_700))
    }

    private fun handleUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textHello.text = "Olá, $userName"
    }

    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }


    override fun onClick(view: View?) {
        if (view!!.id == R.id.button_new_phrase) {
            handleNextPhrase()
        } else {
            handleFilter(view.id)
        }
    }

    private fun handleNextPhrase() {
        binding.textPhrase.text = Mockk().getPhrase(categoryId)
    }

    fun handleFilter(id: Int) {
        when (id) {
            R.id.image_all -> {
                setUpView()
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_sunny -> {
                setUpView()
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
            R.id.image_happy -> {
                setUpView()
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }

        }
    }
}