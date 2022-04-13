package zhutao

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.*
// 代码段3

fun main() {
    testStartCoroutine()
    Thread.sleep(2000L)
}

val block = suspend {
    println("Hello!")
    delay(1000L)
    println("World!")
    "Result"
}

private fun testStartCoroutine() {

    val continuation = object : Continuation<String> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
            println("Result is: ${result.getOrNull()}")
        }
    }

    block.startCoroutine(continuation)
}

private fun testCreateCoroutine() {

    val continuation = object : Continuation<String> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
            println("Result is: ${result.getOrNull()}")
        }
    }
    val coroutine = block.createCoroutine(continuation)
    coroutine.resume(Unit)
}

// 代码段15
private fun main34() {
    testLaunch()
    Thread.sleep(2000L)
}

private fun testLaunch() {
    val scope = CoroutineScope(Job())
    scope.launch {
        println("Hello!")
        delay(timeMillis = 1000L)
        println("World!")
    }
}


private suspend fun func2(): String {
    println("Hello!")
    delay(1000L)
    println("World!")
    return "Result"
}

private fun testStartCoroutineForSuspend() {
    val block = ::func2

    val continuation = object : Continuation<String> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<String>) {
            println("Result is: ${result.getOrNull()}")
        }
    }

    block.startCoroutine(continuation)
}
