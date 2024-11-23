package br.com.jef.agendav5.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jef.agendav5.R
import br.com.jef.agendav5.databinding.ActivityMainBinding
import br.com.jef.agendav5.ui.utils.Constants
import br.com.jef.agendav5.ui.utils.listener.AgendaListener

class MainActivity : AppCompatActivity(), AgendaListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.statusBarColor = resources.getColor(R.color.statusBar)
        setContentView(binding.root)
        dropDownMenu()
        saveUser()


    }


    private fun dropDownMenu() {
        val items = listOf("Cellphone", "Work", "House")
        val autoComplete: AutoCompleteTextView = findViewById(R.id.auto_complete)
        val adapter = ArrayAdapter(this, R.layout.list_tel_options, items)
        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val itemSelected = adapterView.getItemAtPosition(i)
                Toast.makeText(this, "Item: $itemSelected", Toast.LENGTH_SHORT).show()
            }
    }

    private fun initRecycler(
        nameList: MutableList<String>,
        telephoneList: MutableList<String>,
        genderList: MutableList<String>,
        phoneTypeList: MutableList<String>
    ) {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = Adapter(
            nameList.asReversed(),
            telephoneList.asReversed(),
            genderList.asReversed(),
            phoneTypeList.asReversed(),
            this
        )
    }

    private fun saveUser() {
        val nameList = mutableListOf<String>()
        val telephoneList = mutableListOf<String>()
        val genderList = mutableListOf<String>()
        val phoneTypeList = mutableListOf<String>()

        binding.btnSave.setOnClickListener() {
            val name = binding.edtNameMain.text.toString()
            val telephone = binding.edtTelMain.text.toString()
            val phoneType = binding.autoComplete.text.toString()
            if (binding.edtNameMain.text?.isEmpty() == true) {
                binding.edtNameMain.error = getString(R.string.empty_field)
            } else if (binding.edtTelMain.text?.isEmpty() == true) {
                binding.edtTelMain.error = getString(R.string.empty_field)
            } else {
                nameList.add(name)
                phoneTypeList.add(phoneType)
                telephoneList.add(telephone)
                genderList.add(getGender())
                initRecycler(nameList, telephoneList, genderList, phoneTypeList)
                clearFields()
            }
        }
    }

    private fun clearFields() {
        binding.edtNameMain.text?.clear()
        binding.autoComplete.text?.clear()
        binding.edtTelMain.text?.clear()
        binding.radioGroup.check(-1)
        binding.edtNameMain.requestFocus()

    }

    private fun getGender(): String {
        val rdGroup = binding.radioGroup
        val id: Int = rdGroup.checkedRadioButtonId
        if (id == -1) {

        } else {
            val btn = findViewById<RadioButton>(id)
            Toast.makeText(this, btn.text.toString(), Toast.LENGTH_SHORT).show()
            return btn.text.toString()
        }
        return ""
    }

    override fun onClick(name: String, phoneType: String, telephone: String, gender: String) {
        val intent = Intent(this, Resume::class.java)
        intent.putExtra(Constants.NAME, name)
        intent.putExtra(Constants.PHONETYPE, phoneType)
        intent.putExtra(Constants.TELEPHONE, telephone)
        intent.putExtra(Constants.GENDER, gender)
        startActivity(intent)
    }


}