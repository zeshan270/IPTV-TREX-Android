package com.iptv_trex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iptv_trex.ui.theme.DarkBg
import com.iptv_trex.ui.theme.Purple500

@Composable
fun HomeScreen(
    navController: NavHostController,
    onLogout: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "IPTV TREX",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Purple500
                )
                IconButton(onClick = onLogout) {
                    Icon(
                        Icons.Default.Logout,
                        contentDescription = "Logout",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Divider(
                color = MaterialTheme.colorScheme.surfaceVariant,
                thickness = 1.dp
            )

            // Main Menu
            Text(
                text = "Browse",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )

            MenuCard(
                icon = Icons.Default.LiveTv,
                title = "Live TV",
                description = "Watch live channels",
                onClick = { navController.navigate(Route.Live.route) }
            )

            MenuCard(
                icon = Icons.Default.Movie,
                title = "Movies",
                description = "Browse movies collection",
                onClick = { navController.navigate(Route.Movies.route) }
            )

            MenuCard(
                icon = Icons.Default.Tv,
                title = "Series",
                description = "Watch TV series",
                onClick = { navController.navigate(Route.Series.route) }
            )

            MenuCard(
                icon = Icons.Default.Favorite,
                title = "Favorites",
                description = "Your favorite items",
                onClick = { navController.navigate(Route.Favorites.route) }
            )

            // Library
            Text(
                text = "Library",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )

            MenuCard(
                icon = Icons.Default.Favorite,
                title = "Watchlist",
                description = "Your watch later items",
                onClick = { navController.navigate(Route.Watchlist.route) }
            )

            // Settings
            Text(
                text = "Account",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 16.dp)
            )

            MenuCard(
                icon = Icons.Default.Settings,
                title = "Settings",
                description = "Preferences and settings",
                onClick = { navController.navigate(Route.Settings.route) }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun MenuCard(
    icon: androidx.compose.material.icons.materialIcon,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = Purple500
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Import for sealed class
import com.iptv_trex.ui.navigation.Route
