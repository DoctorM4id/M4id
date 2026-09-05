package dev.doctorm4id.m4id.bridge

import dev.doctorm4id.m4id.M4id
import dev.doctorm4id.m4id.registration.M4idAutoRegistrar
import dev.doctorm4id.m4id.platform.fabric.bridge.BridgePlatform
import dev.doctorm4id.m4id.internal.TestMsg
import dev.doctorm4id.m4id.message.M4idMsg
import kotlinx.serialization.KSerializer
import net.minecraft.core.Registry
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import java.nio.file.Path

interface M4idSharedApi {

    // Loader

    val platform: BridgePlatform

    // Messaging

    // * Client

    fun isOnClient(): Boolean

    fun isOnServer(): Boolean

    fun <M : M4idMsg> registerClientMessage(serializer: KSerializer<M>, id: CustomPacketPayload.Type<M>): Boolean

    fun <M : M4idMsg> sendMsgToClient(msg: M, player: ServerPlayer)

    // * Server

    fun <M : M4idMsg> registerServerMessage(serializer: KSerializer<M>, id: CustomPacketPayload.Type<M>): Boolean

    fun <M : M4idMsg> sendMsgToServer(msg: M)

    // Registration

    fun <T> register(autoReg: M4idAutoRegistrar, reg: Registry<T>, thingId: String, obj: T): T

    // Internal

    fun registerTestMessage() {
        M4id.Message.registerClientMessage<TestMsg>(
            ResourceLocation.fromNamespaceAndPath("m4id", "test_msg")
        )
    }

    fun getConfigDir(): Path
}
