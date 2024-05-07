package com.jkgug.example.storitest.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import org.koin.dsl.module

class SingleModule {

    companion object {

        val singleModule = module {

            // Firebase
            single { FirebaseAuth.getInstance() }
            single { Firebase.firestore }

        }

    }

}