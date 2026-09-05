package dev.doctorm4id.m4id.logging

import dev.doctorm4id.m4id.M4id
import org.apache.logging.log4j.Marker
import kotlin.collections.iterator

internal object M4idMarkers {

    val Registry = mutableMapOf<String, Boolean>()

    val General = createIdMarker("general")
    val Rendering = createIdMarker("rendering")

    private fun createIdMarker(name: String): Marker {
        return M4id.Logging.createMarker(M4id.idOf(name))
    }

    internal fun handleContainerMarkers(states: Map<String, Boolean>) {
        for ((key, state) in states) {
            Registry[key] = state
        }
    }

}