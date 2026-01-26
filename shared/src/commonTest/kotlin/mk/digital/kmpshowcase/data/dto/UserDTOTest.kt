package mk.digital.kmpshowcase.data.dto

import kotlin.test.Test
import kotlin.test.assertEquals

class UserDTOTest : BaseDTOTest() {

    @Test
    fun testSuccess() = runTest<UserDTO>(
        """
    {
      "address": {
        "city": "Test City",
        "street": "Test Street",
        "suite": "Suite 1",
        "zipcode": "12345"
      },
      "email": "test@example.com",
      "id": 1,
      "name": "Test User"
    }
    """
    ) {
        assertEquals(
            UserDTO(
                address = AddressDTO("Test City", "Test Street", "Suite 1", "12345"),
                email = "test@example.com",
                id = 1,
                name = "Test User"
            ),
            it
        )
    }
}
