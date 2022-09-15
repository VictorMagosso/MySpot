package com.victor.myspot.core

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseConfig : IFirebaseConfig {
    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseRef: DatabaseReference? = null

    override fun getFirebaseAuth(): FirebaseAuth {
        if(firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }
        return firebaseAuth as FirebaseAuth
    }
    override fun getFirebaseDatabase(): DatabaseReference {
        if(firebaseRef == null) {
            firebaseRef = FirebaseDatabase.getInstance().reference
        }
        return firebaseRef as DatabaseReference
    }
}

interface IFirebaseConfig {
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseDatabase(): DatabaseReference
}