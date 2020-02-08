package com.mohsenoid.themoviedb.domain.mapper

interface Mapper<I, O> {

    fun map(input: I): O
}
