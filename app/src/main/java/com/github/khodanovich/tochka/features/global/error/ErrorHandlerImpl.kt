package com.github.khodanovich.tochka.features.global.error

import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException

interface ErrorHandler{
    fun proceed(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable)
}

class ErrorHandlerImpl : ErrorHandler {

    companion object {
        private const val FORBIDDEN_CODE = 403
        private const val FORBIDDEN_MESSAGE = "Исчерпано количество запросов для неавторизованного пользователя"
        private const val DEFAULT_MESSAGE = "Что то пошло не так, мы уже работаем над этим"
    }

    override fun proceed(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable) {
        processException(errorMessagesLiveData, throwable)
    }

    private fun processException(errorMessagesLiveData: MutableLiveData<String>, throwable: Throwable){
        when(throwable) {
            is HttpException -> processHttpException(errorMessagesLiveData, throwable)
            else -> showDefaultMessage(errorMessagesLiveData)

        }
    }

    private fun processHttpException(errorMessagesLiveData: MutableLiveData<String>, httpException: HttpException){
        when(httpException.code()){
            FORBIDDEN_CODE -> errorMessagesLiveData.postValue(FORBIDDEN_MESSAGE)
            else -> showDefaultMessage(errorMessagesLiveData)
        }
    }

    private fun showDefaultMessage(errorMessagesLiveData: MutableLiveData<String>){
        errorMessagesLiveData.postValue(DEFAULT_MESSAGE)
    }


}