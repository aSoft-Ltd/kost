@file:JvmName("SenderUtils")

package kost

import kotlin.jvm.JvmName

inline fun Sender.toCreator() = Creator(uid = uid, name = name)