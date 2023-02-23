package kost

import kotlinx.serialization.Serializable

@Serializable
sealed interface InvoicePointer

@Serializable
data class InvoiceRef(val uid: String) : InvoicePointer

@Serializable
object None : InvoicePointer