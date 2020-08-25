/*
 * Copyright (C) 2017 Mantas Varnagiris.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package com.mvcoding.expensius.feature

import android.content.Context
import com.mvcoding.expensius.R
import com.mvcoding.expensius.extension.isTomorrow
import com.mvcoding.expensius.extension.isYesterday
import com.mvcoding.expensius.model.ReportPeriod
import net.danlew.android.joda.DateUtils.*
import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder

class DateFormatter(private val context: Context) {

    val dateFormatter: DateTimeFormatter = DateTimeFormatterBuilder()
            .appendMonthOfYearText()
            .appendLiteral(" ")
            .appendYear(0, 4)
            .toFormatter()

    fun formatDateRelativeToToday(timestamp: Long): String {
        val dateTime = DateTime(timestamp)

        if (isToday(dateTime)) {
            return context.getString(R.string.today)
        } else if (isYesterday(dateTime)) {
            return context.getString(R.string.yesterday)
        } else if (isTomorrow(dateTime)) {
            return context.getString(R.string.tomorrow)
        }

        return formatDateTime(context, dateTime, FORMAT_SHOW_DATE or FORMAT_SHOW_WEEKDAY or FORMAT_ABBREV_ALL)
    }

    fun formatDateShort(dateTime: DateTime): String = formatDateTime(context, dateTime, FORMAT_SHOW_DATE or FORMAT_ABBREV_ALL)
    fun formatInterval(reportPeriod: ReportPeriod, interval: Interval): String = when (reportPeriod) {
        ReportPeriod.MONTH -> dateFormatter.print(interval.start)
    }
}