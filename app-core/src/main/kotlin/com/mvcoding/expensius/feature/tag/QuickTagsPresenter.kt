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

package com.mvcoding.expensius.feature.tag

import com.mvcoding.expensius.RxSchedulers
import com.mvcoding.expensius.data.DataSource
import com.mvcoding.expensius.model.Tag
import com.mvcoding.mvp.Presenter
import rx.Observable
import rx.Observable.combineLatest

class QuickTagsPresenter(
        private val tagsSource: DataSource<List<Tag>>,
        private val schedulers: RxSchedulers) : Presenter<QuickTagsPresenter.View>() {

    private val toggledTags = hashMapOf<Tag, Boolean>()

    override fun onViewAttached(view: View) {
        super.onViewAttached(view)

        val selectedTags = view.selectedTagsUpdates().doOnNext { it.forEach { toggledTags.put(it, true) } }
        val allTags = combineLatest(tagsSource.data(), selectedTags, {
            tags, selectedTags ->
            tags.plus(selectedTags.filterNot { tags.contains(it) }).sortedBy(Tag::order)
        })

        allTags.subscribeOn(schedulers.io)
                .map { toSelectableTags(it) }
                .observeOn(schedulers.main)
                .subscribeUntilDetached { view.showSelectableTags(it) }

        view.selectableTagToggles()
                .doOnNext { toggledTags.put(it.tag, it.isSelected.not()) }
                .subscribeUntilDetached { view.showUpdatedSelectableTag(it, it.toggled()) }
    }

    private fun toSelectableTags(tags: List<Tag>) = tags.map { SelectableTag(it, toggledTags.getOrElse(it, { false })) }

    interface View : Presenter.View {
        fun selectedTagsUpdates(): Observable<Set<Tag>>
        fun selectableTagToggles(): Observable<SelectableTag>
        fun showSelectableTags(selectableTags: List<SelectableTag>)
        fun showUpdatedSelectableTag(oldSelectableTag: SelectableTag, newSelectableTag: SelectableTag)
    }
}