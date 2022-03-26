package zhutao

/*
 ①    ②      ③            ④
 ↓     ↓       ↓            ↓      */
fun String.lastElement(): Char? {
    //    ⑤
    //    ↓
    if (this.isEmpty()) {
        return null
    }

    return this[length - 1]
}

val String.lastElement: Char?
    get() = if (isEmpty()) { null } else { get(length - 1) }

// 使用扩展函数
fun main() {
    val msg = "Hello Wolrd"
    // lastElement就像String的成员方法一样可以直接调用
    val last = msg.lastElement() // last = d

    val last2 = msg.lastElement

}

interface H {
    val X
     get() = 1
}

class h : H {
    override val X: Int
        get() = super.X
}


open class Person {
    var name: String = ""
    var age: Int = 0
}

class Helper {
    private fun walkOnFoot() {
        println("用脚走路")
    }
    val Person.isAdult: Boolean
        get() = age >= 18

    fun Person.walk() {
        // 调用了Helper的私有方法
        walkOnFoot()
    }

    fun test() {
        val person = Person()
        // 仅可以在Helper类当中使用此扩展
        person.walk()

        val res = mutableListOf<Int>()
    }
}