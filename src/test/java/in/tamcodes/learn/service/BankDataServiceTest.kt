package `in`.tamcodes.learn.service

import `in`.tamcodes.learn.datasource.mockdatasource.BankDataSource
import `in`.tamcodes.learn.dtoModel.Bank
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BankDataServiceTest{

    private val  bankDataSource :  BankDataSource = mockk()
//    private val  bankDataSource :  BankDataSource = mockk(relaxed = true)
    private val bankDataService = BankDataService(bankDataSource)

    @Test
    fun `should call its datasource`(){
        //given
        every { bankDataSource.retreiveBanks() } returns listOf(Bank("12",1.2,22))

        //when
        val banks = bankDataService.getBanks()
        //then
//        assertThat(banks).isNotEmpty()

        verify(exactly = 1) {bankDataSource.retreiveBanks()}

    }
}