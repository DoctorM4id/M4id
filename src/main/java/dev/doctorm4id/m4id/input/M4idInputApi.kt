package dev.doctorm4id.m4id.input

class M4idInputApi internal constructor() {

    private val keyBinds = mutableListOf<M4idKeybind>()

    fun updateNormBinds() {
        for (bind in keyBinds) {
            if (!bind.realTime) {
                bind.update()
            }
        }
    }

    fun updateRealBinds() {
        for (bind in keyBinds) {
            if (bind.realTime) {
                bind.update()
            }
        }
    }

    fun registerBinding(
        key: KambrikModifiedBind,
        realTime: Boolean = false,
        bindingDsl: M4idKeybind.() -> Unit
    ): M4idKeybind {
        val kambrikKeybind = M4idKeybind(
            key, realTime
        ).apply(bindingDsl)
        keyBinds.add(kambrikKeybind)
        return kambrikKeybind
    }

}