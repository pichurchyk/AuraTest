package com.pichurchyk.auratest.ui

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pichurchyk.auratest.data.database.BootDatabase
import com.pichurchyk.auratest.databinding.ActivityMainBinding
import com.pichurchyk.auratest.ui.base.BaseActivity
import com.pichurchyk.auratest.ui.ext.setVisibility
import com.pichurchyk.auratest.utils.NotificationWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

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

        setupDatabaseListener()
    }

    private fun setupDatabaseListener() {
        val database = BootDatabase.getDatabase(this)
        val dao = database.bootDao()

        lifecycleScope.launch(Dispatchers.IO) {
            dao.getAllBoots().let { boots ->
                binding.tvEmptyBoots.setVisibility(boots.isEmpty())
                binding.rvBoots.setVisibility(boots.isNotEmpty())

                bootsAdapter.submitList(boots)
            }
        }
    }
}