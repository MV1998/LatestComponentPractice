package com.example.latestcomponentpractice.interfaces

import android.util.Log

/*
screen  - resolution, height, width, pixel
battery, company,
 */

interface S {
    fun SS()
}

abstract class Resolution {
}

abstract class Mobile {
    val resolution: Resolution? = null
}

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

    val s = Singleton()
    val ss = Singleton()
    println("${s.hashCode()}  and ${ss.hashCode()}")
}

class Singleton constructor() {

//    companion object {
//        private var INSTANCE : Singleton? = null
//        fun  getInstance() : Singleton {
//            if (INSTANCE == null) {
//                INSTANCE = Singleton()
//                return INSTANCE!!
//            }
//            return INSTANCE!!
//        }
//    }
}