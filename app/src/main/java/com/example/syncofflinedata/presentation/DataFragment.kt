package com.example.syncofflinedata.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filedownloader.databinding.FragmentDataBinding
import com.example.syncofflinedata.DataEntity
import com.example.syncofflinedata.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DataFragment : Fragment() {

    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    private val dataViewModel: DataViewModel by viewModels()
    private lateinit var dataAdapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView adapter
        setupRecyclerView()

        // Collect and observe StateFlow from ViewModel
        observeUnsyncedData()

        // Handle button click to insert data into the database
        binding.buttonSync.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun setupRecyclerView() {
        dataAdapter = DataAdapter()
        binding.recyclerViewData.apply {
            adapter = dataAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeUnsyncedData() {
        // Use the lifecycleScope to collect StateFlow
        viewLifecycleOwner.lifecycleScope.launch {
            dataViewModel.unsyncedData.collectLatest { dataList ->
                // Update the RecyclerView with the new data
                dataAdapter.submitList(dataList)
            }
        }
    }

    private fun insertDataToDatabase() {
        // Create a new data entity to insert into the Room database
        val newData = DataEntity(
           // title = "Sample Title",  // You can get this from user input if required
            content = "Sample Content" // You can get this from user input if required
        )

        // Call the insert function in the ViewModel
        dataViewModel.insertData(newData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}