package zhutao

import kotlinx.coroutines.*
import java.text.ChoiceFormat.nextDouble
import java.util.*
import kotlin.random.Random.Default.nextDouble

fun main() = runBlocking{
//    main161()

//    main162()

//    main163()

//    main164()

    val job = launch {
        println("start")
    }

    job.join()
}

//// 代码段2
//
//fun main161() = runBlocking {
//    val job = launch {
//        delay(1000L)
//    }
//    job.log()       // ①
//    job.cancel()    // ②
//    job.log()       // ③
//    delay(1500L)
//}
//
///**
// * 打印Job的状态信息
// */
//fun Job.log() {
//    logX("""
//        isActive = $isActive
//        isCancelled = $isCancelled
//        isCompleted = $isCompleted
//    """.trimIndent())
//}
//
///**
// * 控制台输出带协程信息的log
// */
//fun logX(any: Any?) {
//    println("""
//================================
//$any
//Thread:${Thread.currentThread().name}
//================================""".trimIndent())
//}
//
//
//
//
//// 代码段3
//
//private fun main162() = runBlocking {
//    //                  变化在这里
//    //                      ↓
//    val job = launch(start = CoroutineStart.LAZY) {
//        logX("Coroutine start!")
//        delay(1000L)
//    }
//    delay(500L)
//    job.log()
//    job.start()     // 变化在这里
//    job.log()
//    delay(500L)
//    job.cancel()
//    delay(500L)
//    job.log()
//    delay(2000L)
//    logX("Process end!")
//}
//
//
//
//// 代码段6
//
//fun main163() = runBlocking {
//    suspend fun download() {
//        // 模拟下载任务
//        val time = (Random().nextDouble() * 1000).toLong()
//        logX("Delay time: = $time")
//        delay(time)
//    }
//    val job = launch(start = CoroutineStart.LAZY) {
//        logX("Coroutine start!")
//        download()
//        logX("Coroutine end!")
//    }
//    delay(500L)
//    job.log()
//    job.start()
//    job.log()
//    job.invokeOnCompletion {
//        job.log() // 协程结束以后就会调用这里的代码
//    }
//    job.join()      // 等待协程执行完毕
//    logX("Process end!")
//
////    job.isCancelled
//
////    job.isCompleted
//}
//
//
//
//// 代码段8
//
//fun main164() = runBlocking {
//    val deferred = async(this.coroutineContext, CoroutineStart.LAZY) {
//        logX("Coroutine start!")
//        delay(1000L)
//        logX("Coroutine end!")
//        "Coroutine result!"
//    }
//    deferred.log()
//    delay(300)
//    deferred.start()
//    deferred.log()
//    val result = deferred.await()
//    deferred.log()
//
//    println("Result = $result")
//    logX("Process end!")
//}