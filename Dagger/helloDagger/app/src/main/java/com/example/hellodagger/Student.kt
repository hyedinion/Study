package com.example.hellodagger

import javax.inject.Inject

data class Student @Inject constructor(val name : String)