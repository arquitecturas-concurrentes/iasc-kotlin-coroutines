import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun <T> asyncFilter(
    lista: List<T>,
    predicado: suspend (T) -> Boolean,
): List<T> {
    return coroutineScope {
        val deferredResults = lista.map { numero ->
            val promesa = async {
                if (predicado(numero)) {
                    numero
                } else {
                    null
                }
            }
            promesa
        }
        deferredResults.awaitAll().filterNotNull()
    }
}

val timeInMillis = measureTimeMillis {
    runBlocking {
        val numeros = listOf(1, 2, 3, 4, 5)
        val pares = asyncFilter(numeros) { numero ->
            delay(100)
            (numero % 2) == 0
        }
        println(pares)
    }
}
println("(The operation took $timeInMillis ms)")