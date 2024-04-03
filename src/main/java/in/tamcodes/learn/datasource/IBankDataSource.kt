package `in`.tamcodes.learn.datasource

import `in`.tamcodes.learn.dtoModel.Bank

interface IBankDataSource {

    fun retreiveBanks() : Collection<Bank>
}