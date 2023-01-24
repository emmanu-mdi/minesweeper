package minesweeper

import kotlin.random.Random

val field = MutableList(9){MutableList(9){'.'} }

fun printGrid(field:MutableList<MutableList<Char>>) {
    for (i in 0..8) {
        for (j in 0..8) {
            print(field[i][j])
        }
        println()
    }
}

fun addMines(mines: Int) {
    var count = 0
    do {
        val i = Random.nextInt(0, 9)
        val j = Random.nextInt(0, 9)
        if (field[i][j] == 'X') {
            field[i][j] = 'X'
        } else {
            field[i][j] = 'X'
            count++
        }
    } while (count != mines)

}

fun main() {
    print("How many mines do you want on the field? ")
    val mines = readln().toInt()
    addMines(mines)
    printGrid(field)
}
