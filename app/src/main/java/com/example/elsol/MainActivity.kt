package com.example.elsol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import com.example.elsol.ui.theme.ElSolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElSolTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { CreateBottomAppBar() }
                ) { innerPadding ->
                    PantallaInicial(
                        modifier = Modifier.padding(innerPadding),
                        snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateBottomAppBar() {
    var likeCount by remember { mutableStateOf(0) }

    BottomAppBar (
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            IconButton(onClick = { likeCount++ }) {
                BadgedBox(
                    modifier = Modifier.size(100.dp),
                    badge = {
                        if (likeCount > 0) {
                            Badge(
                                modifier = Modifier
                                    .offset(x = (-16).dp, y = (7).dp)
                            ) {
                                Text(text = likeCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = "Like",
                        Modifier.size(35.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    )
}