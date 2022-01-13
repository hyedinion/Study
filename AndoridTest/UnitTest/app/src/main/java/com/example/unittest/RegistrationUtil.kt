package com.example.unittest

object RegistrationUtil { //specify signature of function

    //이미 존재하는 User를 fake로 만들어놨음
    private val existingUsers = listOf("Peter","Carl")

    //언제 valid한지 생각해보자
    /*
    * the input is not valid if
    * ...the username/password is empty
    * ...the username is already taken
    * ...the confirmed password is not the same as the real password
    * ...the password contains less than 2 digits
    *
    * */
    //여기에 관한 모든 test case를 작성해야 한다

    fun validateRegistrationInput( //세개다 정확하게 입력하면 true를 return
        username : String,
        password: String,
        confirmedPassword: String
    ) : Boolean{
        if(username.isEmpty() || password.isEmpty()){
            return false
        }
        if(username in existingUsers){
            return false
        }
        if(password != confirmedPassword){
            return false
        }
        if(password.count { it.isDigit() }<2){
            return false
        }
        return true
    }
}