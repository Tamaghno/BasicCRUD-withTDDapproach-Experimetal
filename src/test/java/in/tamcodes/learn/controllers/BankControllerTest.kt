package `in`.tamcodes.learn.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import `in`.tamcodes.learn.dtoModel.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest  @Autowired constructor(
   val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){


    val baseUrl = "/api/banks"



    @Nested
    @DisplayName("Remove existing bank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class DeleteExistingBank{

        @Test
//        @DirtiesContext
        fun `should delete existing bank`(){
            //given
            val bankAccIdToRemove : String = "1234"
            val banks = listOf(
//                Bank("1234",1.2,1),
                Bank("12345",2.1,2),
                Bank("1235",3.1,20)
            )

            //when
            val performDelete = mockMvc.delete("$baseUrl/$bankAccIdToRemove")

            //then
            performDelete.andDo { print() }
                .andExpect {
                    status { isFound() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(banks))
                    }
                }
        }

        @Test
        fun `should return Bad Request if bank doesn't exist`(){

            //given
            val bankAccIdToRemove = "invalidID"

            //when
            val performDelete = mockMvc.delete("$baseUrl/$bankAccIdToRemove")

            //then
            performDelete.andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }

        }
    }

    @Nested
    @DisplayName("ReturnAllBanks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class ReturnAllBanks{
        @Test

        fun `should return all banks`(){
            //when/then
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { MediaType.APPLICATION_JSON }
//                    jsonPath("$[0].accountId"){ value("1234") }
                }

        }
    }

    @Nested
    @DisplayName("ReturnBankWithAccID")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class ReturnBankWithAccID{
        @Test
        fun `should return the bank with given accId`(){

            //given
            val accId = "1234"

            //when/then
            mockMvc.get("$baseUrl/$accId")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { MediaType.APPLICATION_JSON }
                    jsonPath("$.trust") {value(4.5)}
                    jsonPath("$.transactionFee"){value(60)}
                }


        }

        @Test
        fun `should return NOT FOUND if the bank with given accId doesn't exist`(){

            //given
            val accId = "blah"

            //when/then
            mockMvc.get("$baseUrl/$accId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }


        }
    }

    @Nested
    @DisplayName("Post a bank")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class TestPostMapping{

        @Test
        fun `should post a new bank`(){

            //given
            val bank = Bank("123",1.2,22)

            //when
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }

            //then
            performPost.andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountId"){value("123") }
                    jsonPath("$.trust"){value(1.2)}
                    jsonPath("$.transactionFee"){value(22)}
                }

        }

        @Test
        fun `should throw error if the new bank already exists`(){

            //given
            val bank = Bank("1234",1.2,22)

            //when
            val performPost = mockMvc.post(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }

            //then
            performPost.andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }

        }

    }

    @Nested
    @DisplayName("Patch checking")
    @TestInstance( Lifecycle.PER_CLASS)
    inner class PatchAnExistingBank{
        @Test
        fun `should update existing bank`(){
            //given
            val updatedBank = Bank("1234",4.5,60)

            //when
            val performPatch = mockMvc.patch(baseUrl){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }

            //then
            performPatch.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }

            mockMvc.get("$baseUrl/${updatedBank.accountId}")
                .andDo { print() }
                .andExpect { content { json(objectMapper.writeValueAsString(updatedBank)) } }

        }

        @Test
        fun `should return NOT FOUND if bank doesn't exist`(){
            //given
            val providedBank = Bank("qwerty",1.2,2)

            //when
            val performPost = mockMvc.patch("$baseUrl"){
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(providedBank)
            }


            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }



        }

    }







}