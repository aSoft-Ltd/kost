@file:JsExport

package kost

import identifier.Email
import identifier.Phone
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed class Address {

    @Serializable
    object Unset : Address()

    @Serializable
    data class Description(
        val details: String
    ) : Address()

    @Serializable
    data class Location(
        val country: String,
        val city: String,
        val street: String,
    ) : Address()

    @Serializable
    data class Contacts(
        val phone: Phone,
        val email: Email
    ) : Address()

    @Serializable
    data class LocationWithContacts(
        val country: String,
        val city: String,
        val street: String,
        val phone: Phone,
        val email: Email
    ) : Address()
}