package com.getrhymes.encodingTest

import com.getrhymes.encoding.CommandString
import com.getrhymes.encoding.encoder
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.File


class TestEncoding {

    @Test
    fun test1() {
        fun readOutputFile(): List<String> {
            val string = CommandString("B12CF", "inputFile.txt", "resultTest1.txt")
            encoder(string)
            return File(string.outputFile).readLines()
        }
        assertEquals(listOf(
                "ᛐᚏ\u16F7\u16FD\u16FAᚍ ᛠ ᛒ\u16F7\u16F5\u16F7ᚍ\u16FF ᛭\u16FFᚏ\u16FFᚎ\u16FA\u16F2\u16F5\u16F1",
                ""), readOutputFile())

        fun readOutputFile2(): List<String> {
            val string = CommandString("B12CF", "resultTest1.txt", "resultTest2.txt")
            encoder(string)
            return File(string.outputFile).readLines()
        }
        assertEquals(listOf("Привет Я Никита Тарасенко", ""), readOutputFile2())
    }
}