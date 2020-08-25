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

package com.mvcoding.expensius.feature.transaction

class TransactionPresenterTest {
//    val transactionStateSubject = PublishSubject<TransactionState>()
//    val transactionTypeSubject = PublishSubject<TransactionType>()
//    val timestampSubject = PublishSubject<Long>()
//    val requestCurrencyChangeSubject = PublishSubject<Unit>()
//    val currencySubject = PublishSubject<Currency>()
//    val exchangeRateSubject = PublishSubject<BigDecimal>()
//    val amountSubject = PublishSubject<BigDecimal>()
//    val tagsSubject = PublishSubject<Set<Tag>>()
//    val noteSubject = PublishSubject<String>()
//    val toggleArchiveSubject = PublishSubject<Unit>()
//    val saveSubject = PublishSubject<Unit>()
//
//    val appUser = anAppUser()
//    val transaction = aTransaction()
//    val transactionsWriteService: TransactionsWriteService = mock()
//    val appUserService: AppUserService = mock()
//    val currenciesProvider = CurrenciesSource()
//
//    val view: TransactionPresenter.View = mock()
//    val presenter = presenter(transaction)
//
//    @Before
//    fun setUp() {
//        whenever(view.transactionStateChanges()).thenReturn(transactionStateSubject)
//        whenever(view.transactionTypeChanges()).thenReturn(transactionTypeSubject)
//        whenever(view.timestampChanges()).thenReturn(timestampSubject)
//        whenever(view.currencyChangeRequests()).thenReturn(requestCurrencyChangeSubject)
//        whenever(view.currencyChanges(any())).thenReturn(currencySubject)
//        whenever(view.exchangeRateChanges()).thenReturn(exchangeRateSubject)
//        whenever(view.amountChanges()).thenReturn(amountSubject)
//        whenever(view.tagsChanges()).thenReturn(tagsSubject)
//        whenever(view.noteChanges()).thenReturn(noteSubject)
//        whenever(view.archiveToggles()).thenReturn(toggleArchiveSubject)
//        whenever(view.saveRequests()).thenReturn(saveSubject)
//
//        whenever(transactionsWriteService.saveTransactions(any())).thenReturn(just(Unit))
//        whenever(transactionsWriteService.createTransactions(any())).thenReturn(just(Unit))
//        whenever(appUserService.appUser()).thenReturn(just(appUser))
//    }
//
//    @Test
//    fun showsInitialValues() {
//        presenter.attach(view)
//
//        verify(view).showModelState(transaction.modelState)
//        verify(view).showTransactionState(transaction.transactionState)
//        verify(view).showTransactionType(transaction.transactionType)
//        verify(view).showTimestamp(transaction.timestamp)
//        verify(view).showMoney(transaction.money)
//        verify(view).showTags(transaction.tagIds)
//        verify(view).showNote(transaction.note)
//    }
//
//    @Test
//    fun showsUpdatedValues() {
//        val tagIds = setOf(aTag(), aTag())
//        val currency = Currency("EUR")
//        val money = Money(TEN, currency)
//        presenter.attach(view)
//
//        updateTransactionState(PENDING)
//        updateTransactionType(INCOME)
//        updateTimestamp(1)
//        requestCurrencyChange()
//        updateCurrency(currency)
//        updateExchangeRate(TEN)
//        updateAmount(TEN)
//        saveTags(tagIds)
//        updateNote("Updated note")
//
//        presenter.detach(view)
//        presenter.attach(view)
//
//        verify(view, atLeast(2)).showTransactionState(PENDING)
//        verify(view, atLeast(2)).showTransactionType(INCOME)
//        verify(view, atLeast(2)).showTimestamp(Timestamp(1))
//        verify(view).currencyChanges(allCurrencies())
//        verify(view, atLeast(2)).showMoney(money)
//        verify(view, atLeast(2)).showTags(tagIds)
//        verify(view, atLeast(2)).showNote(Note("Updated note"))
//    }
//
//    @Test
//    fun savesExistingTransactionAndDisplaysResult() {
//        presenter.attach(view)
//
//        save()
//
//        verify(transactionsWriteService).saveTransactions(argThat { first() == transaction })
//        verify(view).displayResult()
//    }
//
//    @Test
//    fun createsNewTransactionAndDisplaysResult() {
//        val tagIds = setOf(aTag(), aTag())
//        val currency = Currency("EUR")
//        val note = Note("note")
//        presenter(noTransaction).attach(view)
//
//        updateTransactionState(PENDING)
//        updateTransactionType(INCOME)
//        updateTimestamp(1)
//        requestCurrencyChange()
//        updateCurrency(currency)
//        updateExchangeRate(TEN)
//        updateAmount(TEN)
//        saveTags(tagIds)
//        updateNote(note.text)
//        save()
//
//        verify(transactionsWriteService).createTransactions(argThat {
//            first() == CreateTransaction(INCOME, PENDING, Timestamp(1), Money(TEN, currency), tagIds, note)
//        })
//        verify(view).displayResult()
//    }
//
//    @Test
//    fun archiveIsDisabledForNewTransaction() {
//        presenter(noTransaction).attach(view)
//
//        verify(view).showArchiveEnabled(false)
//    }
//
//    @Test
//    fun archiveIsEnabledForExistingTransaction() {
//        presenter.attach(view)
//
//        verify(view).showArchiveEnabled(true)
//    }
//
//    @Test
//    fun archivesTransactionAndDisplaysResult() {
//        val archivedTransaction = transaction.copy(modelState = ARCHIVED)
//        presenter.attach(view)
//
//        toggleArchive()
//
//        verify(transactionsWriteService).saveTransactions(argThat { first() == archivedTransaction })
//        verify(view).displayResult()
//    }
//
//    @Test
//    fun restoresTransactionAndDisplaysResult() {
//        val archivedTransaction = transaction.copy(modelState = ARCHIVED)
//        val restoredTransaction = transaction.copy(modelState = NONE)
//        presenter(archivedTransaction).attach(view)
//
//        toggleArchive()
//
//        verify(transactionsWriteService).saveTransactions(argThat { first() == restoredTransaction })
//        verify(view).displayResult()
//    }
//
//    private fun toggleArchive() = toggleArchiveSubject.onNext(Unit)
//
//    private fun updateTransactionType(transactionType: TransactionType) = transactionTypeSubject.onNext(transactionType)
//    private fun updateTransactionState(transactionState: TransactionState) = transactionStateSubject.onNext(transactionState)
//    private fun updateTimestamp(timestamp: Long) = timestampSubject.onNext(timestamp)
//    private fun requestCurrencyChange() = requestCurrencyChangeSubject.onNext(Unit)
//    private fun updateCurrency(currency: Currency) = currencySubject.onNext(currency)
//    private fun updateExchangeRate(exchangeRate: BigDecimal) = exchangeRateSubject.onNext(exchangeRate)
//    private fun updateAmount(amount: BigDecimal) = amountSubject.onNext(amount)
//    private fun saveTags(tags: Set<Tag>) = tagsSubject.onNext(tags)
//    private fun updateNote(note: String) = noteSubject.onNext(note)
//    private fun save() = saveSubject.onNext(Unit)
//    private fun allCurrencies() = TestSubscriber.create<List<Currency>>().apply { currenciesProvider.data().subscribe(this) }.onNextEvents.first()
//    private fun presenter(transaction: Transaction = aTransaction()) = TransactionPresenter(transaction, transactionsWriteService, currenciesProvider)
}