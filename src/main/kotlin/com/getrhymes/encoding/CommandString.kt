package com.getrhymes.encoding

import java.lang.IllegalArgumentException

class CommandString(var key: String, var inputFile: String, var outputFile: String) {

    fun readerStr (str: Array<String>) {
        key = str[2]
        inputFile = str[3]
        outputFile =
                if (str.size == 6 ) str[5]
                else "outputFile.txt"

    }
}














//Вариант 2 — шифрация
//Шифрация (-c) или дешифрация (-d) указанного (в аргументе командной строки) файла.
// Выходной файл указывается как -o filename.txt, по умолчанию
// имя формируется из имени входного файла с добавлением расширения.
//Алгоритм шифрации XOR. Ключ указывается после -c или -d в
// шестнадцатеричной системе, длина ключа — любое целое количество байт.
//Command Line: ciphxor [-c key] [-d key] inputname.txt [-o outputname.txt]