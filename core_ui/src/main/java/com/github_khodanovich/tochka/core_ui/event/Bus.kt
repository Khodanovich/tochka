package com.github_khodanovich.tochka.core_ui.event

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


open class Bus<T> {
    private val subject = PublishSubject.create<T>()

    fun getData(): Observable<T> = subject

    fun setData(data: T) {
        subject.onNext(data)
    }
}