package `in`.tamcodes.learn.datasource

import `in`.tamcodes.learn.dtoModel.Bank

interface IBankDataSource {

    fun retreiveBanks() : Collection<Bank>
    abstract fun retreiveBank(accid: String) : Bank
    abstract fun addBank(bank: Bank)
    abstract fun modifyBank(bank: Bank)
    abstract fun removeBank(accId: String)
}