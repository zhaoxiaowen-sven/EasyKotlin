package zhutao

import kotlinx.coroutines.*

// 代码段2

fun main() = runBlocking {
//    val user = getUserInfo()
//    logX(user)
    main172()
}

//suspend fun getUserInfo(): String {
//    logX("Before IO Context.")
//    withContext(Dispatchers.IO) {
//        logX("In IO Context.")
//        delay(1000L)
//    }
//    logX("After IO Context.")
//    return "BoyCoder"
//}

//fun logX(any: Any?) {
//    println(
//        """
//================================
//$any
//Thread:${Thread.currentThread().name}
//================================""".trimIndent()
//    )
//}

// 代码段14

@OptIn(ExperimentalStdlibApi::class)
fun main172() = runBlocking {
    // 注意这里
    val scope = CoroutineScope(CoroutineName("main172") + Job() + Dispatchers.IO)

    scope.launch {
        // 注意这里
        logX(coroutineContext[CoroutineDispatcher])
        logX(coroutineContext[CoroutineName])
        delay(1000L)
        logX("First end!")  // 不会执行
    }

    delay(500L)
    scope.cancel()
    delay(1000L)
}