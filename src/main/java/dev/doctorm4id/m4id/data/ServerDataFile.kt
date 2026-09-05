package dev.doctorm4id.m4id.data

import java.io.File

class ServerDataFile(src: File) : DataFile(src) {
    private var loaded = false

    init {
        M4idPersistence.serverDataFiles.add(this)
    }

    override fun load() {
        super.load()
        loaded = true
    }

    override fun save() {
        super.save()
        loaded = false
    }

    override fun <R : Any> loadResult(key: String): R {
        if (!loaded) {
            throw Exception("Trying to access server data before the server is loaded!")
        }
        return super.loadResult(key)
    }
}