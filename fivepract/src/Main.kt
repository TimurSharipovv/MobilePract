fun main(){
    val warrior1 = Warrior("Ivan", 5, 100, 10)
    val warrior2 = Warrior("Petya", 10, 85, 15)
    println(warrior1.Attack())
    Warrior.ShowAge()

}

class Warrior(
    public val name: String,
    protected val damage: Int,
    private val health: Int,
    internal val level: Int
    ){
    public fun Attack(): String{
        return "Воин с уровнем $level $damage урона"
    }

    companion object{
        var warriorAge: Int = 25

        fun ShowAge(){
            println("Возраст всех воинов $warriorAge")
        }
    }
}