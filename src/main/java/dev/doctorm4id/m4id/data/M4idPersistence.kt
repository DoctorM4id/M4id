package dev.doctorm4id.m4id.data

object M4idPersistence {
    val serverDataFiles = mutableListOf<ServerDataFile>()
    val configDataFiles = mutableListOf<ConfigDataFile>()

    fun saveAllConfigResults() {
        println("Saving config results")
        configDataFiles.forEach { dataFile ->
            dataFile.save()
        }
    }

    fun saveAllServerResults() {
        println("Saving server results")
        serverDataFiles.forEach { dataFile ->
            dataFile.save()
        }
    }

    fun loadServerResults() {
        serverDataFiles.forEach { dataFile ->
            dataFile.load()
        }
    }

}