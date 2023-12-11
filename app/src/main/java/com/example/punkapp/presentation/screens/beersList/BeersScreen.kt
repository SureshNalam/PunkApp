package com.example.punkapp.presentation.screens.beersList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.punkapp.NavRoute
import com.example.punkapp.domain.domainModels.BeersDomainModel
import com.example.punkapp.presentation.screens.beersList.mvi.BeersScreenState
import com.example.punkapp.ui.widgets.LoadingScreen

@Composable
fun BeersScreen(navController: NavHostController) {
    val beersViewModel = hiltViewModel<BeersViewModel>()
    val state = beersViewModel.state.collectAsState().value

    Scaffold(topBar = {
        TopBar(title = "Punk App")
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    LoadingScreen()
                }

                state.errorMsg != null -> {
                    ErrorScreen(state.errorMsg)
                }

                state.beers.isNotEmpty() -> {
                    BeersListContent(state = state,
                        onBeerItemSelected = {
                            navController.navigate(NavRoute.BEER_DETAILS.route + "/${it.id}")
                        })
                }
            }
        }
    }

}

@Composable
private fun BeersListContent(
    state: BeersScreenState,
    onBeerItemSelected: (BeersDomainModel) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        items(state.beers) {
            BeerItem(beer = it) { selectedBeerItem ->
                onBeerItemSelected(selectedBeerItem)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BeerItem(
    beer: BeersDomainModel,
    modifier: Modifier = Modifier,
    onBeerItemSelected: (BeersDomainModel) -> Unit
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp), onClick = { onBeerItemSelected(beer) }) {
        Row {
            AsyncImage(
                model = beer.imageUrl,
                contentDescription = "beer description",
                modifier = Modifier
                    .height(60.dp)
                    .width(100.dp)
                    .padding(2.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                beer.name?.let { BeerName(title = it) }
                beer.tagline?.let { BeerTagLine(title = it) }
            }
        }
    }
}

@Composable
private fun ErrorScreen(errorMsg: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        errorMsg?.let { Text(text = it, textAlign = TextAlign.Center) }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar(title: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        title = { Text(text = title, textAlign = TextAlign.Center) }
    )
}

@Composable
fun BeerName(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier.padding(4.dp),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BeerTagLine(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier.padding(4.dp),
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold
    )
}