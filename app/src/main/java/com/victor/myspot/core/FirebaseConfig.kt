package com.victor.myspot.core

import com.google.firebase.auth.FirebaseAuth

class FirebaseConfig : IFirebaseConfig {
    private var firebaseAuth: FirebaseAuth? = null

    override fun getFirebaseAuth(): FirebaseAuth {
        if(firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }
        return firebaseAuth as FirebaseAuth
    }
}

interface IFirebaseConfig {
    fun getFirebaseAuth(): FirebaseAuth
}