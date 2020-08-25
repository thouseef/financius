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

package com.mvcoding.expensius.feature.splash

import com.mvcoding.expensius.RxSchedulers
import com.mvcoding.expensius.data.DataSource
import com.mvcoding.expensius.feature.login.LoginPresenter.Destination
import com.mvcoding.expensius.feature.login.LoginPresenter.Destination.APP
import com.mvcoding.expensius.model.AppUser
import com.mvcoding.mvp.Presenter

class SplashPresenter(
        private val appUserSource: DataSource<AppUser>,
        private val schedulers: RxSchedulers) : Presenter<SplashPresenter.View>() {

    override fun onViewAttached(view: View) {
        super.onViewAttached(view)

        appUserSource.data()
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.main)
                .subscribeUntilDetached {
                    if (it.isLoggedIn()) view.displayApp()
                    else view.displayLogin(APP)
                }
    }

    interface View : Presenter.View {
        fun displayLogin(destination: Destination)
        fun displayApp()
    }
}