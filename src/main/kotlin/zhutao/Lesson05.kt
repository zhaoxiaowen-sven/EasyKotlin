package zhutao

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
//
//interface A {
//    fun funA()
//}
//
//interface B {
//    fun funB()
//}
//
//abstract class Man {
//    abstract fun findMan()
//}
private val  s : String = "1"
fun main() {
//    // 这个匿名内部类，在继承了Man类的同时，还实现了A、B两个接口
//    val item = object : Man(), A, B{
//        override fun funA() {
//            // do something
//        }
//        override fun funB() {
//            // do something
//        }
//        override fun findMan() {
//            // do something
//        }
//    }
//
    UserManager3.getInstance("hello")
    P.InnerSingleton.foo()
    P.foo()

}


class P {
    companion object InnerSingleton {
        @JvmStatic
        fun foo() {}
    }
}

//object UserManager {
//    fun login() {}
//}


//  私有的构造函数，外部无法调用
//            ↓
//class User private constructor(name: String) {
//    companion object {
//        @JvmStatic
//        fun create(name: String): User {
//            // 统一检查，比如敏感词过滤
//            return User(name)
//        }
//    }
//}
//private object UserManager {
//    val user by lazy {
//        loadUser()
//    }
//
//    private fun loadUser():User {
//        return User.create("tom")
//    }
//
//    fun login () {
//
//    }
//}
//
//class UserManager2 private constructor(name : String) {
//    companion object {
//        @Volatile private var Instance: UserManager2? = null
////        @JvmStatic
//        fun getInstance(name : String) :UserManager2 =
//            Instance?: synchronized(this) {
//                Instance?:UserManager2(name).also {
//                    Instance = it
//                }
//            }
//    }
//}

private class UserManager3 private constructor(val name : String) {
    companion object : BaseSingleton<String, UserManager3>() {
        override fun creator(param: String): UserManager3 {
           return UserManager3(name = param)
        }
    }
}

abstract class BaseSingleton<in P, out T> {
    private var instance : T? = null

    protected abstract fun creator(param: P) : T

    fun getInstance(param: P) : T =
        instance ?: synchronized(this) {
            instance ?: creator(param).also { instance = it }
        }
}

