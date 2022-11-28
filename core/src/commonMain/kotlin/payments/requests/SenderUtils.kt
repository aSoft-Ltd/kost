@file:JvmName("SenderUtils")

package payments.requests

import kotlin.jvm.JvmName

inline fun Sender.toCreator() = Creator(uid = uid, name = name)