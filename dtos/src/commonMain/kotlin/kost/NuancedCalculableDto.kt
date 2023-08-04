package kost

interface NuancedCalculableDto : CalculableDto {
    val tax: TaxRateDto?
    val discount: OverallDiscountDto
}