package com.example.caloriecounter.data

import com.example.caloriecounter.util.Resource

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
}