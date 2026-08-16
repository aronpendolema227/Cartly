package com.ap.cartly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ap.cartly.data.repository.UserRepository
import com.ap.cartly.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val currentUserId = "U003"

    val userProfile: StateFlow<User?> =
        userRepository.getUserProfile(currentUserId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    fun updateProfile(user: User) {
        viewModelScope.launch {
            userRepository.update(user)
        }
    }
}

