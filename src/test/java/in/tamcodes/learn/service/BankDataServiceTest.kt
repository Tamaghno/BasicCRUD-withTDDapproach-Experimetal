package `in`.tamcodes.learn.service

import `in`.tamcodes.learn.datasource.mockdatasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BankDataServiceTest{

    private val  bankDataSource :  BankDataSource = mockk()
    private val bankDataService = BankDataService(bankDataSource)

    @Test
    fun `should return a list of banks`(){
        //given
        every { bankDataSource.retreiveBanks() } returns emptyList()

        //when
        val banks = bankDataService.getBanks()
        //then
//        assertThat(banks).isNotEmpty()

        verify(exactly = 1) {bankDataSource.retreiveBanks()}

    }
}