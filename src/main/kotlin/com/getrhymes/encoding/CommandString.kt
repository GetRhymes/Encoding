package com.getrhymes.encoding

class Encoding(val command: String, val key: String, val inputFile: String) {

    fun readerStr (str: String) {
        val partOfStr = str.split(" ")
        if (partOfStr.size != 6) throw IllegalArgumentException()
        
    }
}














//Вариант 2 — шифрация
//Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
// Выходной файл указывается как -o filename.txt, по умолчанию
// имя формируется из имени входного файла с добавлением расширения.
//Алгоритм шифрации XOR. Ключ указывается после -c или -d в
// шестнадцатеричной системе, длина ключа — любое целое количество байт.
//Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]