package dev.doctorm4id.m4id.input

import net.minecraft.client.gui.screens.Screen

class M4idKeyModifier(
    val shift: Boolean = false,
    val ctrl: Boolean = false,
    val alt: Boolean = false
) {
    fun getIsPressed(): Boolean {
        return (shift == Screen.hasShiftDown() && ctrl == Screen.hasControlDown() && alt == Screen.hasAltDown())
    }

    companion object {
        val EMPTY = M4idKeyModifier()
    }
}