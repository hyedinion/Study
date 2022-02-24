package com.example.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import kotlinx.android.parcel.Parcelize
import java.net.URLEncoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "first"){
                composable("first"){
                    FirstScreen(navController)
                }
                composable("second"){
                    SecondScreen(navController)
                }
                composable("third/{value}"){ backStackEntry ->
                    ThirdScreen(
                        navController = navController,
                        //번들로 값을 받아옴
                        value = backStackEntry.arguments?.getParcelable("value")?:Hi("fail",-1)
                    )
                }
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {
        //FirstScreen()
    }
}

@Composable
fun FirstScreen(navController: NavController){
    val (value,setValue) = remember{ mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "첫화면")
        Spacer(modifier= Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("second")
        }){
            Text(text = "두번째")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = value, onValueChange = setValue)
        Button(onClick = {
            if (value.isNotEmpty()) {
                val test = URLEncoder.encode(value)
                val hiTest = Hi(test,0)
                navController.navigate("third/$hiTest")
            }
        }){
            Text(text = "세번째")
        }

    }
}
@Composable
fun SecondScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "두 번째 화면")
        Spacer(modifier= Modifier.height(16.dp))

        Button(onClick = {
            navController.navigateUp()
        }){
            Text(text = "뒤로가기")
        }
        LazyColumn{
            for(i in 1..100) {
                items(i){ i ->
                    Text(text = i.toString())
                }
            }
        }


    }
}
@Composable
fun ThirdScreen(navController: NavController, value: Hi){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "세 번째 화면")
        Spacer(modifier= Modifier.height(16.dp))
        Text(text = value.toString())
        Button(onClick = {
            navController.navigateUp()
        }){
            Text(text = "뒤로가기")
        }


    }
}

@Parcelize
data class Hi(val a : String, val b : Int) : Parcelable