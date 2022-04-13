package zhutao

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
//    main201()
//    main202()
//    main203()
//    main204()
//    main205()
    main206()
}


// 代码段1

fun main201() = runBlocking {
    flow {                  // 上游，发源地
        logX("emit 1")
        emit(1)             // 挂起函数
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }.filter { it > 2 }     // 中转站1
        .map { it * 2 }     // 中转站2
        .take(2)            // 中转站3
        .collect {           // 下游
            logX(it)
            println(it)
        }
}

// 代码段2
fun main202() = runBlocking {
    flowOf(1, 2, 3, 4, 5)
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect {
            println(it)
        }

    listOf(1, 2, 3, 4, 5)
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach {
            println(it)
        }
}

// 代码段3
fun main203() = runBlocking {
    // Flow转List
    flowOf(1, 2, 3, 4, 5)
        .toList()
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .forEach {
            println(it)
        }

    // List转Flow
    listOf(1, 2, 3, 4, 5)
        .asFlow()
        .filter { it > 2 }
        .map { it * 2 }
        .take(2)
        .collect {
            println(it)
        }
}


// 代码段11
fun main204() = runBlocking {
    val flow = flow {
        logX("Start")
        emit(1)
        logX("Emit: 1")
        emit(2)
        logX("Emit: 2")
        emit(3)
        logX("Emit: 3")
    }

    flow
//        .flowOn(Dispatchers.IO)  // 注意这里
        .filter {
            logX("Filter: $it")
            it > 2
        }
//        .flowOn(Dispatchers.IO)  // 注意这里
        .collect {
            logX("Collect $it")
        }
}

fun main205() = runBlocking {
// 代码段15
    val scope = CoroutineScope(Dispatchers.Default)
    flow {
        logX("emit 01")
        emit(1)
        logX("emit 02")
        emit(2)
        logX("emit 03")
        emit(3)
        logX("emit 04")
        emit(4)
    }.flowOn(Dispatchers.IO)
        .filter {
            logX("Filter: $it")
            it > 2
        }
        .onEach {
            logX("onEach $it")
        }
        .launchIn(scope)

    delay(1000)
}


fun main206() = runBlocking {
// 代码段15
    flow {
        logX("emit 01")
        emit(1)
        logX("emit 02")
        emit(2)
        logX("emit 03")
        emit(3)
        logX("emit 04")
        emit(4)
    }
        .filter {
            logX("Filter: $it")
            it > 2
        }
//        .onEach {
//            logX("onEach $it")
//        }
        .collect {
            logX("collect $it")
        }

    delay(1000)
}

// 代码段17
//fun main206() = runBlocking {
//    val scope = CoroutineScope(mySingleDispatcher)
//    val flow = flow {
//        logX("Start")
//        emit(1)
//        logX("Emit: 1")
//        emit(2)
//        logX("Emit: 2")
//        emit(3)
//        logX("Emit: 3")
//    }
//        .flowOn(Dispatchers.IO)
//        .filter {
//            logX("Filter: $it")
//            it > 2
//        }
//        .onEach {
//            logX("onEach $it")
//        }
//
//    scope.launch { // 注意这里
//        flow.collect().let {
//        }
//    }
//
//    delay(100L)
//}