// MODULE: common
// FILE: common.kt

expect fun defaultValue1(param: Int = 1): Int
expect suspend fun defaultValue2(param: Int = 1): Int
expect inline fun defaultValue3(param: () -> Int = { 1 }): Int

// ATTACH_LIBRARY: maven(org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2)
// MODULE: jvm
// FILE: jvm.kt
// DEPENDS_ON: common
import kotlinx.coroutines.runBlocking

actual fun defaultValue1(param: Int): Int = param
actual suspend fun defaultValue2(param: Int): Int = param
actual inline fun defaultValue3(param: () -> Int): Int = param()

fun main() {
    // EXPRESSION: defaultValue1()
    // RESULT: 1: I
    //Breakpoint!
    val a = 0

    // EXPRESSION: defaultValue1(2)
    // RESULT: 2: I
    //Breakpoint!
    val b = 0

    runBlocking {
        // EXPRESSION: defaultValue2()
        // RESULT: 1: I
        //Breakpoint!
        val c = 0

        // EXPRESSION: defaultValue2(2)
        // RESULT: 2: I
        //Breakpoint!
        val d = 0
    }

    // EXPRESSION: defaultValue3()
    // RESULT: 1: I
    //Breakpoint!
    val e = 0

    // EXPRESSION: defaultValue3({2})
    // RESULT: 2: I
    //Breakpoint!
    val f = 0
}