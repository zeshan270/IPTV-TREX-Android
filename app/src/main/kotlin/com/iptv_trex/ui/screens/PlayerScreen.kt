package com.iptv_trex.ui.screens

import android.content.pm.ActivityInfo
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.iptv_trex.ui.viewmodels.PlayerViewModel
import timber.log.Timber

@Composable
fun PlayerScreen(
    streamUrl: String,
    streamName: String,
    navController: NavHostController,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setStreamData(streamUrl, streamName)
    }

    var exoPlayer: ExoPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer?.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (uiState.isLoading && exoPlayer == null) {
            // Loading State
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Loading stream...",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else if (uiState.error != null) {
            // Error State
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Error",
                    modifier = Modifier.size(64.dp),
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Error loading stream",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = uiState.error ?: "Unknown error",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Go Back")
                }
            }
        } else {
            // Player View
            AndroidView(
                factory = { context ->
                    val player = ExoPlayer.Builder(context).build()
                    exoPlayer = player

                    val mediaItem = viewModel.loadMediaItem()
                    player.setMediaItem(mediaItem)
                    player.prepare()
                    player.play()

                    // Setup player listeners
                    val playerListener = object : androidx.media3.common.Player.Listener {
                        override fun onPlaybackStateChanged(state: Int) {
                            viewModel.onPlaybackStateChanged(state)
                        }

                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            viewModel.onIsPlayingChanged(isPlaying)
                        }

                        override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                            Timber.e(error, "Player error: ${error.message}")
                            viewModel.onPlayerError(error.message ?: "Unknown error")
                        }
                    }
                    player.addListener(playerListener)

                    PlayerView(context).apply {
                        this.player = player
                        controllerShowTimeoutMs = 5000
                        controllerHideTimeoutMs = 5000
                        useController = true
                        showBufferingSpinner = true
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Top Bar with Close Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.TopStart),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = streamName,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Fullscreen Button
            IconButton(
                onClick = { viewModel.toggleFullscreen() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    if (uiState.isFullscreen) Icons.Default.FullscreenExit else Icons.Default.Fullscreen,
                    contentDescription = "Fullscreen",
                    tint = Color.White
                )
            }
        }
    }
}
