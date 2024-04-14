import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

val timeInMillis = measureTimeMillis {
    runBlocking {
        val numeros = listOf(1, 2, 3, 4, 5)
        val pares = numeros.filter { numero ->
            delay(100)
            (numero % 2) == 0
        }
        println(pares)
    }
}
println("(The operation took $timeInMillis ms)")