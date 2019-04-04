package com.getrhymes.encoding

import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {
    val string = CommandString("", "", "")
    string.readerStr(args)
    encoder(CommandString(string.key, string.inputFile, string.outputFile))
}


fun encoder(keyAndFile: CommandString) {
    val writer = File(keyAndFile.outputFile).bufferedWriter()
    val keyNS2 = keyAndFile.key
            .toInt(16)
            .toString(2) // Ключ в 2сс
    val text = File(keyAndFile.inputFile).readLines()
    for (line in text) {
        val words = mutableListOf<String>()
        val partOfLine = line.split(" ") // сплитим строку текса и получаем отдельные слова
        for (part in partOfLine) {
            val charArray = part.toCharArray() //представляем слово как чаровский массив
            val listChar = mutableListOf<Char>()
            for (char in charArray) {
                var listString2NS: List<String>
                val charIn2NS = char.toInt().toString(2) // переводим каждую букву слова в 2сс
                val partOfStringKey = keyNS2.toCharArray()
                val partOfStringChar = charIn2NS.toCharArray()
                listString2NS = translate(partOfStringChar, partOfStringKey)
                listChar.add(listString2NS.joinToString(separator = "").toInt(2).toChar())
            }
            words.add(listChar.joinToString(separator = ""))
        }
        writer.write(words.joinToString(separator = " "))
        writer.newLine()
    }
    writer.close()
}

fun translate(partOfStringChar: CharArray, partOfStringKey: CharArray): List<String> {
    val difference = abs(partOfStringChar.size - partOfStringKey.size)
    fun sumXOR (EqualCharStr: CharArray, partOfString: CharArray): MutableList<String> {
        val mutList = mutableListOf<String>()
        for (el in 0 until EqualCharStr.size) {
            val sumXOR = ((EqualCharStr[el].toInt() + partOfString[el].toInt()) % 2).toString()
            mutList.add(sumXOR)
        }
        return mutList
    }
    fun revertString(partOfStringChar1: CharArray): CharArray {
        var revertStr = partOfStringChar1.reversedArray()
        for (i in 0 until difference) revertStr += '0'
        return revertStr.reversedArray()
    }
    return when {
        partOfStringChar.size > partOfStringKey.size -> {
            val revertStr = revertString(partOfStringKey)
            sumXOR(revertStr, partOfStringChar)
        }
        partOfStringChar.size < partOfStringKey.size -> {
            val revertStr =revertString(partOfStringChar)
            sumXOR(revertStr, partOfStringKey)
        }
        else -> sumXOR(partOfStringChar, partOfStringKey)
    }
} // возвращает букву зашифрованного слова в двоичном коде

