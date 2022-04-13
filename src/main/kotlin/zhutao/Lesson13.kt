package zhutao

import kotlinx.coroutines.*

fun main() {

//    main1()
//    main3()
    main5()

}

fun main131() = runBlocking {
    println(this)
    launch {
        repeat(3) {
            delay(1000L)
            println("Print-1:${Thread.currentThread().name}")
        }
    }

    launch {
        repeat(3) {
            delay(900L)
            println("Print-2:${Thread.currentThread().name}")
        }
    }
    // 避免提前结束
    delay(3000L)
}

fun main132() {
    GlobalScope.launch {
        println("Coroutine started!")

        delay(1000L)
        println("Hello World!")
    }
    println("Process end!")
    // 为了不让我们的主线程退出。
    Thread.sleep(1000L)
}


// runBlocking 阻塞线程
fun main133() {
    runBlocking {
        println("First:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello First!")
    }

    runBlocking {
        println("Second:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Second!")
    }

    runBlocking {
        println("Third:${Thread.currentThread().name}")
        delay(1000L)
        println("Hello Third!")
    }

    // 删掉了 Thread.sleep
    println("Process end!")
}

// runBlocking 返回结果
fun main134() {
    Dispatchers.Default
    val result = runBlocking {
        delay(1000L)
        // return@runBlocking 可写可不写
        return@runBlocking "Coroutine done!"
    }

    println("Result is: $result")
}

// async 不阻塞线程且可返回结果
fun main5() = runBlocking {
    println("In runBlocking:${Thread.currentThread().name}")

    val deferred: Deferred<String> = async {
        println("In async:${Thread.currentThread().name}")
        delay(1000L) // 模拟耗时操作
        return@async "Task completed!"
    }

    println("After async:${Thread.currentThread().name}")
    val result = deferred.await()
    println("Result is: $result")
}