package dev.doctorm4id.m4id.logging

import dev.doctorm4id.m4id.M4id
import net.minecraft.resources.ResourceLocation

data class M4idMarker(val id: ResourceLocation) {
    companion object {
        val Rendering = M4idMarker(M4id.idOf("rendering"))
        val NBT = M4idMarker(M4id.idOf("nbt"))
    }
}