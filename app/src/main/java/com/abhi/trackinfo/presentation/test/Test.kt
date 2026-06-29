package com.abhi.trackinfo.presentation.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.retain.retain
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun Test() {

    val navController = rememberNavController()

    var newAbc by retain { mutableStateOf("") }

//    Scaffold(
//
//    ) { pad ->
//        NavHost(
//            modifier = Modifier.padding(pad),
//            navController = navController,
//            startDestination = "first"
//        ) {
//
//            composable(route = "first") { FirstScreen(navController) }
//            composable(route = "second") { SecondScreen(navController) }
//
//        }
//    }


    var triggerEffect by remember { mutableStateOf(false) }
    var showDisposable by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // 1. LaunchedEffect — runs when triggerEffect becomes true
    LaunchedEffect(triggerEffect) {
        if (triggerEffect) {
            println("Test LaunchedEffect: triggered!")
            delay(1000)
            println("Test LaunchedEffect: done after 1 sec")
        }
    }

    // 2. SideEffect — runs on every recomposition
    SideEffect {
        println("Test SideEffect: recomposed!")
    }

    // 3. DisposableEffect — shows when triggerEffect is true
    if (showDisposable) {
        DisposableEffect(Unit) {
            println("Test DisposableEffect: registered!")
            onDispose {
                println("Test DisposableEffect: cleaned up!")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = newAbc,
            onValueChange = { newValue -> newAbc = newValue },
        )

        Text(newAbc)

//        // Button 1 — triggers LaunchedEffect + SideEffect
//        TextButton(
//            onClick = {
//                triggerEffect = !triggerEffect   // flips key → LaunchedEffect reruns
//            }
//        ) {
//            Text("Trigger LaunchedEffect")
//        }
//
//        // Button 2 — triggers DisposableEffect register/cleanup
//        TextButton(
//            onClick = {
//                showDisposable = !showDisposable  // toggle shows/hides DisposableEffect
//            }
//        ) {
//            Text(if (showDisposable) "Remove DisposableEffect" else "Add DisposableEffect")
//        }
//
//        // Button 3 — rememberCoroutineScope (user-triggered async)
//        TextButton(
//            onClick = {
//                scope.launch {
//                    println("Test rememberCoroutineScope: started!")
//
//                    println("Test rememberCoroutineScope: finished!")
//                }
//            }
//        ) {
//            Text("Trigger Scope Launch")
//        }
    }

}

@Composable
fun ExternalFun() {

    InternalFun1()

}

@Composable
fun InternalFun1() {

    LaunchedEffect(Unit) { }

    SideEffect {  }

}

@Composable
fun FirstScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("First Screen")

        TextButton(
            onClick = {
                navController.navigate("second")
            }
        ) {
            Text("Move to second screen")
        }
    }
}

@Composable
fun SecondScreen(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(" Second Screen")

        TextButton(
            onClick = {
                navController.navigate("first")
            }
        ) {
            Text("Move to first screen")
        }
    }
}

@Composable
fun InternalFun3() {

    LaunchedEffect(Unit) { }

    SideEffect {  }

}
