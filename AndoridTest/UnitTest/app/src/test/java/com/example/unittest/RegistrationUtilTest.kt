package com.example.unittest


import com.google.common.truth.Truth.assertThat
import org.junit.Test

//여기서 test-case를 작성할 수 있다
class RegistrationUtilTest{

    @Test //Test annotation 사용
    fun `empty username returns false`(){
        //username만 비어있는 result를 생성
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse() //가독성이 좋음
    }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        //올바르게 작성된 result
        val result = RegistrationUtil.validateRegistrationInput(
            "philipp",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username already exists returns false`(){
        //username만 비어있는 result를 생성
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        //password가 비어있음
        val result = RegistrationUtil.validateRegistrationInput(
            "philipp",
            "",
            "" //여기도 비워줘야 password와 같아서 comfirmedPassword는 문제가 없는 코드임
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password was repeated incorrectly returns false`(){
        val result = RegistrationUtil.validateRegistrationInput(
            "philipp",
            "123",
            "456"
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `password contains less than 2 digits returns false`(){
        //숫자가 1개만 있는 password
        val result = RegistrationUtil.validateRegistrationInput(
            "philipp",
            "abc1",
            "abc1"
        )
        assertThat(result).isFalse()
    }
}