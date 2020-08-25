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

import com.mvcoding.expensius.data.DataSource
import com.mvcoding.expensius.model.AppUser
import com.mvcoding.expensius.model.Tag
import com.mvcoding.expensius.model.UserId
import com.mvcoding.expensius.model.extensions.aTag
import com.mvcoding.expensius.model.extensions.anAppUser
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Test
import rx.Observable

class TagsWriterTest {

    val appUser = anAppUser()

    val appUserSource = mock<DataSource<AppUser>>()
    val updateTags = mock<(UserId, Set<Tag>) -> Unit>()
    val tagsWriter = TagsWriter(appUserSource, updateTags)

    @Before
    fun setUp() {
        whenever(appUserSource.data()).thenReturn(Observable.just(appUser))
    }

    @Test
    fun `writes tags for app user`() {
        val tagsSet = setOf(aTag(), aTag(), aTag())

        tagsWriter.write(tagsSet)

        verify(updateTags).invoke(appUser.userId, tagsSet)
    }
}