package com.github.zuccini

class Fruit(
    var fruitName: String = "",
    var fruitId: String = "",
    var location: String = "",
    var description: String = ""
) {
    override fun toString(): String {
        return "$fruitName ($description) - $location"
    }
}

