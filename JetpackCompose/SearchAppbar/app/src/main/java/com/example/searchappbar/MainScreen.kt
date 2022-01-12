package com.example.searchappbar

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(mainViewModel : MainViewModel){

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    Scaffold(
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                   mainViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    mainViewModel.updateSearchTextState(newValue = "")
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched Text", it)
                },
                onSearchTriggered = {
                    mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        }
    ) {}
}

//default appbar 쓸건지 SearchAppBar 쓸건지 결정
@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState : String,
    onTextChange : (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered : () -> Unit
){
    when(searchWidgetState){
        SearchWidgetState.CLOSED ->{
            DefaultAppBar (
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,// 여기서 state로 지켜보다가 update되면 recompose해줌
                onTextChange = onTextChange, //여기서 콜백함수로 text를 전달해줌
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }

}


@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit){
    TopAppBar(
        title = { Text(text = "Home")},
        actions = {
            IconButton(onClick = { onSearchClicked() } ) {
                Icon(
                    imageVector = Icons.Filled.Search,//image 소문자로 적어야 오류가 안남
                    contentDescription = "Search icon",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text : String, //typing된 text 나타냄
    onTextChange : (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked : (String) -> Unit,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {onTextChange(it)},//여기서 updateSearchTextState에게 값을 전달해줌
            placeholder = { //hint 속성인듯
                Text(
                    //alpha == 불투명도 설정, 1f : 불투명, 0f : 투명
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "search here ...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            //search icon
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        tint = Color.White

                    )

                }
            },
            //textfield 옆에 X 아이콘
            trailingIcon = {
                IconButton(
                    onClick = {
                        //close 버튼을 눌렀을 때
                        if(text.isNotEmpty()){
                            onTextChange("")//text 초기화
                        }else{
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close icon",
                        tint = Color.White

                    )

                }
            },
            keyboardOptions = KeyboardOptions(
                //keyboard의 입력버튼을 뭘로 할지 설정가능
                imeAction = ImeAction.Search
            ),
            //입력 버튼이 눌렸을 때
            keyboardActions = KeyboardActions(
                onSearch = {onSearchClicked(text)}
            ),
            //textField color가 surface color를 없애 버림, 여기서 수정해 줘야함
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}

@Composable
@Preview
fun DefaultAppBarPreview(){
    DefaultAppBar(onSearchClicked = {})
}

@Composable
@Preview
fun SearchAppBarPreview(){
    SearchAppBar(
        text = "random text",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {})
}