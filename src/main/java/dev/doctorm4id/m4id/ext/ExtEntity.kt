package dev.doctorm4id.m4id.ext

import net.minecraft.world.entity.LivingEntity

fun LivingEntity.healFully() {
    heal(maxHealth - health)
}

fun LivingEntity.heal(amount: Float) {
    health = (health + amount).coerceAtMost(maxHealth)
}

fun LivingEntity.isMoving(): Boolean = deltaMovement.length() > 0.01