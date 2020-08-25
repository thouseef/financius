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

import com.mvcoding.expensius.model.AppUser
import com.mvcoding.expensius.model.UserId
import rx.Observable

class AppUserIdSource(private val appUserSource: DataSource<AppUser>) : DataSource<UserId> {
    override fun data(): Observable<UserId> = appUserSource.data().map { it.userId }.distinctUntilChanged()
}