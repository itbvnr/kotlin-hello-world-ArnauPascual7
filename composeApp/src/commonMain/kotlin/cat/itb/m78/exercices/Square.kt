package cat.itb.m78.exercices

import kotlinx.serialization.Serializable
import kotlinx.serialization.*
import kotlinx.serialization.json.Json

data class Jokes(val id: Int, val type: String, val setup: String, val punchline: String)

@Serializable
data class Rectangle(val height: Double, val width: Double)

fun main() {
    val rectangle = Rectangle(10.0, 10.0)
    val rectangleJson = Json.encodeToString(rectangle)
    val recList = listOf(rectangle)
    val recListJson = Json.encodeToString(recList)

    println("Rectangle: $rectangle")
    println("Json: $rectangleJson")
    println("Rectangle List: $recList")
    println("Json List: $recListJson")
}