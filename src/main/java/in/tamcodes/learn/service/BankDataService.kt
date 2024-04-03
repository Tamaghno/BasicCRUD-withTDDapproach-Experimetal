package `in`.tamcodes.learn.service

import `in`.tamcodes.learn.datasource.mockdatasource.BankDataSource
import `in`.tamcodes.learn.dtoModel.Bank
import org.springframework.stereotype.Service

@Service
class BankDataService(private val bankDataSource: BankDataSource) {
    fun getBanks(): Collection<Bank> {
        return bankDataSource.retreiveBanks()
    }


}