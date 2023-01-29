package minesweeper

import kotlin.random.Random

val field = MutableList(9){MutableList(9){'.'} }
val fakeField = MutableList(9){MutableList(9){'.'} }

fun printGrid(field:MutableList<MutableList<Char>>) {
    println(" |123456789|")
    println("-|---------|")
    for (i in 0..8) {
        print("${i + 1}|")
        for (j in 0..8) {
            print(field[i][j])
        }
        println("|")
    }
    println("-|---------|")
}

fun addCheckMines(mines: Int) {
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
    } while (count < mines)

    for (i in 0..8){
        for (j in 0..8) {
            if (field[i][j] == '.') {
                val n = cntNeighbors(i, j)
                if (n > 0) {
                    field[i][j] = n.digitToChar()
                    fakeField[i][j] = n.digitToChar()
                }
            }
        }
    }
}

fun cntNeighbors(r: Int, c: Int): Int {
    var mCount = 0
    for (i in r - 1..r + 1) {
        for (j in c - 1..c + 1) {
            if (i in 0 until 9 && j in 0 until 9) {
                mCount += if (field[i][j] == 'X') 1 else 0
            }
        }
    }
    return mCount
}

fun play(mines: Int){
    var count = 0
    while (count < mines) {
        print("Set/delete mines marks (x and y coordinates): ")
        val userCoord = readln().split(" ")
        val y = userCoord[0].toInt() - 1
        val x = userCoord[1].toInt() - 1

        if (field[x][y] in '1'..'8') {
            println("There is a number here!")
        } else if (field[x][y]  == '.' && fakeField[x][y] == '.') {
            fakeField[x][y] = '*'
            printGrid(fakeField)
        }  else if (fakeField[x][y] == '*' && field[x][y] == '.') {
            fakeField[x][y] = '.'
            printGrid(fakeField)
        } else if (field[x][y] == 'X') {
            fakeField[x][y] = '*'
            count++
            printGrid(fakeField)
        }
    }
    if (count == mines) {
        println("Congratulations! You found all the mines!")
    }
}

fun main() {
    print("How many mines do you want on the field? ")
    val mines = readln().toInt()
    addCheckMines(mines)
    printGrid(fakeField)
    play(mines)
}
