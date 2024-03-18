package com.example.latestcomponentpractice.Repository

class UserRepository {
    companion object {
        fun receiveAllUsers() : List<String> {
         return ArrayList<String>().apply {
             add("Mohit")
             add("Archana")
             add("Mahendra")
             add("Sukhsagar")
             add("Ranjan")
         }
        }
    }
}