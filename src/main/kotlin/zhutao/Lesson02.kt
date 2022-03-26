package zhutao

fun main() {

}

open class Person1(val name: String, var age: Int) {
    val isAdult: Boolean
        get() {
            // do something else
            return age >= 18
        }

    open fun walk() {
        println("walk")
    }
}

class Boy(name: String, age : Int) : Person1(name, age) {
    override fun walk() {

    }
}

interface Behavior {
    val canWalk : Boolean

    fun walk() {
        if(canWalk) {

        }
    }
}


enum class Human(val value: Int ) {
    MAN(1),
    WOMAN(0)
}



fun isMan(data: Human) = when(data) {

    Human.MAN -> true
    Human.WOMAN -> false
    // 这里不需要else分支，编译器自动推导出逻辑已完备
}


