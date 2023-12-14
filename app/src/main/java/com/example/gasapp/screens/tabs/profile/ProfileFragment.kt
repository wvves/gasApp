package com.example.gasapp.screens.tabs.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.navOptions
import com.example.gasapp.R
import com.example.gasapp.Repositories
import com.example.gasapp.databinding.FragmentProfileBinding
import com.example.gasapp.model.accounts.entites.Account
import com.example.gasapp.utils.findTopNavController
import com.example.gasapp.utils.observeEvent
import com.example.gasapp.utils.viewModelCreator
import java.text.SimpleDateFormat
import java.util.Date

class ProfileFragment: Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModelCreator { ProfileViewModel(Repositories.accountsRepository) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        binding.editProfileButton.setOnClickListener { onEditProfileButtonPressed() }
        binding.logoutButton.setOnClickListener { onCreateOrderButtonPressed() }

        observeAccountDetails()
        observeRestartAppFromLoginScreenEvent()
    }

    private fun observeAccountDetails() {
        val formatter = SimpleDateFormat.getDateTimeInstance()
        viewModel.account.observe(viewLifecycleOwner) { account ->
            if (account == null) return@observe
            binding.emailTextView.text = account.email
            binding.usernameTextView.text = account.username
            binding.createdAtTextView.text = if (account.createdAt == Account.UNKNOWN_CREATED_AT)
                getString(R.string.placeholder)
            else
                formatter.format(Date(account.createdAt))
        }
    }

    private fun observeRestartAppFromLoginScreenEvent() {
        viewModel.restartWithSignInEvent.observeEvent(viewLifecycleOwner) {
            findTopNavController().navigate(R.id.signInFragment, null, navOptions {
                popUpTo(R.id.tabsFragment) {
                    inclusive = true
                }
            })
        }
    }

    private fun onLogoutButtonPressed() {
        viewModel.logout()
    }

    private fun onEditProfileButtonPressed() {
        findTopNavController().navigate(R.id.editProfileFragment)
    }

    private fun onCreateOrderButtonPressed() {
        findTopNavController().navigate(R.id.createOrderFragment)
    }


}