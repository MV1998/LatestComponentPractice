package com.example.latestcomponentpractice.interfaces

import android.util.Log

abstract class Printer(open val paperSize: Int) {

    open fun print() {}
}


class LGPrinter(override val paperSize: Int) : Printer(paperSize) {
    override fun print() {
        println("${javaClass.simpleName} print: $paperSize")
    }
}

class CanonPrinter(override val paperSize: Int) : Printer(paperSize) {
    override fun print() {
        println("${javaClass.simpleName} print: $paperSize")
    }
}

class PrinterManager(private val printer: Printer) {
    fun print() {
        printer.print()
    }
}

fun main() {
    PrinterManager(LGPrinter(192)).apply {
        print()
    }
    PrinterManager(CanonPrinter(232)).apply {
        print()
    }
}