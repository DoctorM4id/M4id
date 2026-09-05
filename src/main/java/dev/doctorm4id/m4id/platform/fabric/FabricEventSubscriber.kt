package dev.doctorm4id.m4id.platform.fabric

//? fabric {

import io.ejekta.kambrik.internal.KambrikCommands
import io.ejekta.kambrikx.data.KambrikPersistence
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader

class FabricEventSubscriber {

	fun registerEvents() {
		CommandRegistrationCallback.EVENT.register(CommandRegistrationCallback(KambrikCommands::register))

		ServerLifecycleEvents.SERVER_STARTED.register {
			KambrikPersistence.loadServerResults()
		}

		ServerLifecycleEvents.SERVER_STOPPING.register {
			KambrikPersistence.saveAllServerResults()
			if (FabricLoader.getInstance().environmentType != EnvType.CLIENT) {
				KambrikPersistence.saveAllConfigResults()
			}
		}
	}
}

//? }
