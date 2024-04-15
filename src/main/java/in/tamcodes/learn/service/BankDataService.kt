package `in`.tamcodes.learn.service

import `in`.tamcodes.learn.datasource.mockdatasource.BankDataSource
import `in`.tamcodes.learn.dtoModel.Bank
import org.springframework.stereotype.Service

@Service
class BankDataService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> {
        return bankDataSource.retreiveBanks()
    }

    fun getBank(accid : String) : Bank{
       return bankDataSource.retreiveBank(accid)
    }

    fun addBank(bank: Bank) {
        bankDataSource.addBank(bank)
    }

    fun modifyBank(bank: Bank) {
        bankDataSource.modifyBank(bank)
    }

    fun removeBank(accId: String) {
        bankDataSource.removeBank(accId)

    }


}