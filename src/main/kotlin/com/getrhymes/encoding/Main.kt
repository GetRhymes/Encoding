package com.getrhymes.encoding

import java.io.File


fun main(args: Array<String>) {
    val string = CommandString("", "", "")
    string.readerStr(args)
    encoder(CommandString(string.command, string.key, string.inputFile))
}


fun encoder(keyAndFile: CommandString) {
    val writer = File("outputFile.txt").bufferedWriter()
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
                listString2NS = when {
                    keyNS2.length == charIn2NS.length -> {
                        val mutList = mutableListOf<String>()
                        for (el in 0 until partOfStringChar.size) {
                            val sumXOR = ((partOfStringChar[el].toInt() + partOfStringKey[el].toInt()) % 2).toString()
                            mutList.add(sumXOR) // после цикла получаем двоичный код буквы зашифрованного слова
                        }
                        mutList
                    }
                    partOfStringChar.size > partOfStringKey.size -> translate(partOfStringChar, partOfStringKey)
                    else -> translate(partOfStringKey, partOfStringChar) // двоичный код буквы зашифр слова
                }
                listChar.add(listString2NS.joinToString(separator = "").toInt(2).toChar())
            }
            words.add(listChar.joinToString(separator = ""))
        }
        writer.write(words.joinToString(separator = " "))
        writer.newLine()
    }
    writer.close()
}

fun revert(n: CharArray): CharArray {
    var strInv = charArrayOf()
    for (i in n.size - 1 downTo 0) {
        strInv+= n[i]
    }
    return strInv
} // возвращает обратный порядок букв

fun translate(partOfString1: CharArray, partOfString2: CharArray): List<String> {
    val list = mutableListOf<String>()
    val difference = partOfString1.size - partOfString2.size
    var revertStr = revert(partOfString2)
    for (i in 0 until difference) revertStr += '0'
    val keyEqualCharStr = revert(revertStr)
    for (el in 0 until keyEqualCharStr.size) {
        val sumXOR = ((keyEqualCharStr[el].toInt() + partOfString1[el].toInt()) % 2).toString()
        list.add(sumXOR)
    }
    return list
} // возвращает букву зашифрованного слова в двоичном коде

