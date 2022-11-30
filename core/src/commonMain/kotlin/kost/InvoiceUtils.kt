package kost

fun Invoice.renderToString() = buildString {
    append(body.renderToString())
}

fun Invoice.printToConsole() = println(renderToString())