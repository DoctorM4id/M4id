package dev.doctorm4id.m4id.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

object M4idCommonUtil {

	fun id(namespace: String?, path: String?): ResourceLocation {
		//? > 1.20.1 {
		return ResourceLocation.fromNamespaceAndPath(namespace!!, path!!)
		//?} <= 1.20.1 {
		/*return ResourceLocation(namespace!!, path!!);
		*///?}
	}

	/**
	 * Copies a block properties from the wanted block.
	 * This is mainly makes it easier for across versions.
	 * @param block Block to copy properties from.
	 * @return The block behavior properties.
	 */
	fun copyBlockProperties(block: Block): BlockBehaviour.Properties {
		//? if >=1.20.5 {
		return BlockBehaviour.Properties.ofFullCopy(block);
		//?} else {
		/*return BlockBehaviour.Properties.copy(block);
		*///?}
	}
}
