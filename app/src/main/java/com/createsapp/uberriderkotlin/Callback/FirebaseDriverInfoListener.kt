package com.createsapp.uberriderkotlin.Callback

import com.createsapp.uberriderkotlin.model.DriverGeoModel

interface FirebaseDriverInfoListener {
    fun onDriverInfoLoadSuccess(driverGeoModel: DriverGeoModel?)
}  