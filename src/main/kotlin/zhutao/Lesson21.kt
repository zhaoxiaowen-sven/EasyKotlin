package zhutao

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select


fun main() {
//    main211()
    main213()
}
// 代码段9
fun main211() = runBlocking {
    val startTime = System.currentTimeMillis()
    val channel1 = produce {
        send("1")
        delay(200L)
        send("2")
        delay(200L)
        send("3")
        delay(150L)
    }

    val channel2 = produce {
        delay(100L)
        send("a")
        delay(200L)
        send("b")
        delay(200L)
        send("c")
    }

    suspend fun selectChannel(channel1: ReceiveChannel<String>,
                              channel2: ReceiveChannel<String>): String = select<String> {
        // 1， 选择channel1
        channel1.onReceive{
            it.also { println(it) }
        }
        // 2， 选择channel1
        channel2.onReceive{
            it.also { println(it) }
        }
    }

    repeat(6){// 3， 选择6次结果
        selectChannel(channel1, channel2)
    }

    println("Time cost: ${System.currentTimeMillis() - startTime}")
}


fun main212() = runBlocking {
    suspend fun <T> fastest(vararg deferreds: Deferred<T>): T = select {
        fun cancelAll() = deferreds.forEach { it.cancel() }

        for (deferred in deferreds) {
            deferred.onAwait {
                cancelAll()
                it
            }
        }
    }

    val deferred1 = async {
        delay(100L)
        println("done1")    // 没机会执行
        "result1"
    }

    val deferred2 = async {
        delay(50L)
        println("done2")
        "result2"
    }

    val deferred3 = async {
        delay(10000L)
        println("done3")    // 没机会执行
        "result3"
    }

//    val deferred4 = async {
//        delay(2000L)
//        println("done4")    // 没机会执行
//        "result4"
//    }
//
//    val deferred5 = async {
//        delay(14000L)
//        println("done5")    // 没机会执行
//        "result5"
//    }

    val result = fastest(deferred1, deferred2, deferred3)
    println(result)
}

// 代码段2
fun main213() = runBlocking {
    val startTime = System.currentTimeMillis()
    val productId = "xxxId"
    //          1，注意这里
    //               ↓
    val product = select<Product?> {
        // 2，注意这里
        async { getCacheInfo(productId) }
            .onAwait { // 3，注意这里
                it
            }
        // 4，注意这里
        async { getNetworkInfo(productId) }
            .onAwait {  // 5，注意这里
                it
            }
    }

    if (product != null) {
        updateUI(product)
        println("Time cost: ${System.currentTimeMillis() - startTime}")
    }
}
suspend fun getCacheInfo(productId: String): Product? {
    delay(100L)
    return Product(productId, 9.9)
}

suspend fun getNetworkInfo(productId: String): Product? {
    delay(200L)
    return Product(productId, 9.8)
}

data class Product(
    val productId: String,
    val price: Double
)

fun updateUI(product: Product) { println("${product.productId}==${product.price}") }
