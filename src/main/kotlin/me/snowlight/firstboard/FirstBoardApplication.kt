package me.snowlight.firstboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirstBoardApplication

fun main(args: Array<String>) {
    runApplication<FirstBoardApplication>(*args)
}
