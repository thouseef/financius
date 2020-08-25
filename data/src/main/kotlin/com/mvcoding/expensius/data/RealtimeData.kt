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

package com.mvcoding.expensius.data

sealed class RealtimeData<out ITEM> {
    abstract val allItems: List<ITEM>

    data class AllItems<out ITEM>(override val allItems: List<ITEM>) : RealtimeData<ITEM>()
    data class AddedItems<out ITEM>(override val allItems: List<ITEM>, val addedItems: List<ITEM>, val position: Int) : RealtimeData<ITEM>()
    data class ChangedItems<out ITEM>(override val allItems: List<ITEM>, val changedItems: List<ITEM>, val position: Int) : RealtimeData<ITEM>()
    data class RemovedItems<out ITEM>(override val allItems: List<ITEM>, val removedItems: List<ITEM>, val position: Int) : RealtimeData<ITEM>()
    data class MovedItems<out ITEM>(override val allItems: List<ITEM>, val movedItems: List<ITEM>, val fromPosition: Int, val toPosition: Int) : RealtimeData<ITEM>()
}