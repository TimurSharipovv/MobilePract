@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Author(val name: String)

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Version(val number: Int)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Demo

@Author("lskjhljdsg")
@Version(1)
class Example {

    @Deprecated("используй newMethod")
    fun oldMethod() { }

    @Suppress("UNUSED_PARAMETER")
    fun someMethod(param: String) { }

    companion object {
        @JvmStatic
        fun util() { }
    }

    @Demo
    fun testMethod() { }
}

@FunctionalInterface
interface MyFunction {
    fun run()
}

class SubClass : BaseClass() {
    @Override
    override fun doSomething() { }
}

open class BaseClass {
    open fun doSomething() { }
}

class VarargExample {
    @SafeVarargs
    fun <T> safeMethod(vararg items: T) { }
}

fun main() {
    val clazz = Example::class
    for (method in clazz.members) {
        if (method.annotations.any { it is Demo }) {
        }
    }
}
