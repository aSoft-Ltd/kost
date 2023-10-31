package kost

fun CostDto.withSelling(price: CostDto): PriceDtoOld {
    val buy = after.tax.asDouble
    val sel = price.after.tax.asDouble

    val markup = if (buy == 0.0) 0 else {
        ((sel - buy) * 10000 / buy).toInt()
    }

    val margin = if (sel == 0.0) 0 else {
        ((sel - buy) * 10000 / sel).toInt()
    }

    return PriceDtoOld(this, price, markup, margin)
}

/**
 * M = (s-b)*100/s
 * s * M = 100s - 100b
 * 100s-Ms = 100b
 * s = 100b/(100-M)
 */
fun CostDto.withMargin(value: Int): PriceDtoOld {
    if (value == 100) return PriceDtoOld(this, this, Int.MAX_VALUE, value)
    return withSelling(this * 100 / (100 - value))
}

/**
 * M = (s-b)*100/b
 * Mb = 100s - 100b
 * 100s = (M+100)b
 * s = (100+M)b/100
 */
fun CostDto.withMarkup(value: Int): PriceDtoOld = withSelling(this * (100 + value) / 100)