package com.iptv_trex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.iptv_trex.data.api.XtreamClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

data class PlayerUiState(
    val streamUrl: String = "",
    val streamName: String = "",
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0,
    val duration: Long = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFullscreen: Boolean = false
)

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    fun setStreamData(
        streamUrl: String,
        streamName: String,
        xtreamClient: XtreamClient? = null
    ) {
        Timber.d("Setting stream: $streamName, URL: $streamUrl")
        _uiState.value = _uiState.value.copy(
            streamUrl = streamUrl,
            streamName = streamName,
            isLoading = true,
            error = null
        )
    }

    fun loadMediaItem(): MediaItem {
        val state = _uiState.value
        return MediaItem.fromUri(state.streamUrl)
    }

    fun onPlaybackStateChanged(state: Int) {
        val isPlaying = state == Player.STATE_READY
        _uiState.value = _uiState.value.copy(isPlaying = isPlaying, isLoading = false)
        Timber.d("Playback state: $state, isPlaying: $isPlaying")
    }

    fun onIsPlayingChanged(isPlaying: Boolean) {
        _uiState.value = _uiState.value.copy(isPlaying = isPlaying)
    }

    fun onPositionChanged(position: Long, duration: Long) {
        _uiState.value = _uiState.value.copy(
            currentPosition = position,
            duration = duration
        )
    }

    fun onPlayerError(error: String) {
        Timber.e("Player error: $error")
        _uiState.value = _uiState.value.copy(
            error = error,
            isLoading = false
        )
    }

    fun toggleFullscreen() {
        _uiState.value = _uiState.value.copy(
            isFullscreen = !_uiState.value.isFullscreen
        )
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
