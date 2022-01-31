package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState

@Composable
fun BlogSearchErrorSnackBar(
    e : LoadState.Error,
    retry : () -> Unit,
) {
    Snackbar(
        action = {
            Button(onClick = {retry()}) {
                Text("retry")
            }
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = e.error.localizedMessage!!,
            color = Color.Black
        )
    }
}