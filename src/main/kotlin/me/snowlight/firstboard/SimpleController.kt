package me.snowlight.firstboard

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleController {
    @GetMapping("/sample")
    fun simple(id: String) = "Success"
}
