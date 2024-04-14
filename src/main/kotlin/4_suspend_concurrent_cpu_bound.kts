import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun fib(n: Int): Int {
    if (n == 0) return 1
    if (n == 1) return 1
    return fib(n-1) + fib(n-2)
}

val timeInMillis = measureTimeMillis {
    runBlocking {
        repeat(3) { i ->
            launch {
                println(fib(42))
            }
        }
    }
}
println("(The operation took $timeInMillis ms)")