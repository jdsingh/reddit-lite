package me.jagdeep.remote

/**
 * Interface for creating a mappers to map Remote layer models to Data layer models.
 */
interface EntityMapper<in R, out E> {

    fun mapToEntity(type: R): E

}
