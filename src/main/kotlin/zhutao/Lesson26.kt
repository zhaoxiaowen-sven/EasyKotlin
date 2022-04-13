@file:Suppress("UNCHECKED_CAST")

package zhutao

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn


// 代码段1
suspend fun testCoroutine() {
    val user = getUserInfo()
    val friendList = getFriendList(user)
    val feedList = getFeedList(user, friendList)
//    log(feedList)
}

//挂起函数
// ↓
suspend fun getUserInfo(): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "BoyCoder"
}

//挂起函数
// ↓
suspend fun getFriendList(user: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "Tom, Jack"
}

//挂起函数
// ↓
suspend fun getFeedList(user: String, list: String): String {
    withContext(Dispatchers.IO) {
        delay(1000L)
    }
    return "{FeedList..}"
}


// 代码段6

// suspend 修饰
// ↓
suspend fun noSuspendFriendList(user: String): String {
    // 函数体跟普通函数一样
    return "Tom, Jack"
}


// 代码段2

fun main() = runBlocking {
//    val result = getLengthSuspend("Kotlin")
//    println(result)
//    main2()

//    main4()

    val result = testSuspendCoroutine()
    println(result)
}

//suspend fun getLengthSuspend(text: String): Int = suspendCoroutine { continuation->
//    thread {
//        // 模拟耗时
//        Thread.sleep(1000L)
//        continuation.resume(text.length)
//    }
//}


// 代码段3

// 变化在这里
fun main2() {
    val func = ::getLengthSuspend as (String, Continuation<Int>) -> Any?

    func("kotlin", object : Continuation<Int> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println(result.getOrNull())
        }
    })

    // 防止程序提前结束
    Thread.sleep(2000L)
}

suspend fun getLengthSuspend(text: String): Int = suspendCoroutine { continuation ->
    thread {
        // 模拟耗时
        Thread.sleep(1000L)
        continuation.resume(text.length)
    }
}


// 代码段4

// 变化在这里
fun main3() {
    func("Kotlin", object : Callback<Int> {
        override fun resume(result: Int) {
            println(result)
        }
    })

    // 防止程序提前结束
    Thread.sleep(2000L)
}

fun func(text: String, callback: Callback<Int>) {
    thread {
        // 模拟耗时
        Thread.sleep(1000L)
        callback.resume(text.length)
    }
}

interface Callback<T> {
    fun resume(value: T)
}


// 代码段9

fun main4() = runBlocking {
    val result = testNoSuspendCoroutine()
    println(result)
}

private suspend fun testNoSuspendCoroutine() =
    suspendCoroutineUninterceptedOrReturn<String> { continuation ->
    return@suspendCoroutineUninterceptedOrReturn "Hello!"
}

private suspend fun testSuspendCoroutine() = suspendCoroutineUninterceptedOrReturn<String> {
        continuation ->
    thread {
        Thread.sleep(1000L)
        continuation.resume("Hello!")
    }
    return@suspendCoroutineUninterceptedOrReturn kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
}

