package br.com.jef.agendav5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jef.agendav5.databinding.ActivityResumeBinding
import br.com.jef.agendav5.ui.utils.Constants

class Resume : AppCompatActivity() {
    private lateinit var binding: ActivityResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUser()

    }

    private fun getUser() {
        val name = intent.getStringExtra(Constants.NAME)
        val phoneType = intent.getStringExtra(Constants.PHONETYPE)
        val telephone = intent.getStringExtra(Constants.TELEPHONE)
        val gender = intent.getStringExtra(Constants.GENDER)

        binding.txtNameResume.text = name
        binding.txtPhoneType.text = phoneType
        binding.txtTelResume.text = telephone
        binding.txtGenderResume.text = gender

    }
}