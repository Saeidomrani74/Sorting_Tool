package sorting

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    var dataType = "word"
    var sortType = "natural"
    var inputFile = ""
    var outputFile = ""
    for (i in args.indices) {

        when (args[i]) {
            "-sortingType" -> {
                if ((args.size - 1 < i + 1) || !(args[i + 1] == "natural" || args[i + 1] == "byCount")) {
                    println("No sorting type defined!")
                    return
                }
                sortType = args[i + 1]
            }
            "-dataType" -> {
                if ((args.size - 1 < i + 1) || !(args[i + 1] == "long" || args[i + 1] == "line" || args[i + 1] == "word")) {
                    println("No data type defined!")
                    return
                }
                dataType = args[i + 1]
            }
            "-inputFile" -> {
                if (args.size - 1 >= i + 1)
                    inputFile = args[i + 1]
            }
            "-outputFile" -> {
                if (args.size - 1 >= i + 1)
                    outputFile = args[i + 1]
            }
        }

        when (args[i]) {
            "-sortingType", "-dataType", "natural", "byCount", "long", "line", "word", "-inputFile", "-outputFile", inputFile, outputFile -> continue
            else -> println("\"${args[i]}\" is not a valid parameter. It will be skipped.")
        }
    }

    sortData(dataType, sortType, inputFile, outputFile)
}

fun sortData(dataType: String, sortType: String, inputFile: String, outputFile: String) {
    val scanner = Scanner(System.`in`)
    val total: Int
    val inps = if (inputFile == "") emptyList() else File(inputFile).readLines()
    when (dataType) {
        "long" -> {
            val numbers = mutableListOf<Int>()
            if (inps.isEmpty()) {
                while (scanner.hasNext()) {
                    try {
                        numbers.add(scanner.nextInt())
                    } catch (e: Exception) {
                        println("\"${scanner.next()}\" is not a long. It will be skipped.")
                    }
                }
            } else {
                for (line in inps) {
//                    try {
                    val items = line.split(" ")
                    for (item in items) {
                        try {
                            numbers.add(item.toInt())
                        } catch (e: Exception) {
                            println("\"${scanner.next()}\" is not a long. It will be skipped.")
                        }
                    }
//                    } catch (e: Exception) {
//                        println("\"${scanner.next()}\" is not a long. It will be skipped.")
//                    }
                }
            }
            total = numbers.size
            val sorted = numbers.sorted()
            val numIns = mutableListOf<Int>()
            val timesIns = mutableListOf<Int>()
            numIns.add(sorted[0])
            timesIns.add(1)
            var map = mutableMapOf<Int, Int>()
            map[sorted[0]] = 1
            var str = ""

            for (j in sorted.indices) {
                str += sorted[j].toString() + " "
                if (j > 0) {
                    if (sorted[j] == sorted[j - 1]) map[sorted[j]] = map[sorted[j]]!! + 1
                    else {
                        map[sorted[j]] = 1
                    }
                }
            }

            map = map.toList().sortedBy { (_, v) -> v }.toMap().toMutableMap()
            var res = ""
            when (sortType) {
                "natural" -> res = "Total numbers: $total.\nSorted data: $str"
                "byCount" -> {
                    res = "Total numbers: $total.\n"
                    for ((k, v) in map)
                        res += "$k: $v time(s), ${v * 100 / total}%\n"
                }
            }
            if (outputFile == "") println(res)
            else File(outputFile).appendText(res)
        }
        "line" -> {
            val lines = (mutableListOf<String>())
            if (inps.isEmpty()) {
                while (scanner.hasNextLine())
                    lines.add(scanner.nextLine())
            } else {
                for (line in inps)
                    lines.add(line)
            }
            total = lines.size
            val sorted = lines.sorted()
            val linesIns = mutableListOf<String>()
            val timesIns = mutableListOf<Int>()
            linesIns.add(sorted[0])
            timesIns.add(1)
            var map = mutableMapOf<String, Int>()
            map[sorted[0]] = 1
            var str = ""

            for (j in sorted.indices) {
                str += "\n" + sorted[j]
                if (j > 0) {
                    if (sorted[j] == sorted[j - 1]) map[sorted[j]] = map[sorted[j]]!! + 1
                    else {
                        map[sorted[j]] = 1
                    }
                }
            }

            map = map.toList().sortedBy { (_, v) -> v }.toMap().toMutableMap()

            var res = ""
            when (sortType) {
                "natural" -> res = "Total lines: $total.\nSorted data: $str"
                "byCount" -> {
                    res = "Total lines: $total.\n"
                    for ((k, v) in map)
                        res += "$k: $v time(s), ${v * 100 / total}%\n"
                }
            }
            if (outputFile == "") println(res)
            else File(outputFile).appendText(res)
        }
        "word" -> {
            val words = (mutableListOf<String>())

            if (inps.isEmpty()) {
                while (scanner.hasNext())
                    words.add(scanner.next())
            } else {
                for (line in inps) {
                    val items = line.split(" ")
                    for (item in items)
                        words.add(item)
                }
            }

            total = words.size
            val sorted = words.sorted()
            val wordsIns = mutableListOf<String>()
            val timesIns = mutableListOf<Int>()
            wordsIns.add(sorted[0])
            timesIns.add(1)
            var map = mutableMapOf<String, Int>()
            map[sorted[0]] = 1
            var str = ""

            for (j in sorted.indices) {
                str += " " + sorted[j]
                if (j > 0) {
                    if (sorted[j] == sorted[j - 1]) map[sorted[j]] = map[sorted[j]]!! + 1
                    else {
                        map[sorted[j]] = 1
                    }
                }
            }

            map = map.toList().sortedBy { (_, v) -> v }.toMap().toMutableMap()
            var res = ""
            when (sortType) {
                "natural" -> res = "Total words: $total.\nSorted data:$str"
                "byCount" -> {
                    res = "Total words: $total.\n"
                    for ((k, v) in map)
                        res += "$k: $v time(s), ${v * 100 / total}%\n"
                }
            }
            if (outputFile == "") println(res)
            else File(outputFile).appendText(res)
        }
    }
}