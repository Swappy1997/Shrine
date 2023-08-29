package com.example.shrine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmhilt.viewmodel.ItemViewModel
import com.example.shrine.adapter.ItemAdapter
import com.example.shrine.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var viewModel: ItemViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        viewModel.fetchShrineItems()
        setupRecyclerView()
        loadItems()
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter()
        binding.recyclerView.apply {
            layoutManager =
                GridLayoutManager(this@MainActivity, 2) // Set grid layout with 2 columns
            adapter = itemAdapter
        }
    }

    private fun loadItems() {
        viewModel.shrineItemsLiveData.observe(this) { shrineList ->
            // Update RecyclerView adapter with the new list of items
            itemAdapter.submitList(shrineList.items)
            Log.d("DATA", "observeItems: ${shrineList.items.size} items observed")
        }
    }
}
