package me.jagdeep.database

interface DatabaseMapper<E, D> {

    fun mapToDatabase(type: E): D

    fun mapFromDatabase(type: D): E

}
