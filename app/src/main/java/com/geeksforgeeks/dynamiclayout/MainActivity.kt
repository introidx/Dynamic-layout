package com.geeksforgeeks.dynamiclayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.geeksforgeeks.dynamiclayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var languageList = ArrayList<Language>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            addNewView()
        }

        binding.buttonSubmitList.setOnClickListener {
            saveData()
        }

        binding.buttonShowList.setOnClickListener {
            showData()
        }

    }

    private fun showData() {
        val count = binding.parentLinearLayout.childCount
        for (i in 0 until count) {
            Toast.makeText(this, "Language at $i is ${languageList[i].name} and experience is ${languageList[i].exp} ", Toast.LENGTH_SHORT).show()

        }
    }


    private fun addNewView() {
        val inflater = LayoutInflater.from(this).inflate(R.layout.row_add_lamguage, null)
        binding.parentLinearLayout.addView(inflater, binding.parentLinearLayout.childCount)

    }

    private fun saveData() {
        languageList.clear()
        val count = binding.parentLinearLayout.childCount
        var v: View? = null

        for (i in 0 until count) {
            v = binding.parentLinearLayout.getChildAt(i)

            val languageName: EditText = v.findViewById(R.id.et_name)
            val experience: Spinner = v.findViewById(R.id.exp_spinner)

            val language = Language()
            language.name = languageName.text.toString()
            language.exp = experience.selectedItem as String

            languageList.add(language)


        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}