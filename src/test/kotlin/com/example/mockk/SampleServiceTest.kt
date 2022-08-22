package com.example.mockk

import io.mockk.every
import io.mockk.just
import io.mockk.mockkObject
import io.mockk.runs
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SampleServiceTest {
    private val service = SampleService()

    @BeforeAll
    fun setup() {
        mockkObject(SampleService.PreconditionChecker)
    }

    @Test
    fun `check that no exception was thrown if PreconditionChecker stays silent`() {
        every { SampleService.PreconditionChecker.check(any()) } just runs
        service.add(Sample("whatever stupid things"))
    }

    @Test
    fun `check that StupidTextException was thrown if PreconditionChecker finds something stupid`() {
        every { SampleService.PreconditionChecker.check(any()) } throws StupidTextException("something stupid")
        assertThrows<StupidTextException> { service.add(Sample("whatever")) }
    }
}
