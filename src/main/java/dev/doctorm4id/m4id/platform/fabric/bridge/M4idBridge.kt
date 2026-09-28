package dev.doctorm4id.m4id.platform.fabric.bridge

import dev.doctorm4id.m4id.M4id
import dev.doctorm4id.m4id.bridge.M4idSharedApi
import java.util.ServiceLoader

val M4idBridge: M4idSharedApi by lazy {
    M4id.Logger.info("Creating M4id Library Shared API...")
    val sls = ServiceLoader.load(M4idSharedApi::class.java)

    M4id.Logger.info("Eh?")

    sls.findFirst().get().also { M4id.Logger.debug("Created API For: {}", it.platform) }
}