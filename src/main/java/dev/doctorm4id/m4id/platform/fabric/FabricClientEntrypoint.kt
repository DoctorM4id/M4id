package dev.doctorm4id.m4id.platform.fabric

//? fabric {

import dev.doctorm4id.m4id.M4id
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint
import io.ejekta.kambrik.Kambrik
import dev.doctorm4id.m4id.platform.fabric.bridge.M4idSharedApiFabric
import io.ejekta.kambrik.message.KambrikMsg
import io.ejekta.kambrikx.data.KambrikPersistence
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents

@Entrypoint("client")
class FabricClientEntrypoint : ClientModInitializer {

	override fun onInitializeClient() {
        M4id.onInitializeClient()

		M4idSharedApiFabric.clientMessageRegistrar = { id ->
			ClientPlayNetworking.registerGlobalReceiver(id) { payload, context ->
				(payload as KambrikMsg).onClientReceived()
			}
		}
		M4idSharedApiFabric.clientMessageSender = { msg ->
			ClientPlayNetworking.send(msg)
		}

		// Client data lifecycle management

		WorldRenderEvents.LAST.register(WorldRenderEvents.Last {
			Kambrik.Input.updateRealBinds()
		})

		ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick {
			Kambrik.Input.updateNormBinds()
		})

		ClientLifecycleEvents.CLIENT_STOPPING.register {
			KambrikPersistence.saveAllConfigResults()
		}
	}
}

//? }
