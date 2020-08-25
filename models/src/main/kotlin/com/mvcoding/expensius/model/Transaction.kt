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

package com.mvcoding.expensius.model

import java.io.Serializable
import java.math.BigDecimal

data class TransactionId(val id: String) : Serializable
data class Timestamp(val millis: Long) : Serializable
data class Note(val text: String) : Serializable
data class Currency(val code: String = "") : Serializable
data class Money(val amount: BigDecimal, val currency: Currency) : Serializable
enum class TransactionState { CONFIRMED, PENDING }
enum class TransactionType { EXPENSE, INCOME }

data class CreateTransaction(
        val transactionType: TransactionType,
        val transactionState: TransactionState,
        val timestamp: Timestamp,
        val money: Money,
        val tags: Set<Tag>,
        val note: Note) : Serializable

data class BasicTransaction(
        val transactionId: TransactionId,
        val modelState: ModelState,
        val transactionType: TransactionType,
        val transactionState: TransactionState,
        val timestamp: Timestamp,
        val money: Money,
        val tagIds: Set<TagId>,
        val note: Note) : Serializable {

    fun toTransaction(allTags: Collection<Tag>) = Transaction(
            transactionId,
            modelState,
            transactionType,
            transactionState,
            timestamp,
            money,
            tagIds.map { tagId -> allTags.firstOrNull { it.tagId == tagId } }.filterNotNull().toSet(),
            note
    )
}

data class Transaction(
        val transactionId: TransactionId,
        val modelState: ModelState,
        val transactionType: TransactionType,
        val transactionState: TransactionState,
        val timestamp: Timestamp,
        val money: Money,
        val tags: Set<Tag>,
        val note: Note) : Serializable {

    fun withMoney(money: Money) = copy(money = money)
}

interface TimestampProvider {
    fun currentTimestamp(): Timestamp
}