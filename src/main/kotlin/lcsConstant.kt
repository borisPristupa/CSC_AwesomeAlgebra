import java.math.BigInteger
import java.math.MathContext
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

fun lcs(s1: String, s2: String): Int {
    val n = s1.length
    val m = s2.length
    val dyn = Array(n + 1) { Array(m + 1) { 0 } }

    for (i in 1..n) {
        for (j in 1..m) {
            dyn[i][j] = if (s1[i - 1] == s2[j - 1]) {
                dyn[i - 1][j - 1] + 1
            } else {
                maxOf(dyn[i - 1][j], dyn[i][j - 1])
            }
        }
    }

    return dyn[n][m]
}

fun lcsConstant(length: Int, timeout: Long, timeUnit: TimeUnit): Double {
    val strings = LinkedList(listOf(StringBuilder()))
    while (length > strings[0].length) {
        val size = strings.size
        for (i in 0 until size) {
            val s = strings.removeFirst()
            strings.addLast(StringBuilder(s).append('0'))
            strings.addLast(s.append('1'))
        }
    }

    val tasks = strings.flatMap { s1 ->
        strings.map { s2 ->
            Callable {
                lcs(s1.toString(), s2.toString())
            }
        }
    }

    val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    val futures = executor.invokeAll(tasks)
    executor.shutdown()
    if (!executor.awaitTermination(timeout, timeUnit)) {
        throw TimeoutException()
    }

    val commonLength = tasks.size.toLong() * length

    return futures.map { future -> future.get() }
        .map { BigInteger.valueOf(it.toLong()) }
        .fold(BigInteger.ZERO) { sum, value -> sum + value }
        .toBigDecimal()
        .divide(commonLength.toBigDecimal(), MathContext.DECIMAL64)
        .toDouble()
}