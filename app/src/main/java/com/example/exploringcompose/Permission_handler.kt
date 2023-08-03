package com.example.exploringcompose

import android.Manifest
import android.Manifest.permission.*
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.security.Permission

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun handle_permission() : MultiplePermissionsState {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            CAMERA, RECORD_AUDIO
        )
    )

    val lifecycleowner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleowner){
        val observer = LifecycleEventObserver{_, event ->
            if (event == Lifecycle.Event.ON_START){
                permissionState.launchMultiplePermissionRequest()
            }
        }
        lifecycleowner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleowner.lifecycle.removeObserver(observer)
        }
    }

    return permissionState
}