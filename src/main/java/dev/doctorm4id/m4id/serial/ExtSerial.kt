package dev.doctorm4id.m4id.serial

import dev.doctorm4id.m4id.M4id
import dev.doctorm4id.m4id.message.M4idMsg
import kotlinx.serialization.KSerializer
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec

//*
fun <M : M4idMsg> KSerializer<M>.toSimplePacketCodec(): StreamCodec<RegistryFriendlyByteBuf, M> {
    val json = M4id.Serial.networkingFormat()
    return StreamCodec.ofMember(
        { value, buf ->
            buf.writeUtf(json.encodeToString(this, value))
        },
        { json.decodeFromString(this, it.readUtf()) }
    )
}