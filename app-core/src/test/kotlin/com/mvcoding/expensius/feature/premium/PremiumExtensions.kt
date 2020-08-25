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

import com.mvcoding.expensius.model.SubscriptionType
import com.mvcoding.expensius.model.SubscriptionType.FREE
import com.mvcoding.expensius.model.SubscriptionType.PREMIUM_PAID
import com.mvcoding.expensius.model.extensions.aBoolean
import com.mvcoding.expensius.model.extensions.aRandomItem
import com.mvcoding.expensius.model.extensions.aString

fun aBillingProduct() = BillingProduct(aString(), aSubscriptionType(), aString(), aString(), aString(), aBoolean())
fun BillingProduct.withSubscriptionType(subscriptionType: SubscriptionType) = copy(subscriptionType = subscriptionType)
fun BillingProduct.withIsOwned(isOwned: Boolean) = copy(isOwned = isOwned)

fun BillingProduct.asPremium() = withSubscriptionType(FREE)
fun BillingProduct.asDonation() = withSubscriptionType(PREMIUM_PAID)
fun BillingProduct.owned() = withIsOwned(true)
fun BillingProduct.notOwned() = withIsOwned(false)

fun aSubscriptionType() = SubscriptionType.values().aRandomItem()