package com.example.latestcomponentpractice

import android.util.Log
import com.example.latestcomponentpractice.Models.AcerMonitorBox
import com.example.latestcomponentpractice.Models.AmazonDeliveryBox
import com.example.latestcomponentpractice.Models.Box
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

//    var array =  AnyArray.toTArray<Int>(100)
    val boxList : ArrayList<Box> = ArrayList()
    repeat(100) {
        if (it % 2 == 0) {
            boxList.add(AcerMonitorBox(id = it, height = 100.0, width = 100.0).apply {
                name = "AcerMonitor"
                isPackaged = it % 3 == 0
            })
        }else{
            boxList.add(AmazonDeliveryBox(id = it, height = 100.0, width = 100.0).apply {
                name = "Amazon Delivery Box"
                isPackaged = it % 5 == 0
            })
        }
    }
//    println("main: ${boxList.size}")
//    for (box in boxList) {
//        runBlocking {
//            box.startPackaging()
//        }
//    }
//
//    Sample(12,2)

    var arr = intArrayOf(1,0,1,0)
    var totalCount = arr.size.toLong()
    for(num in arr.indices) {
        for (second in num+1..<arr.size) {
            var isBreak = false
            var subArray = mutableListOf<Int>()
            for(it in num..second) {
                subArray.add(arr[it])
                if (subArray.size > 1 && subArray[subArray.size-1] == subArray[subArray.size-2]) {
                    isBreak = true
                    break
                }
            }
            subArray.clear()
            if(!isBreak) {
                totalCount++
            }
        }
    }
    println("totalCount $totalCount")
}

class Sample( a : Int,b : Int) {

    constructor(s: Int, d : Double) : this(s, d.toInt()) {
        val mm = s
    }

    init {
        val sss = a
    }
}