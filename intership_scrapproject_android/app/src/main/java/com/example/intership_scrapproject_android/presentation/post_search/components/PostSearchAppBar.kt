package com.example.intership_scrapproject_android.presentation.post_search.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun PostSearchAppBar(
    text : String,
    onTextChange : (String) -> Unit,
    onSearchClicked : (String) -> Unit,
    onBackPressed : () -> Unit,
){

    val keyboardController = LocalSoftwareKeyboardController.current

    Box (
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 3.dp)
            .fillMaxWidth()
            .height(50.dp)
    ) {

        TextField(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
            ,
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.disabled),
                    text = "스크랩 검색",
                    color = Color.Black
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {onBackPressed()}
                ) {
                    Icon(
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                            .size(30.dp),
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = "Back",
                        tint = Color.DarkGray
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = {onSearchClicked(text)}
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
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Gray.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}


@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    IntershipScrapProjectAndroidTheme {
        PostSearchAppBar(
            text = "",
            onTextChange = {},
            onSearchClicked = {},
            {}
        )
    }
}
