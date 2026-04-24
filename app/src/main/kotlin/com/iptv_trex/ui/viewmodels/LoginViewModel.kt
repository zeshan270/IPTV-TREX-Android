package com.iptv_trex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptv_trex.data.api.RetrofitClient
import com.iptv_trex.data.api.XtreamClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class LoginUiState(
    val serverUrl: String = "",
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false,
    val xtreamClient: XtreamClient? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateServerUrl(url: String) {
        _uiState.value = _uiState.value.copy(serverUrl = url)
    }

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(username = username)
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        val state = _uiState.value

        // Validation
        if (state.serverUrl.isBlank()) {
            _uiState.value = state.copy(error = "Server URL is required")
            return
        }
        if (state.username.isBlank()) {
            _uiState.value = state.copy(error = "Username is required")
            return
        }
        if (state.password.isBlank()) {
            _uiState.value = state.copy(error = "Password is required")
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = state.copy(isLoading = true, error = null)

                // Create XtreamClient
                val client = XtreamClient(
                    serverUrl = state.serverUrl,
                    username = state.username,
                    password = state.password
                )

                // Test connection by fetching user info
                val userInfo = client.getUserInfo()
                Timber.d("Login successful")

                _uiState.value = state.copy(
                    isLoading = false,
                    isAuthenticated = true,
                    error = null,
                    xtreamClient = client
                )
            } catch (e: Exception) {
                Timber.e(e, "Login failed")
                _uiState.value = state.copy(
                    isLoading = false,
                    error = "Login failed: ${e.message}"
                )
            }
        }
    }

    fun logout() {
        _uiState.value = LoginUiState()
        RetrofitClient.resetApiService()
    }
}
