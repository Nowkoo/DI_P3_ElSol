package com.example.elsol

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

data class Sol (
    var nombre: String,
    @DrawableRes var foto: Int
)

@Composable
fun PantallaInicial(modifier: Modifier, snackbarHostState: SnackbarHostState) {
    var sols: MutableList<Sol> by remember { mutableStateOf(getSols().toMutableList()) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        content = {
            items(sols.size) { index ->
                ItemSol(sols[index], snackbarHostState, sols)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSol(sol: Sol, snackbarHostState: SnackbarHostState, sols: MutableList<Sol>) {
    val scope = rememberCoroutineScope()
    var showDropdownMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(10.dp)
            .clickable {
                scope.launch {
                    snackbarHostState.showSnackbar(sol.nombre)
                }
            }
    ) {
        Image(
            painter = painterResource(sol.foto),
            contentDescription = sol.nombre,
            Modifier
                .fillMaxWidth()
                .size(200.dp),
              //  .padding(bottom = 20.dp),
            contentScale = ContentScale.Crop
        )

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text(
                    sol.nombre,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(PaddingValues(start = 5.dp))
                )
            },
            actions = {
                IconButton(onClick = { showDropdownMenu = !showDropdownMenu}) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More"
                    )
                }

                DropdownMenu(
                    expanded = showDropdownMenu,
                    onDismissRequest = {showDropdownMenu = false}
                ) {
                    DropdownMenuItem(
                        text = { Text("Copiar") },
                        onClick = { sols.add(sol) },
                        leadingIcon = { Icon(Icons.Filled.Add, contentDescription = null) }
                    )
                    DropdownMenuItem(
                        text = { Text("Eliminar") },
                        onClick = { sols.remove(sol)},
                        leadingIcon = { Icon(Icons.Filled.Delete, contentDescription = null) },
                    )
                }
            }
        )
    }
}

fun getSols(): List<Sol> {
    return listOf(
        Sol("Corona solar", R.drawable.corona_solar),
        Sol("Erupción solar", R.drawable.erupcionsolar),
        Sol("Espiculas", R.drawable.espiculas),
        Sol("Filamentos", R.drawable.filamentos),
        Sol("Magnetosfera", R.drawable.magnetosfera),
        Sol("Mancha solar", R.drawable.manchasolar)
    )
}