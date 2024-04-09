package com.example.latestcomponentpractice.di

import javax.inject.Inject

class UserRegistrationService @Inject constructor(
    val userRepository: UserRepository,
    val emailService: EmailService) {
    fun registerUser(email : String, password : String) {
        userRepository.saveUser(email, password)
        emailService.sendEmail(email, "Hello From Dependency Injection...")
    }
}