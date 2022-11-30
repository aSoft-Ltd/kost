package kost

fun Body.renderToString() = buildString {
    for (li in items) appendLine(li.renderToString())
}

private fun LineItem.renderToString() = buildString {
    append("${details}\t(${quantity}*${unitRate})")
}