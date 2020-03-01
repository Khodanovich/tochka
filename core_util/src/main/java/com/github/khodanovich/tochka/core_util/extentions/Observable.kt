package com.github.khodanovich.tochka.core_util.extentions

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.async() = io().mainThread()

fun Completable.io() = subscribeOn(Schedulers.io())

fun Completable.mainThread() = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.async() = io().mainThread()

fun <T> Single<T>.io() = subscribeOn(Schedulers.io())

fun <T> Single<T>.mainThread() = observeOn(AndroidSchedulers.mainThread())
