package kost

interface Offerable {
    val uid: String
}

data class Product(override val uid: String) : Offerable
data class Service(override val uid: String) : Offerable
data class CustomOfferable(override val uid: String) : Offerable

class LItem<out T : Offerable>(
    val uid: String,
    val data: T,
    val amount: Long
)

val li = listOf(
    LItem("unset", Product("uid"), 0L),
    LItem("unset", Product("ui2"), 0L),
    LItem("unset", Service("ui2"), 0L),
)

