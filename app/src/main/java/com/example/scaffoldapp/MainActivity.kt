package com.example.scaffoldapp

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldapp.ui.theme.ScaffoldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldAppTheme {
                    ScaffoldApp()
                }
            }
        }
}


@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ){
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController){
    Scaffold (
        topBar = { MainTopBar(title = "My App", navController )},
        content = {Text(text= "Content for Home screen")}
    )
}

@Composable
fun InfoScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar(title = "Info", navController )},
        content = {Text(text= "Content for Info screen")}

    )
}

@Composable
fun SettingScreen(navController: NavController){
    Scaffold (
        topBar = { ScreenTopBar(title = "Settings", navController )},
        content = {Text(text= "Content for Settings screen")}

            )
}



@Composable
fun MainTopBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {Text("My App")},
        //navigationIcon = {
         //   IconButton(onClick = { /*TODO*/ }) {
         //       Icon(Icons.Filled.Menu, contentDescription = null)
          //  }
       // },
        actions = {
            IconButton(
                onClick = { expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false}) {
                DropdownMenuItem(onClick = { navController.navigate("info")}) {
                    Text("Info")
                }
                DropdownMenuItem(onClick = { navController.navigate("settings")}) {
                    Text("Settings")
                }

            }
        }
    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldAppTheme {
        ScaffoldApp()
    }
}