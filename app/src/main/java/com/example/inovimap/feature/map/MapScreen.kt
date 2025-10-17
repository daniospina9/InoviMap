package com.example.inovimap.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(

) {
    // Default location (set to coordinates of your choice)
    val defaultLocation = LatLng(4.7110, -74.0721)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
    }

    Scaffold { innerPadding ->
        GoogleMap(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        )
    }
}