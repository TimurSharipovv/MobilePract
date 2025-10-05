import java.util.concurrent.*
import kotlin.concurrent.thread

class RunnableTask : Runnable {
    override fun run() {
    }
}

class ThreadTask : Thread() {
    override fun run() {
    }
}

class CallableTask : Callable<Unit> {
    override fun call(): Unit {
    }
}

fun main() {
    val executor1 = Executors.newFixedThreadPool(2)
    executor1.submit(RunnableTask())
    executor1.shutdown()

    val executor2 = Executors.newFixedThreadPool(2)
    executor2.submit(ThreadTask())
    executor2.shutdown()

    val executor3 = Executors.newFixedThreadPool(2)
    val future: Future<Unit> = executor3.submit(CallableTask())
    executor3.shutdown()

    val tasks1 = List(5) { RunnableTask() }
    val executor4 = Executors.newFixedThreadPool(3)
    tasks1.forEach { executor4.submit(it) }
    executor4.shutdown()

    val tasks2 = List(5) { ThreadTask() }
    tasks2.forEach { it.start() }

    val tasks3 = List(5) { CallableTask() }
    val executor5 = Executors.newFixedThreadPool(3)
    val futures = tasks3.map { executor5.submit(it) }
    executor5.shutdown()

    val executor6 = Executors.newFixedThreadPool(5)
    val futuresList = List(5) { executor6.submit(CallableTask()) }
    val completed = CompletableFuture.anyOf(*futuresList.map { it.toCompletableFuture() }.toTypedArray())
    completed.thenRun {
        futuresList.forEach { it.cancel(true) }
        executor6.shutdownNow()
    }

    val executor7 = Executors.newFixedThreadPool(5)
    val futuresList2 = List(5) { executor7.submit(CallableTask()) }
    val completableFutures = futuresList2.map { it.toCompletableFuture() }
    val firstThree = CompletableFuture.allOf(*completableFutures.take(3).toTypedArray())
    firstThree.thenRun {
        completableFutures.drop(3).forEach { it.cancel(true) }
        executor7.shutdownNow()
    }

    val executor8 = Executors.newFixedThreadPool(5)
    val futuresList3 = List(5) { executor8.submit(CallableTask()) }
    futuresList3.forEach { future ->
        thread {
            try {
                future.get()
            } catch (e: Exception) {
            }
        }
    }

    val executor9 = Executors.newFixedThreadPool(5)
    val futuresList4 = List(5) { executor9.submit(CallableTask()) }
    val latch = CountDownLatch(1)
    futuresList4.forEachIndexed { index, future ->
        thread {
            if (index > 0) {
                latch.await()
            }
            try {
                future.get()
                if (index == 0) {
                    latch.countDown()
                }
            } catch (e: Exception) {
            }
        }
    }
}