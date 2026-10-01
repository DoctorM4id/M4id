@file:Suppress("unused")

package dev.doctorm4id.m4id.util

import kotlin.random.Random

class M4idPool<T> {

	private var totalWeight = 0
	private var entries = ArrayList<M4idPoolEntry<T>>()

	/**
	 * Adds an entry
	 * @param value the value, (Ex: Block)
	 * @param weightIn the weight of the entry
	 */
	fun addEntry(value: T, weightIn: Int) {
		if (weightIn <= 0) return

		totalWeight += weightIn
		val poolEntry = M4idPoolEntry(value, weightIn)
		entries.add(poolEntry)
	}

	/**
	 * Adds an experimental entry
	 * @param value the value, (Ex: Block)
	 * @param weightIn the weight of the entry
	 */
	fun addExperimentalEntry(value: T, weightIn: Int) {
		if (weightIn <= 0) return

		totalWeight += weightIn

		val poolEntry = M4idPoolEntry(value, weightIn)
		poolEntry.requireExperimentalMode()
		entries.add(poolEntry)
	}

	/**
	 * Returns a random entry.
	 * @return a random value.
	 */
	fun getRandomEntry(): T? {
		if (totalWeight <= 0 || entries.isEmpty()) return null

		val randomValue = Random.nextInt(totalWeight)
		var cumulativeSum = 0

		for (entry in entries) {
			cumulativeSum += entry.weight

			if (randomValue < cumulativeSum) return entry.value
		}

		return entries.lastOrNull()?.value
	}
}

class M4idPoolEntry<T>(val value: T, var weight: Int) : Comparable<M4idPoolEntry<T>> {

	var requiresExperimentalMode: Boolean = false

	fun requireExperimentalMode() {
		requiresExperimentalMode = true
	}

	fun doesRequireExperimentalMode(): Boolean {
		return requiresExperimentalMode
	}

	override fun compareTo(other: M4idPoolEntry<T>): Int {
		return this.weight.compareTo(other.weight)
	}
}
