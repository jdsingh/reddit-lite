package me.jagdeep.data

/**
 * Interface for creating a mappers to map Entities to Domain models and vice versa.
 */
interface DataMapper<E, D> {

    fun mapToEntity(type: D): E

    fun mapFromEntity(type: E): D

}
