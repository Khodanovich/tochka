package com.github.khodanovich.tochka.features.global.error

import androidx.lifecycle.MutableLiveData

interface ErrorHandler{
    fun proceed(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable)
}

class ErrorHandlerImpl : ErrorHandler {

    override fun proceed(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable) {
        processException(errorMessagesLiveData, throwable)
    }

    private fun processException(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable){
        errorMessagesLiveData.postValue("Что то пошло не так, мы уже работаем над этим")
        //Тут необходимо обработать ошибки
    }
}