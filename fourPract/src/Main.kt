fun main() {
    val text: String = "Hello World"
    val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val num1 = 5
    val num2 = 4
    val num3 = 3
    val num4 = 2
    val A = 10
    val B = 2
    val result = 0
    val resul2 = 0

    val oooooSig: List<(Int, Int) -> Int> = listOf(::multiplyNew, ::proiz)
    for (f in oooooSig) {
        println("Result: ${f(A, B)}")
    }
























    println(" 1. Функции с одинаковой сигнатурой ")
    val sameSig: List<(Int, Int) -> Int> = listOf(::sum, ::multiply)
    for (f in sameSig) {
        println("Result: ${f(num1, num2)}")
    }

    println("\n 2. Функции с разной сигнатурой ")
    val diffSig: List<Any> = listOf<(IntArray) -> Unit>(
        ::avgNumber,
        { arr: IntArray -> println("Size: ${arr.size}") }
    ) + listOf<(String) -> Unit>(
        ::stringClass
    )

    (diffSig[0] as (IntArray) -> Unit)(numbers)
    (diffSig[1] as (IntArray) -> Unit)(numbers)
    (diffSig[2] as (String) -> Unit)(text)

    println("\n3. Функции с разным количеством аргументов ")
    val diffArgs: List<Any> = listOf(
        { a: Int, b: Int -> sum(a, b) },
        { a: Int, b: Int -> a - b },
        { a: Int, b: Int, c: Int -> a + b + c }
    )

    println((diffArgs[0] as (Int, Int) -> Int)(num1, num2))
    println((diffArgs[1] as (Int, Int) -> Int)(num3, num4))
    println((diffArgs[2] as (Int, Int, Int) -> Int)(1, 2, 3))

    println("\n===== 4. Декораторы функций =====")
    val baseFunc: (Int, Int) -> Int = ::multiply

    val decorated1 = logDecorator(baseFunc)
    val decorated2 = timeDecorator(baseFunc)
    val decorated3 = checkPositiveDecorator(baseFunc)

    println("Single decorator (log): ${decorated1(3, 4)}")
    println("Single decorator (time): ${decorated2(3, 4)}")
    println("Single decorator (checkPositive): ${decorated3(3, 4)}")

    val allDecorated = logDecorator(timeDecorator(checkPositiveDecorator(baseFunc)))
    println("All decorators: ${allDecorated(3, 4)}")

    println("\n 5. Декоратор с параметром ")
    val scaledMultiply = scaleDecorator(baseFunc, 10)
    println("Scaled multiply: ${scaledMultiply(2, 5)}")
}

fun stringClass(text: String) {
    println("StringClass input: $text")
    val vowels = "aeoiuy"
    val result = text.map { ch ->
        if (ch.lowercaseChar() in vowels) ch.uppercaseChar() else ch
    }.joinToString("")
    println("StringClass output: $result")
}

fun avgNumber(numbers: IntArray) {
    println("Your array:")
    numbers.forEach { println(it) }
    val avgNum = numbers.average()
    println("Average number: $avgNum")
}

fun sum(a: Int, b: Int): Int {
    val result = a + b
    println("Sum result: $result")
    return result
}

fun multiply(a: Int, b: Int): Int {
    val result = a * b
    println("Multiply result: $result")
    return result
}

fun logDecorator(func: (Int, Int) -> Int): (Int, Int) -> Int {
    return { a, b ->
        println("Calling function with args: $a, $b")
        val res = func(a, b)
        println("Result is $res")
        res
    }
}

fun timeDecorator(func: (Int, Int) -> Int): (Int, Int) -> Int {
    return { a, b ->
        val start = System.nanoTime()
        val res = func(a, b)
        val end = System.nanoTime()
        println("Execution took: ${end - start} ns")
        res
    }
}

fun checkPositiveDecorator(func: (Int, Int) -> Int): (Int, Int) -> Int {
    return { a, b ->
        if (a < 0 || b < 0) {
            println("Error: negative numbers are not allowed!")
            0
        } else {
            func(a, b)
        }
    }
}

fun scaleDecorator(func: (Int, Int) -> Int, factor: Int): (Int, Int) -> Int {
    return { a, b ->
        val res = func(a, b)
        res * factor
    }
}













fun multiplyNew(A:Int, B:Int): Int{
    val result = A*B
    return result
}

fun proiz(A:Int, B:Int): Int{
    val result = A*B
    return result
}

