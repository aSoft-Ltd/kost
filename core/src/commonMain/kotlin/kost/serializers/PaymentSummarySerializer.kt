package kost.serializers

import kash.Zero
import kollections.toIList
import kost.Payment
import kost.PaymentSummary
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object PaymentSummarySerializer : KSerializer<PaymentSummary> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("kost.PaymentSummary")

    private val serializer = ListSerializer(Payment.serializer())

    override fun deserialize(decoder: Decoder): PaymentSummary {
        val items = decoder.decodeSerializableValue(serializer).toIList()
        return PaymentSummary(items, Zero)
    }

    override fun serialize(encoder: Encoder, value: PaymentSummary) {
        encoder.encodeSerializableValue(serializer, value.items)
    }
}