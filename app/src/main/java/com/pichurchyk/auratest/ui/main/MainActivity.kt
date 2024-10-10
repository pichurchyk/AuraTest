package com.pichurchyk.auratest.ui.main

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pichurchyk.auratest.databinding.ActivityMainBinding
import com.pichurchyk.auratest.ui.BootListAdapter
import com.pichurchyk.auratest.ui.base.BaseActivity
import com.pichurchyk.auratest.ui.ext.setVisibility
import com.pichurchyk.auratest.utils.NotificationWorker
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    private var bootsAdapter: BootListAdapter = BootListAdapter()

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        NotificationWorker.scheduleNotificationWorker(this)

        bootsAdapter = BootListAdapter()

        with(binding) {
            rvBoots.apply {
                layoutManager = LinearLayoutManager(this@MainActivity).apply {
                    orientation = RecyclerView.VERTICAL
                }
                adapter = bootsAdapter
            }
        }

        lifecycleScope.launch {
            viewModel.bootsHistory.collect { boots ->
                binding.tvEmptyBoots.setVisibility(boots.isEmpty())
                binding.rvBoots.setVisibility(boots.isNotEmpty())

                bootsAdapter.submitList(boots)
            }
        }
    }
}