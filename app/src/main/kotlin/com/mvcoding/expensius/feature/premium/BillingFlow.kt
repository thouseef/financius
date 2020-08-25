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

package com.mvcoding.expensius.feature.premium

import android.app.Activity
import android.content.Intent
import rx.Observable

interface BillingFlow {
    fun startPurchase(activity: Activity, requestCode: Int, productId: String): Observable<Unit>
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}