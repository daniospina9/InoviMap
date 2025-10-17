package com.example.inovimap.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    latitude: Double,
    longitude: Double
) {
    // User location from server
    val userLocation = LatLng(latitude, longitude)

    //creates and remembers the state of the map camera.
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 10f)
    }

    //Creating the Scoreboard State
    val markerState = rememberMarkerState(position = userLocation)

    Scaffold { innerPadding ->
        //GoogleMap is the @Composable component that renders the map on the screen.
        GoogleMap(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            //Marker draws a marker on the map.
            Marker(
                state = markerState,
                title = "Your Location",
                snippet = "Your User Location"
            )
        }
    }
}