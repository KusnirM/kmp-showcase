package mk.digital.kmpsample.data.dto

import mk.digital.kmpsample.data.repository.dto.DTOTestRunner
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AddressDTOTest : DTOTestRunner() {

    @Test
    fun testSuccess() = runTest<AddressDTO>(
        """
    {
      "city": "city",
      "street": "street",
      "suite": "suite",
      "zipcode": "zipcode"
    }
    """
    ) {
        Assertions.assertEquals(
            AddressDTO("city", "street", "suite", "zipcode"),
            it
        )
    }
}
