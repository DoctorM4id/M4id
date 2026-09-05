package dev.doctorm4id.m4id.registration

import dev.doctorm4id.m4id.M4id
import dev.doctorm4id.m4id.ext.register
import dev.doctorm4id.m4id.ext.ResourceLocation

import net.minecraft.core.Registry

object M4idRegistrar {

    data class RegistrationEntry<T>(val registry: Registry<T>, val itemId: String, val item: Lazy<T>) {
        fun register(modId: String) {
            M4id.Logger.debug("Registering item: ${modId}:${itemId}")
            registry.register(ResourceLocation(modId, itemId), item.value)
        }
    }

    data class ModRegistrar(val requestor: M4idAutoRegistrar, val content: MutableList<RegistrationEntry<*>> = mutableListOf())

    private val registrars = mutableMapOf< M4idAutoRegistrar, ModRegistrar>()

    operator fun get(requester:  M4idAutoRegistrar): ModRegistrar {
        return registrars.getOrPut(requester) { ModRegistrar(requester) }
    }

    fun <T> register(requester:  M4idAutoRegistrar, reg: Registry<T>, itemId: String, obj: Lazy<T>): Lazy<T> {
        M4id.Logger.debug("StoatLibrary registering '${requester::class.qualifiedName} for '$itemId' for auto-registration")
        this[requester].content.add(RegistrationEntry(reg, itemId, obj))
        return obj
    }

    fun doRegistrationsFor(modId: String) {
        registrars.filter { it.key.getId() == modId }.forEach { (_, items) ->
            for (item in items.content) {
                item.register(modId)
            }
        }
    }

    fun doRegistrationsFor(requester:  M4idAutoRegistrar) {
        this[requester].content.forEach { item ->
            item.register(requester.getId())
        }
    }
}