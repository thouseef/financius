/*
 * Copyright (C) 2016 Mantas Varnagiris.
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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.forEachChild(action: (View) -> Unit) {
    0.rangeTo(childCount - 1).forEach {
        action.invoke(getChildAt(it))
    }
}

@Suppress("UNCHECKED_CAST")
fun <V : View> ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false) as V