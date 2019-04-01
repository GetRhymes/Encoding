package com.getrhymes.encoding

import java.lang.IllegalArgumentException

class CommandString(var command: String, var key: String, var inputFile: String) {

    fun readerStr (str: Array<String>) {
            if (str.size != 6) throw IllegalArgumentException()
            command = str[1]
            key = str[2]
            inputFile = str[3]
    }
}














//Вариант 2 — шифрация
//Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
// Выходной файл указывается как -o filename.txt, по умолчанию
// имя формируется из имени входного файла с добавлением расширения.
//Алгоритм шифрации XOR. Ключ указывается после -c или -d в
// шестнадцатеричной системе, длина ключа — любое целое количество байт.
//Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]