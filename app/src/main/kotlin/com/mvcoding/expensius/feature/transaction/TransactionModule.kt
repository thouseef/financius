/*
 * Copyright (C) 2015 Mantas Varnagiris.
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

package com.mvcoding.expensius.feature.transaction

import android.view.View
import com.memoizrlabs.ShankModule
import com.memoizrlabs.shankkotlin.provideGlobalSingleton
import com.memoizrlabs.shankkotlin.provideSingletonFor
import com.memoizrlabs.shankkotlin.registerFactory
import com.mvcoding.expensius.extension.registerFactory
import com.mvcoding.expensius.feature.ModelDisplayType
import com.mvcoding.expensius.feature.currency.provideCurrenciesProvider
import com.mvcoding.expensius.model.Transaction
import com.mvcoding.expensius.provideDatabase
import com.mvcoding.expensius.provideRxSchedulers
import com.mvcoding.expensius.provideSettings
import com.mvcoding.expensius.provider.DatabaseTransactionsProvider
import com.mvcoding.expensius.provider.database.table.TagsTable
import com.mvcoding.expensius.provider.database.table.TransactionTagsTable
import com.mvcoding.expensius.provider.database.table.TransactionsTable
import memoizrlabs.com.shankandroid.withActivityScope

class TransactionModule : ShankModule {
    override fun registerFactories() {
        transactionsProvider()
        transactionsPresenter()
        transactionPresenter()
    }

    private fun transactionsProvider() = registerFactory(TransactionsProvider::class) { ->
        DatabaseTransactionsProvider(
                provideDatabase(),
                TransactionsTable(),
                TransactionTagsTable(),
                TagsTable())
    }

    private fun transactionsPresenter() = registerFactory(TransactionsPresenter::class) { modelDisplayType: ModelDisplayType ->
        TransactionsPresenter(provideTransactionsProvider(), modelDisplayType, provideRxSchedulers())
    }

    private fun transactionPresenter() = registerFactory(TransactionPresenter::class) {
        transaction: Transaction ->
        TransactionPresenter(transaction, provideTransactionsProvider(), provideCurrenciesProvider(), provideSettings())
    }
}

fun provideTransactionsProvider(): TransactionsProvider = provideGlobalSingleton()
fun View.provideTransactionPresenter(transaction: Transaction): TransactionPresenter = withActivityScope.provideSingletonFor(transaction)
fun View.provideTransactionsPresenter(modelDisplayType: ModelDisplayType): TransactionsPresenter =
        withActivityScope.provideSingletonFor(modelDisplayType)