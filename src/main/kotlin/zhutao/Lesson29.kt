package zhutao

import kotlinx.coroutines.*


// 代码段3

fun main() {
    testLaunch()
    Thread.sleep(2000L)
}

private fun testLaunch() {
    val scope = CoroutineScope(Job())
    Dispatchers.Default
    scope.launch{
        logX("Hello!")
        delay(1000L)
        logX("World!")
    }
}

/**
 * 控制台输出带协程信息的log
 */
fun logX(any: Any?) {
    println(
        """
================================
$any
Thread:${Thread.currentThread().name}
================================""".trimIndent()
    )
}
