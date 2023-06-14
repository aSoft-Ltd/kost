package kost

import kotlinx.serialization.Serializable

@Serializable
sealed interface NuancedCalculableDto : CalculableDto {
    val tax: TaxDto
    val discount: OverallDiscountDto
}