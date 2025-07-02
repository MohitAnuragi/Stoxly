package com.example.stoxly.topmovers

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stoxly.api.StockItem
import com.example.stoxly.components.AppColors
import com.example.stoxly.components.HorizontalStockSection
import com.example.stoxly.components.StockCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMoversScreen(
    viewModel: TopMoversViewModel = viewModel(),
    onViewAllClick: (String) -> Unit,
    onStockClick: (String, String?, String?) -> Unit,
    onSearchClick: () -> Unit,
    onWatchlistClick: () -> Unit
) {
    val state = viewModel.topMoversState
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage
    val isDummyData = viewModel.isDummyData

    LaunchedEffect(Unit) {
        viewModel.fetchTopMovers("LKT1YMFIN0W0HD2B") // Use invalid key for testing
    }

    Scaffold(
        containerColor = AppColors.Background,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(AppColors.GradientStart, AppColors.GradientEnd)
                        )
                    )
            ) {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                "STOXLY",
                                color = AppColors.TextPrimary,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp
                                )
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(onClick = onWatchlistClick)
                            {
                                Icon(
                                    imageVector = Icons.Default.List,
                                    contentDescription = "watchlist",
                                    tint = AppColors.AccentGreen
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = AppColors.TextPrimary
                    ),
                    modifier = Modifier

                )
            }
        },
        bottomBar = {
            var selectedTab by remember { mutableStateOf("top_movers") }
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selected ->
                    selectedTab = selected
                    if (selected == "watchlist") {
                        onWatchlistClick()
                    }
                    // If needed, handle other tabs here.
                }
            )
        }

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.Background)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 24.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(AppColors.CardBackground)
                    .clickable(onClick = onSearchClick)
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search for Stock",
                        tint = AppColors.AccentGreen,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Text(
                        text = "Search for Stock",
                        color = AppColors.TextSecondary,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = AppColors.AccentGreen,
                            strokeWidth = 4.dp
                        )
                    }
                }

                state != null -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.animateContentSize(tween(300))
                    ) {
                        item {
                            if (isDummyData && error != null) {
                                Text(
                                    text = "Showing sample data due to error: $error",
                                    color = Color.Yellow,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .background(
                                            AppColors.CardBackground,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .padding(12.dp)
                                )
                            }
                        }
                        item {
                            HorizontalStockSection(
                                title = "Top Gainers",
                                stocks = state.topGainers ?: emptyList(),
                                onViewAllClick = { onViewAllClick("gainers") },
                                onStockClick = { symbol, price, change ->
                                    onStockClick(symbol, price, change)
                                }
                            )
                        }
                        item {
                            HorizontalStockSection(
                                title = "Top Losers",
                                stocks = state.topLosers ?: emptyList(),
                                onViewAllClick = { onViewAllClick("losers") },
                                onStockClick = { symbol, price, change ->
                                    onStockClick(symbol, price, change)
                                }
                            )
                        }
                        item {
                            HorizontalStockSection(
                                title = "Most Active",
                                stocks = state.mostActive ?: emptyList(),
                                onViewAllClick = { onViewAllClick("active") },
                                onStockClick ={ symbol, price, change ->
                                    onStockClick(symbol, price, change)
                                }
                            )
                        }
                    }
                }

                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No data available",
                            color = AppColors.TextSecondary,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
// Bottom Bar
@Composable
fun BottomNavigationBar(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.CardBackground)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomBarItem(
            label = "Home",
            icon = Icons.Default.Home,
            isSelected = selectedTab == "home",
            onClick = { onTabSelected("home") }
        )
        BottomBarItem(
            label = "Watchlist",
            icon = Icons.Default.List,
            isSelected = selectedTab == "watchlist",
            onClick = { onTabSelected("watchlist") }
        )
    }
}

@Composable
fun BottomBarItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor = if (isSelected) AppColors.AccentGreen else AppColors.TextSecondary

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = contentColor
        )
        Text(
            text = label,
            color = contentColor,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 12.sp
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullStockListScreen(
    title: String,
    stocks: List<StockItem>,
    onBack: () -> Unit,
    onStockClick: (String, String, String?) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
                        color = AppColors.TextPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = AppColors.AccentGreen
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.CardBackground
                )
            )
        },
        containerColor = AppColors.Background
    ) { paddingValues ->
        if (stocks.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No $title available",
                    color = AppColors.TextSecondary,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(stocks) { stock ->
                    StockCard(
                        name = stock.ticker,
                        price = stock.price,
                        change = "${stock.changeAmount} (${stock.changePercentage})",
                        changeIsPositive = stock.changeAmount.toDoubleOrNull()?.let { it >= 0 }
                            ?: true,
                        onClick = {
                            onStockClick(
                                stock.ticker,
                                stock.price,
                                stock.changePercentage
                            )
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .shadow(6.dp, RoundedCornerShape(12.dp))
                            .background(AppColors.CardBackground)
                    )
                }
            }
        }
    }
}

