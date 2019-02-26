package com.example.logonrmlocal.projectpokemon.fcm

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseInstanceIdService : FirebaseInstanceIdService(){

    override  fun  onTokenRefresh(){
       super.onTokenRefresh()
        Log.i("Token", FirebaseInstanceId.getInstance().token)

    }
}