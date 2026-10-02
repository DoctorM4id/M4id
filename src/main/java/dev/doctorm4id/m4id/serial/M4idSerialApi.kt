package dev.doctorm4id.m4id.serial

import dev.doctorm4id.m4id.serial.serializers.BoxSerializer
import dev.doctorm4id.m4id.serial.serializers.IdentitySer
import dev.doctorm4id.m4id.serial.serializers.Vec3DSer
import dev.doctorm4id.percale.reverse.CompoundTagSerializer
import dev.doctorm4id.percale.reverse.toSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.modules.SerializersModule
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.phys.AABB
import net.minecraft.world.phys.Vec3

@Suppress("PropertyName")
class M4idSerialApi {

    val DefaultSerializers = SerializersModule {
        contextual(ResourceLocation::class, IdentitySer)
        contextual(AABB::class, BoxSerializer)
        contextual(Vec3::class, Vec3DSer)
        contextual(CompoundTag::class, CompoundTagSerializer)
        //contextualCodec(ItemStack.CODEC)
        contextual(ItemStack::class, ItemStack.CODEC.toSerializer(JsonObject.serializer()))
    }

    private var networkSerializers = SerializersModule {
        include(DefaultSerializers)
    }

    fun addNetworkSerializerModule(module: SerializersModule) {
        networkSerializers = SerializersModule {
            include(DefaultSerializers)
            include(networkSerializers)
            include(module)
        }
    }

    fun networkingFormat(default: Boolean = true): Json {
        //return Json { serializersModule = networkSerializers }
        return Json {
            serializersModule = if (default) {
                SerializersModule {
                    include(DefaultSerializers)
                    include(networkSerializers)
                }
            } else {
                networkSerializers
            }
        }
    }

    val Format = formatFor(DefaultSerializers) {
        prettyPrint = true
    }

    fun formatFor(serialModule: SerializersModule = DefaultSerializers, builder: JsonBuilder.() -> Unit = {}): Json {
        return Json {
            this.apply {
                serializersModule = serialModule
            }.builder()
        }
    }

}