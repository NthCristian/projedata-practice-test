package io.nthcristian.projedata.dto

interface ApplyChanges<T> {
    fun apply(entity: T)
}
