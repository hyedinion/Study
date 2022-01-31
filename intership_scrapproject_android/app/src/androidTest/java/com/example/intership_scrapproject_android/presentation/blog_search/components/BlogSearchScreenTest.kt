package com.example.intership_scrapproject_android.presentation.blog_search.components

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.intership_scrapproject_android.di.ApiModule
import com.example.intership_scrapproject_android.di.DatabaseModule
import com.example.intership_scrapproject_android.di.RepositoryModule
import com.example.intership_scrapproject_android.di.UseCaseModule
import com.example.intership_scrapproject_android.presentation.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(ApiModule::class, DatabaseModule::class, RepositoryModule::class, UseCaseModule::class)
class BlogSearchScreenTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        hiltRule.inject()
    }

}