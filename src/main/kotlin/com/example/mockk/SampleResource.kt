package com.example.mockk

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SampleResource(val service: SampleService) {
    @GetMapping
    fun all() = service.all()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody sample: Sample) {
        service.add(sample)
    }
}

@ControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleStupidTextException(ex: StupidTextException) = ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
}
