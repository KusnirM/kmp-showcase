package com.mk.kmpshowcase.data.dto

import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleDTOTest : BaseDTOTest() {

    @Test
    fun testSuccess() = runTest<ExampleDTO>(
        """
    {
      "id": 1,
      "name": "Test Example"
    }
    """
    ) {
        assertEquals(
            ExampleDTO(
                id = 1,
                name = "Test Example"
            ),
            it
        )
    }
}
