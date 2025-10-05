data class Person(val name: String, val age: Int){
    override fun toString(): String {
        return "Name: $name  Age: $age"
    }
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

interface TriangleFactory {
    fun createTriangle(angle: Double)
}

class DegreesTriangleFactory : TriangleFactory {
    override fun createTriangle(angle: Double){
        require(angle in 0.0f..180.0f) { "Угол должен быть между 0 и 180 градусами" }

    }
}

class RadiansTriangleFactory : TriangleFactory {
    override fun createTriangle(angle: Double){
        val degrees = Math.toDegrees(angle.toDouble()).toFloat()
        require(degrees in 0.0f..180.0f) { "Угол должен быть между 0 и π радианами" }

    }
}

class Triangle private constructor(val angleInDegrees: Float) {

    fun getAngleInRadians(): Float = Math.toRadians(angleInDegrees.toDouble()).toFloat()

    fun calculateArea(): Float {
        return angleInDegrees * 0.5f
    }

    override fun toString(): String = "Triangle(angle=${angleInDegrees}°)"

    companion object {
        val degreesFactory: TriangleFactory = DegreesTriangleFactory()
        val radiansFactory: TriangleFactory = RadiansTriangleFactory()

    }
}

class EmptyProduct {
    var field1: String = ""
    var field2: String = ""
}

class EmptyBuilder {
    private val instance = EmptyProduct()

    fun field1(value: String): EmptyBuilder {
        instance.field1 = value
        return this
    }

    fun field2(value: String): EmptyBuilder {
        instance.field2 = value
        return this
    }

    fun build(): EmptyProduct {
        return instance
    }
}

fun main(){
    val alice = Person("Alice", 24)
    println("${alice.name}")
}