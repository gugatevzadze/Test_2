package com.example.test_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val initialContainer: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveButton()
        }

        binding.outputBtn.setOnClickListener {
            outputButton()
        }
    }

    private fun saveButton() {
        //getting input from edit text and adding it to the list
        val input = binding.editTextField.text.toString().trim()
        if (input.isNotEmpty()) {
            initialContainer.add(input)
            binding.editTextField.text?.clear()
        }
    }

    private fun groupFunction(words: List<String>): Map<String, List<String>> {
        //finding anagrams in a list of words by sorting their letters and grouping words with the same sorted letters
        val sortedGroups = mutableMapOf<String, MutableList<String>>()

        for (word in words) {
            val sortedWord = word.toCharArray().sorted().joinToString("")
            if (!sortedGroups.containsKey(sortedWord)) {
                //
                sortedGroups[sortedWord] = mutableListOf()
            }
            sortedGroups[sortedWord]?.add(word)
        }
        return sortedGroups
    }

    private fun outputButton() {
        //button for grouping the words in initialContainer into anagram containers
        val groupedAnagrams = groupFunction(initialContainer)

        val groupCount = groupedAnagrams.size
        binding.outputTextView.text = "Anagrams count $groupCount"
    }
}