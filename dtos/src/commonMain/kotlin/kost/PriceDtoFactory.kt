package kost

/**
 * M = (s-b)*100/s
 * s * M = 100s - 100b
 * 100s-Ms = 100b
 * s = 100b/(100-M)
 */
fun CostDto.withMargin(value: Int): PriceDto {
    if (value == 100) return PriceDto(this, this)
    return PriceDto(this, this * 100 / (100 - value))
}

/**
 * M = (s-b)*100/b
 * Mb = 100s - 100b
 * 100s = (M+100)b
 * s = (100+M)b/100
 */
fun CostDto.withMarkup(value: Int): PriceDto = PriceDto(this, this * (100 + value) / 100)