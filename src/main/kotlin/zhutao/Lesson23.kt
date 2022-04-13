package zhutao

import kotlinx.coroutines.*

fun main() {
//    main231()
//    main236()
    main238()
}
// 代码段2

fun main231() = runBlocking {
    val job = launch(Dispatchers.Default) {
        var i = 0
        // 变化在这里
        while (isActive) {
//            Thread.sleep(500L)
            delay(300)
            i ++
            println("i = $i")
        }
    }

    delay(2000L)

    job.cancel()
//    job.join()

    println("End")
}


// 代码段7

fun main233() = runBlocking {

    val parentJob = launch(Dispatchers.Default) {
        launch {
            var i = 0
            while (true) {
                try {
                    delay(500L)
                } catch (e: CancellationException) {
                    println("Catch CancellationException")
                    // 1，注意这里
                    // throw e
                }
                i ++
                println("First i = $i")
            }
        }

        launch {
            var i = 0
            while (true) {
                delay(500L)
                i ++
                println("Second i = $i")
            }
        }
    }

    delay(2000L)

    parentJob.cancel()
    parentJob.join()

    println("End")
}


// 代码段8

fun main234() = runBlocking {
    try {
        launch {
            delay(100L)
            1 / 0 // 故意制造异常
        }
    } catch (e: ArithmeticException) {
        println("Catch: $e")
    }

    delay(500L)
    println("End")
}


// 代码段10

fun main235() = runBlocking {

    launch {
        try {
            delay(100L)
            1 / 0 // 故意制造异常
        } catch (e: ArithmeticException) {
            println("Catch: $e")
        }
    }

    delay(500L)
    println("End")
}



// 代码段13

fun main236() = runBlocking {
    val deferred = async {
        delay(100L)
        1 / 0
    }

    delay(500L)
    println("End")
}

/*
输出结果
崩溃：
Exception in thread "main" ArithmeticException: / by zero
*/

// 代码段14

fun main237() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.async {
        delay(100L)
        1 / 0
    }

    delay(500L)
    println("End")
}



// 代码段15

fun main238() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    // 变化在这里
    val deferred = scope.async {
        delay(100L)
        1 / 0
    }

    try {
        deferred.await()
    } catch (e: ArithmeticException) {
        println("Catch: $e")
    }

    delay(500L)
    println("End")
}

/*
输出结果
Catch: java.lang.ArithmeticException: / by zero
End
*/