package mk.digital.kmpshowcase.data.dto

import kotlinx.serialization.Serializable
import mk.digital.kmpshowcase.data.base.TransformToDomainModel
import mk.digital.kmpshowcase.domain.model.Example

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
