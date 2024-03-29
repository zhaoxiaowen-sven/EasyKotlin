package zhutao

import kotlinx.coroutines.*

// 代码段5

private fun testScope() {
    // 1
    val scope = CoroutineScope(Job())
    scope.launch {
        launch {
            delay(1000000L)
            logX("Inner")  // 不会执行
        }
        logX("Hello!")
        delay(1000000L)
        logX("World!")  // 不会执行
    }

    Thread.sleep(500L)
    // 2
    scope.cancel()
}

//public interface CoroutineScope {
//    public val coroutineContext: CoroutineContext
//}
//
//public interface Job : CoroutineContext.Element {}