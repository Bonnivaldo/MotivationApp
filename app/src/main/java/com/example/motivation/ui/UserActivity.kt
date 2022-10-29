package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.UserActivityBinding

class UserActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: UserActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSaveName.setOnClickListener(this)
        supportActionBar?.hide()
        verifyUserName()
    }

    private fun verifyUserName() {
        val userName = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (userName != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View?) {
       if (view!!.id == R.id.button_save_name){
            handleName()
       }
    }

    private fun handleName() {
        val userName = binding.editTakeName.text.toString()
        if (userName != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, userName)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, R.string.validation_mandatory_name,Toast.LENGTH_SHORT).show()
        }
    }


}