package dev.doctorm4id.m4id.ext

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import kotlin.jvm.optionals.getOrNull

//? > 1.20.1 {

fun ResourceLocation(a: String, b: String) = ResourceLocation.fromNamespaceAndPath(a, b)

//?} <= 1.20.1 {

/*fun ResourceLocation(a: String, b: String) = ResourceLocation(MOD_ID, path!!)

*///?}

val Item.id: ResourceLocation
    get() = BuiltInRegistries.ITEM.getKey(this)

val ItemStack.id: ResourceLocation
    get() = BuiltInRegistries.ITEM.getKey(item)

val EntityType<*>.id: ResourceLocation
    get() = BuiltInRegistries.ENTITY_TYPE.getKey(this)

fun <T : Any> Registry<T>.getKeyNullable(thing: T): ResourceLocation? {
    return wrapAsHolder(thing).unwrapKey().getOrNull()?.location()
}

fun <T> Registry<T>.register(id: ResourceLocation, obj: T) {
    if (obj != null) {
        Registry.register(this, id, obj)
    } else throw NullPointerException("Cannot register ${id.path}, obj is null.")
}

fun <T> Registry<T>.registerForMod(modId: String, items: () -> Map<String, T>) {
    for ((itemId, item) in items()) {
        register(ResourceLocation(modId, itemId), item)
    }
}

operator fun <T> Registry<T>.get(tagKey: TagKey<T>): List<T> {
    return get(tagKey).toList()
}
