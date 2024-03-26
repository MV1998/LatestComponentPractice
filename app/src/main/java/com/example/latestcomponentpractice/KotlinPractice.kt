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
    println("main: ${boxList.size}")
//    for (box in boxList) {
//        runBlocking {
//            box.startPackaging()
//        }
//    }

}