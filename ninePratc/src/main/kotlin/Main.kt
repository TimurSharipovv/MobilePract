fun firstFunc() {
    println("First func")
}

fun secondFunc() {
    println("Second func")
}

fun main() {
    val functionsList = listOf(::firstFunc, ::secondFunc)

    val firstThread = Thread { firstFunc() }
    firstThread.start()

    val secondThread = Thread { secondFunc() }
    secondThread.start()

    val threadList: List<Thread> = functionsList.map { func -> Thread { func() } }
    threadList.forEach { it.start() }
    threadList.forEach { it.join() }

    val sharedList = mutableListOf<Int>()
    val lock = Object()

    fun producer(id: Int) {
        for (i in 1..5) {
            synchronized(lock) {
                sharedList.add(i)
            }
        }
    }

    fun consumer(id: Int) {
        for (i in 1..5) {
            synchronized(lock) {
                if (sharedList.isNotEmpty()) sharedList.removeAt(0)
            }
        }
    }

    val producers = List(2) { id -> Thread { producer(id) } }
    val consumers = List(3) { id -> Thread { consumer(id) } }
    (producers + consumers).forEach { it.start() }
    (producers + consumers).forEach { it.join() }

    val parentThread = Thread {
        val childThread = Thread {
            while (!Thread.currentThread().isInterrupted) {}
        }
        childThread.start()
        try {
            Thread.sleep(20000)
        } catch (e: InterruptedException) {
            childThread.interrupt()
        }
    }
    parentThread.start()
    Thread.sleep(1000)
    parentThread.interrupt()

    val uniqueTaskThreads = List(3) { id ->
        Thread {
            repeat(3) { println("Уникальная задача поток $id, шаг $it") }
        }
    }
    uniqueTaskThreads.forEach { it.start() }
    uniqueTaskThreads.forEach { it.join() }
}
