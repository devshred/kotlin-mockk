package com.example.mockk

import org.springframework.stereotype.Service

@Service
class SampleService {
    object PreconditionChecker {
        fun check(sample: Sample) {
            if (sample.text.contains("stupid")) {
                throw StupidTextException("This text contains stupidity: ${sample.text}")
            }
        }
    }

    private val sampleStore = arrayListOf<Sample>()

    fun add(sample: Sample) {
        PreconditionChecker.check(sample)
        sampleStore.add(sample)
    }

    fun all() = sampleStore
}

data class Sample(val text: String)

class StupidTextException(message: String) : RuntimeException(message)
