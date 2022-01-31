package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.intership_scrapproject_android.ui.theme.IntershipScrapProjectAndroidTheme


@ExperimentalComposeUiApi
@Composable
fun BlogSearchAppBar(
    text : String,
    isBlogSearchLayoutList : Boolean,
    onTextChange : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onBlogSearchLayoutChange : () -> Unit,
){

    val keyboardController = LocalSoftwareKeyboardController.current

    Row {
        Card(
            modifier = Modifier
                .padding(start = 10.dp,top = 10.dp,bottom=10.dp)
                .weight(6f),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(1.dp, Color.Black),
            backgroundColor = Color.White
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text,
                onValueChange = { onTextChange(it) },
                placeholder = { //hint 속성
                    Text(
                        //alpha == 불투명도 설정, 1f : 불투명, 0f : 투명
                        modifier = Modifier
                            .alpha(ContentAlpha.disabled),
                        text = "검색어를 입력하세요",
                        color = Color.Black
                    )
                },
                textStyle = TextStyle(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize
                ),
                singleLine = true,
                leadingIcon = {
                    IconButton(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search icon",
                            tint = Color.Black
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked(text)
                        keyboardController?.hide()
                    },
                ),
                //textField color가 surface color를 없애 버림, 여기서 수정해 줘야함
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Gray.copy(alpha = ContentAlpha.medium)
                )
            )
        }

        IconButton(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .alpha(ContentAlpha.medium)
                .weight(1f),
            onClick = {onBlogSearchLayoutChange()}
        ) {
            if (isBlogSearchLayoutList) {
                Icon(
                    modifier = Modifier
                        .size(30.dp),
                    imageVector = Icons.Default.GridOn,
                    contentDescription = "grid icon",
                    tint = Color.Black

                )
            }
            else{
                Icon(
                    modifier = Modifier
                        .size(40.dp),
                    imageVector = Icons.Default.List,
                    contentDescription = "list icon",
                    tint = Color.Black

                )
            }

        }
    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    IntershipScrapProjectAndroidTheme {
        BlogSearchAppBar(
            text = "",
            isBlogSearchLayoutList = true,
            onTextChange = {},
            onSearchClicked = {},
            onBlogSearchLayoutChange = {}
        )
    }
}