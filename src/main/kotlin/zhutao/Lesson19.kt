package zhutao

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 代码段2

fun main() {
//    main194()
//    main192()
    main193()
}

fun main191() = runBlocking {
    val channel = Channel<Int>()

    launch {
        (1..3).forEach {
            channel.send(it)
            logX("Send: $it")
        }

        channel.close() // 变化在这里
    }

    launch {
        for (i in channel) {
            logX("Receive: $i")
        }
        channel.receive()
    }

    logX("end")
}


// 代码段11

fun main192() = runBlocking {
    val channel: Channel<Int> = Channel()

    launch {
        (1..3).forEach {
            channel.send(it)
        }
    }

    // 调用4次receive()
    channel.receive()       // 1
    println("Receive: 1")
    channel.receive()       // 2
    println("Receive: 2")
    channel.receive()       // 3
    println("Receive: 3")
//    channel.receiveCatching()       // 永远挂起
    channel.receive()       // 永远挂起

    logX("end")
}


// 代码段11

fun main193() = runBlocking {
    // 1，创建管道
    val channel: ReceiveChannel<Int> = produce {
        // 发送3条数据
        (1..3).forEach {
            send(it)
        }
    }

    // 调用4次receive()
    val i1 = channel.receive() // 1
    println("i1 = $i1")
    channel.receive() // 2
    val i3 = channel.receiveCatching() // 3
    println("i3 = $i3")
//    channel.receive() // 异常
//    channel.receiveCatching() // 异常

    logX("end")
}

/*
输出结果：
ClosedReceiveChannelException: Channel was closed
*/

/*
输出结果
Receive: 1
Receive: 2
Receive: 3
*/

// 代码段14

fun main194() = runBlocking {
    val channel: ReceiveChannel<Int> = produce(capacity = 3) {
        (1..300).forEach {
            send(it)
            println("Send $it")
        }
    }

    // 变化在这里
    channel.consumeEach {
        println("Receive $it")
    }

    logX("end")
}