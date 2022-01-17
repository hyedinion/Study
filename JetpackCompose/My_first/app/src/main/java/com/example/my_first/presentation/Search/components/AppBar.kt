package com.example.my_first.presentation.Search.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my_first.ui.theme.My_firstTheme


@Composable
fun SearchAppBar(
    text : String, //typing된 text 나타냄
    onTextChange : (String) -> Unit,
    onCloseClicked : () -> Unit,
    onSearchClicked : (String) -> Unit,
){
    var text by remember { mutableStateOf("") }
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
            //onValueChange = {onTextChange(it)},
            onValueChange = {text = it},
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


@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    My_firstTheme {
        SearchAppBar(
            text = "",
            onTextChange = {},
            onCloseClicked = {},
            onSearchClicked = {}
        )
    }
}