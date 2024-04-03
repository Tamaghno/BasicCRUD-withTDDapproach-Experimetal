package `in`.tamcodes.learn.datasource.mockdatasource

import `in`.tamcodes.learn.datasource.IBankDataSource
import `in`.tamcodes.learn.dtoModel.Bank
import org.springframework.stereotype.Repository

@Repository
class BankDataSource : IBankDataSource {

    override fun retreiveBanks(): Collection<Bank> {
        return listOf(
            Bank("1234",1.2,1),
            Bank("12345",2.1,2),
            Bank("1235",3.1,20)
        )
    }

}