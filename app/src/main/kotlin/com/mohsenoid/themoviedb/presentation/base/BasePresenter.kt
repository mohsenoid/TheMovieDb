package com.mohsenoid.themoviedb.presentation.base

interface BasePresenter<V : BaseView> {

    fun bind(view: V)

    fun unbind()
}
