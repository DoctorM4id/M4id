package dev.doctorm4id.m4id.ext.commands

import com.mojang.brigadier.suggestion.SuggestionsBuilder

fun SuggestionsBuilder.addAll(list: List<String>) {
    for (item in list) {
        suggest(item)
    }
}