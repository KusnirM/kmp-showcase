package com.mk.kmpshowcase.data.dto

import kotlinx.serialization.Serializable
import com.mk.kmpshowcase.data.base.TransformToDomainModel
import com.mk.kmpshowcase.domain.model.Example

@Serializable
data class ExampleDTO(
    val id: Int,
    val name: String,
) : TransformToDomainModel<Example> {
    override fun transform(): Example = Example(
        id = id,
        name = name,
    )
}
