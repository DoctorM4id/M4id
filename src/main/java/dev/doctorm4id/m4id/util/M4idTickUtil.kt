@file:Suppress("unused")

package dev.doctorm4id.m4id.util

import net.minecraft.world.level.Level
import kotlin.math.abs

object M4idTickUtil {

	val TICKS_PER_SECOND: Int = 20
	val TICKS_PER_MINUTE: Int = TICKS_PER_SECOND * 60
	val TICKS_PER_HOUR: Int = TICKS_PER_MINUTE * 60
	val TICKS_PER_DAY: Int = TICKS_PER_HOUR * 24
	val TICKS_PER_WEEK: Int = TICKS_PER_DAY * 7
	val TICKS_PER_MONTH: Int = TICKS_PER_DAY * 30
	val TICKS_PER_YEAR: Int = TICKS_PER_DAY * 365

	fun convertSecondsToTicks(seconds: Float): Int {
		return (seconds * TICKS_PER_SECOND).toInt()
	}

	fun convertTicksToSeconds(ticks: Long): Long {
		return ticks / TICKS_PER_SECOND
	}

	fun convertSecondsToTicks(seconds: Int): Int {
		return seconds * TICKS_PER_SECOND
	}

	fun convertMinutesToTicks(minutes: Int): Int {
		return minutes * TICKS_PER_MINUTE
	}

	fun convertTicksToMinutes(ticks: Long): Long {
		return ticks / TICKS_PER_MINUTE
	}

	fun convertHoursToTicks(hours: Int): Int {
		return hours * TICKS_PER_HOUR
	}

	fun convertDaysToTicks(days: Int): Int {
		return days * TICKS_PER_DAY
	}

	fun convertWeeksToTicks(weeks: Int): Int {
		return weeks * TICKS_PER_WEEK
	}

	fun convertMonthsToTicks(months: Int): Int {
		return months * TICKS_PER_MONTH
	}

	fun convertYearsToTicks(years: Int): Int {
		return years * TICKS_PER_YEAR
	}

	/**
	 * Checks if number of ticks has passed.
	 * If event never occurred before, returns true.
	 * @param lastTickTime The tick of when event last occurred.
	 * @param currentTickTime The current tick.
	 * @param ticks Minimum required waiting ticks that must pass before event can happen.
	 * @return True if required number of ticks has passed or first occurrence. False otherwise
	 */
	fun hasTicksPassed(lastTickTime: Long, currentTickTime: Long, ticks: Long): Boolean {
		if (lastTickTime == 0L) return true

		return abs(currentTickTime - lastTickTime) >= ticks
	}

	/**
	 * Checks if number of ticks has passed using level's current tick.
	 * If event never occurred before, returns true.
	 * @param lastTickTime The tick of when event last occurred.
	 * @param level The world providing current tick.
	 * @param ticks Minimum required waiting ticks that must pass before event can happen.
	 * @return True if required number of ticks has passed or first occurrence. False otherwise
	 */
	fun hasTicksPassed(lastTickTime: Long, level: Level, ticks: Long): Boolean {
		return hasTicksPassed(lastTickTime, level.gameTime, ticks)
	}
}
