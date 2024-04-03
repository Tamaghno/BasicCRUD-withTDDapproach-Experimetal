package `in`.tamcodes.learn.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("get/demopage")
class DemoController {

    @GetMapping
    fun getDemoPage() : String{
        return "Demo Page"
    }
}