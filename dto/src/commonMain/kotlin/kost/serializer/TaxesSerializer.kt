@file:OptIn(ExperimentalSerializationApi::class)

package kost.serializer

import kollections.toIList
import kost.TaxDto
import kost.TaxesDto
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.listSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TaxesSerializer : KSerializer<TaxesDto> {
    override val descriptor: SerialDescriptor = listSerialDescriptor(TaxDto.serializer().descriptor)

    private val serializer = ListSerializer(TaxDto.serializer())

    override fun deserialize(decoder: Decoder): TaxesDto {
        return TaxesDto(decoder.decodeSerializableValue(serializer).toIList())
    }

    override fun serialize(encoder: Encoder, value: TaxesDto) {
        encoder.encodeSerializableValue(serializer, value.items)
    }
}