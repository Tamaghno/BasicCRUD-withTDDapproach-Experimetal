package `in`.tamcodes.learn.controllers

import `in`.tamcodes.learn.dtoModel.Bank
import `in`.tamcodes.learn.service.BankDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/api/banks")
class BankController(private  val service: BankDataService) {

    @ExceptionHandler(NoSuchElementException :: class)
    fun handleNotFound(e : NoSuchElementException) : ResponseEntity<String>{
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException :: class)
    fun handleAlreadyExists(e : IllegalArgumentException) : ResponseEntity<String>{
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping()
    fun getBank() : Collection<Bank>{
       return  service.getBanks()
    }

    @GetMapping("/{accId}")
    fun getBankFromAccId(@PathVariable accId : String ) : Bank{
        return service.getBank(accId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank : Bank) : Bank{
        service.addBank(bank)
        return bank
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    fun patchBank(@RequestBody bank: Bank) : Bank{
        service.modifyBank(bank)
        return bank
    }

    @DeleteMapping("/{accId}")
    @ResponseStatus(HttpStatus.FOUND)
    fun deleteBank(@PathVariable accId: String) : Collection<Bank>{
        service.removeBank(accId)
        return service.getBanks()
    }


}