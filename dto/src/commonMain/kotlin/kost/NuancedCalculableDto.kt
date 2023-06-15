package kost

interface NuancedCalculableDto : CalculableDto {
    val tax: TaxDto?
    val discount: OverallDiscountDto
}