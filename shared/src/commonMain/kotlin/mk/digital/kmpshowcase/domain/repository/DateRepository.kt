package mk.digital.kmpshowcase.domain.repository

import kotlinx.datetime.LocalDate

interface DateRepository {
    fun today(): LocalDate
}
