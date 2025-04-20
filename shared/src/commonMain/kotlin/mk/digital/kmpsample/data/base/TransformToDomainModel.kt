package mk.digital.kmpsample.data.base

interface TransformToDomainModel<out DomainModel> {
    fun transform(): DomainModel
}
