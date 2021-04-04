package com.createsapp.uberriderkotlin.common

import com.createsapp.uberriderkotlin.model.RiderModel

object Common {
    var currentRider: RiderModel? = null
    const val RIDER_INFO_REFERENCE: String = "Riders"

    fun buildWelcomeMessage(): String {
        return StringBuilder("Welcome, ")
            .append(currentRider!!.firstName)
            .append(" ")
            .append(currentRider!!.lastName)
            .toString()
    }

}