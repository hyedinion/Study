package com.example.my_first.presentation.scrap.components

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.preference.PreferenceManager
import com.example.my_first.presentation.BottomBar
import com.example.my_first.presentation.scrap.ScrapEvent
import com.example.my_first.presentation.scrap.ScrapViewModel

@ExperimentalAnimationApi
@Composable
fun ScrapScreen(
    navController: NavHostController,
    viewModel : ScrapViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState() //order창이 열렸는지 state remember
    val scope = rememberCoroutineScope()
    val shared = PreferenceManager.getDefaultSharedPreferences(LocalContext.current)

    val editor = shared.edit()
    editor.putString("키","값")
    editor.apply()
    val myCheck = shared.getString("키","")

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("add_scrap")},
                backgroundColor = MaterialTheme.colors.primary
            ) {
               Icon(imageVector = Icons.Default.Add, contentDescription = "Add Scrap")
            }
        },
        bottomBar = { BottomBar(navController = navController) },
        scaffoldState = scaffoldState
    ) {
        //Appbar
        Text(text = myCheck?:"")
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "your Scrap",
                    style = MaterialTheme.typography.h6
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(ScrapEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Sort")
                }

            }

            //order Section
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()

            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    scrapOrder = state.scrapOrder,
                    onOrderChange = {
                        viewModel.onEvent(ScrapEvent.Order(it)) //usecase에서 전달해준 order로 설정
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            //Lazy Column
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(state.scraps){ scrap ->
                    ScrapItem(
                        scrap = scrap,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                    )

                }
            }

        }
    }




}