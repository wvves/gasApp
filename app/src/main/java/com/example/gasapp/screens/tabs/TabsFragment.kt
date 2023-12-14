package com.example.gasapp.screens.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.gasapp.R
import com.example.gasapp.databinding.FragmentTabsBinding

class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTabsBinding.bind(view)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }
}