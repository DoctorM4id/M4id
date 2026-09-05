package dev.doctorm4id.m4id.platform.fabric

//? fabric {

import dev.doctorm4id.m4id.platform.Platform
import net.fabricmc.loader.api.FabricLoader

class FabricPlatform : Platform {

	override fun isModLoaded(modId: String?): Boolean {
		return FabricLoader.getInstance().isModLoaded(modId)
	}

	override fun loader(): Platform.ModLoader {
		return Platform.ModLoader.FABRIC
	}

	override fun mcVersion(): String? {
		return FabricLoader.getInstance().rawGameVersion
	}

	override fun isDevelopmentEnvironment(): Boolean {
		return FabricLoader.getInstance().isDevelopmentEnvironment
	}
}

//? }
