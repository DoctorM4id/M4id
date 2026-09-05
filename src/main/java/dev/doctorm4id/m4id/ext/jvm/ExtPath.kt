package dev.doctorm4id.m4id.ext.jvm

import dev.doctorm4id.m4id.ext.internal.assured
import java.nio.file.Path

fun Path.ensured(folderName: String): Path {
    return resolve(folderName).assured
}

