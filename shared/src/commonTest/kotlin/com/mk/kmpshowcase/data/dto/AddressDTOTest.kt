package com.mk.kmpshowcase.data.dto

import kotlin.test.Test
import kotlin.test.assertEquals

class AddressDTOTest : BaseDTOTest() {

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
        assertEquals(
            AddressDTO("city", "street", "suite", "zipcode"),
            it
        )
    }
}
