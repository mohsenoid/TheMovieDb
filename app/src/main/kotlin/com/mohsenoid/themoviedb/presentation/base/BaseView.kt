package com.mohsenoid.themoviedb.presentation.base

interface BaseView {

    fun showLoading()

    fun hideLoading()

    fun showMessage(message: String)

    fun showOfflineMessage(isCritical: Boolean)
}
