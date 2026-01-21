package mk.digital.kmpsample.domain

import io.mockk.MockKAnnotations
import org.junit.jupiter.api.BeforeEach

//todo -> move to common
abstract class BaseTest<ClassUnderTest> {
    abstract var classUnderTest: ClassUnderTest

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        beforeEach()
    }

    abstract fun beforeEach()
}
