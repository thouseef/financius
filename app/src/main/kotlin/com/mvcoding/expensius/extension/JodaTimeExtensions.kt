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

package com.mvcoding.expensius.extension

import android.text.format.DateUtils
import org.joda.time.LocalDate
import org.joda.time.ReadableInstant

fun isYesterday(readableInstant: ReadableInstant): Boolean = LocalDate.now().compareTo(LocalDate(readableInstant.millis + DateUtils.DAY_IN_MILLIS)) == 0
fun isTomorrow(readableInstant: ReadableInstant): Boolean = LocalDate.now().compareTo(LocalDate(readableInstant.millis - DateUtils.DAY_IN_MILLIS)) == 0