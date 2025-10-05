import kotlin.reflect.full.*

open class BaseClass(val baseField: String) {
    fun baseMethod() {}
}

class ExampleClass(val id: Int, var name: String) : BaseClass("base") {
    fun printMessage(message: String, count: Int) {}
}

fun main() {
    val clazz = ExampleClass::class

    println("1. Список всех атрибутов")
    clazz.annotations.forEach { println(it) }

    println("\n2. Список всех предков")
    clazz.supertypes.forEach { println(it) }

    println("\n3. Список всех методов класса:")
    clazz.functions.forEach { println(it.name) }

    println("\n4. Список всех свойств класса:")
    clazz.memberProperties.forEach { println(it.name) }

    println("\n5. Список аргументов функции printMessage:")
    val func = clazz.functions.find { it.name == "printMessage" }
    func?.parameters?.forEach { println(it) }

    println("\n6. Список типов аргументов функции printMessage:")
    func?.parameters?.forEach { println(it.type) }

}
