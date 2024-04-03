package `in`.tamcodes.learn.datasource.mockdatasource

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BankDataSourceTest{

    private val mockBankDataSource = BankDataSource()

    @Test
    fun `should return a list of Banks`(){

        //given

        //when
        val banks = mockBankDataSource.retreiveBanks()

        //then
        assertThat(banks).isNotEmpty()
        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should return some mock data`(){
        //given

        //when
        val banks = mockBankDataSource.retreiveBanks()

        //then
        assertThat(banks).allMatch { it.accountId.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}