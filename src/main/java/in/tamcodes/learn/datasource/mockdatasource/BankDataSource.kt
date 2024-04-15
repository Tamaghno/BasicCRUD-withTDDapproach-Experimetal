package `in`.tamcodes.learn.datasource.mockdatasource

import `in`.tamcodes.learn.datasource.IBankDataSource
import `in`.tamcodes.learn.dtoModel.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class BankDataSource : IBankDataSource {

    val banks = mutableListOf(
        Bank("1234",1.2,1),
        Bank("12345",2.1,2),
        Bank("1235",3.1,20)
    )

    override fun retreiveBanks(): Collection<Bank> {
        return banks
    }

    override fun retreiveBank(accid: String) : Bank {
        return banks.firstOrNull() { it.accountId == accid } ?: throw NoSuchElementException("No bank with this account number")
    }

    override fun addBank(bank: Bank) {

        if (banks.any{ it.accountId == bank.accountId})
            throw IllegalArgumentException("Bank with account id ${bank.accountId} already exists")

        banks.add(bank)
    }

    override fun modifyBank(bank: Bank) {
        val currentBank = banks.firstOrNull() { it.accountId == bank.accountId } ?: throw NoSuchElementException("No bank with this account number")

        banks.remove(currentBank)

        banks.add(bank)
    }

    override fun removeBank(accId: String) {
        val bankToBeRemoved = banks.firstOrNull(){ it.accountId == accId } ?: throw java.util.NoSuchElementException("No bank with this account number")

        banks.remove(bankToBeRemoved)
    }

}