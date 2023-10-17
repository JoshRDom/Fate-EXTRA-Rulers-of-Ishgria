package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.AmuYunos
import scalafxml.core.macros.sfxml

import java.awt.Button
import scalafx.scene.Group
import scalafx.event.ActionEvent
import scalafx.scene.control.ProgressBar
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text

import scala.collection.mutable

// Controller for stage two of the game (StageTwo.fxml)
@sfxml
class StageTwoController(
                          // HP GUI
                          private var enemyHpBar : ProgressBar,
                          private var playerHpBar: ProgressBar,

                          // Combat Order GUI
                          private val quickAttack : Button,
                          private val guard : Button,
                          private val breakAttack : Button,
                          private val retractLastMove : Group,
                          private val turnNumber : Text,
                          private val moveButtons : Group,

                          private val turnHighlighter : Rectangle,
                          private val playerMove1Tile : Rectangle,
                          private val playerMove1Text : Text,
                          private val playerMove2Tile: Rectangle,
                          private val playerMove2Text: Text,
                          private val playerMove3Tile: Rectangle,
                          private val playerMove3Text: Text,
                          private val playerMove4Tile: Rectangle,
                          private val playerMove4Text: Text,
                          private val playerMove5Tile: Rectangle,
                          private val playerMove5Text: Text,
                          private val playerMove6Tile: Rectangle,
                          private val playerMove6Text: Text,
                          private val enemyMove1Tile: Rectangle,
                          private val enemyMove1Text: Text,
                          private val enemyMove2Tile: Rectangle,
                          private val enemyMove2Text: Text,
                          private val enemyMove3Tile: Rectangle,
                          private val enemyMove3Text: Text,
                          private val enemyMove4Tile: Rectangle,
                          private val enemyMove4Text: Text,
                          private val enemyMove5Tile: Rectangle,
                          private val enemyMove5Text: Text,
                          private val enemyMove6Tile: Rectangle,
                          private val enemyMove6Text: Text,
                          private val movesOverlay: Group,

                          // fight pop up components
                          private val fightPopUp: Group,
                          private val proceedFight : Button,
                          private val cancelFight : Button,

                          // battle overlay components
                          private val battleOverlay : Group,
                          private val battleMove : Text,
                          private val playerChainValue : Text,
                          private val playerChainText : Text,
                          private val enemyChainValue : Text,
                          private val enemyChainText : Text
                        ) {

  val playerMoves = new mutable.Stack[String]

  def quickAttack(action: ActionEvent): Unit = {
    pushMove("ATTACK")
  }

  def guard(action: ActionEvent): Unit = {
    pushMove("GUARD")
  }

  def breakAttack(action: ActionEvent): Unit = {
    pushMove("BREAK")
  }

  def pushMove(move: String): Unit = {
    // make sure no more than 6 moves are made
    if( playerMoves.length < 6 ){
      playerMoves.push(move)
      if (playerMoves.length == 1) {
        playerMove1Text.text = move
        retractLastMove.setVisible(true)
      } else if (playerMoves.length == 2) {
        playerMove2Text.text = move
      } else if (playerMoves.length == 3) {
        playerMove3Text.text = move
      } else if (playerMoves.length == 4) {
        playerMove4Text.text = move
      } else if (playerMoves.length == 5) {
        playerMove5Text.text = move
      } else if (playerMoves.length == 6) {
        playerMove6Text.text = move
      }

      // move turn highlighter
      turnHighlighter.layoutX = turnHighlighter.getLayoutX + 180

      // actions depending on if the sixth move was ordered
      if (playerMoves.length < 6) {
        turnNumber.text = (playerMoves.length + 1).toString
      } else {
        turnHighlighter.setVisible(false)
        fightPopUp.setVisible(true)
      }
    }
  }

  def undo(action: ActionEvent): Unit = {
    // make sure no less than 0 moves are made
    if( playerMoves.length > 0 ) {
      playerMoves.pop()
      if (playerMoves.length == 0) {
        playerMove1Text.text = "1"
        retractLastMove.setVisible(false)
      } else if (playerMoves.length == 1) {
        playerMove2Text.text = "2"
      } else if (playerMoves.length == 2) {
        playerMove3Text.text = "3"
      } else if (playerMoves.length == 3) {
        playerMove4Text.text = "4"
      } else if (playerMoves.length == 4) {
        playerMove5Text.text = "5"
      } else if (playerMoves.length == 5) {
        playerMove6Text.text = "6"
        turnHighlighter.setVisible(true)
      }
      turnNumber.text = (playerMoves.length + 1).toString
      turnHighlighter.layoutX = turnHighlighter.getLayoutX - 180
    }
  }

  def cancel(action: ActionEvent): Unit = {
    fightPopUp.setVisible(false)
    undo(null)
  }

  def battle(action: ActionEvent): Unit = {
    fightPopUp.setVisible(false)

    // migrate the player's moves into the battle stack
    val playerMoveStack = new mutable.Stack[String]
    while (playerMoves.length > 0) {
      playerMoveStack.push(playerMoves.pop)
    }

    // enemy move stack
    val enemyMoveStack = new mutable.Stack[String]()
    enemyMoveStack.push("BREAK") // Move 6
    enemyMoveStack.push("GUARD") // Move 5
    enemyMoveStack.push("ATTACK") // Move 4
    enemyMoveStack.push("BREAK") // Move 3
    enemyMoveStack.push("GUARD") // Move 2
    enemyMoveStack.push("ATTACK") // Move 1

    // execute battle
    var playerChain = 0
    var enemyChain = 0

    while (playerMoveStack.length > 0 & playerHpBar.progress() > 0 & enemyHpBar.progress() > 0){

      // move phase
      if (playerMoveStack.top == "ATTACK") {
        if (enemyMoveStack.top == "ATTACK") {
          playerChain = 0
          enemyChain = 0
          playerHpBar.progress() -= 0.07
          enemyHpBar.progress() -= 0.07
        } else if (enemyMoveStack.top == "BREAK") {
          playerChain += 1
          enemyChain = 0
          enemyHpBar.progress() -= 0.12
        } else if (enemyMoveStack.top == "GUARD") {
          playerChain = 0
          enemyChain += 1
          enemyHpBar.progress() -= 0.01
          playerHpBar.progress() -= 0.19
        }
      } else if (playerMoveStack.top == "BREAK") {
        if (enemyMoveStack.top == "ATTACK") {
          playerChain = 0
          enemyChain += 1
          playerHpBar.progress() -= 0.12
        } else if (enemyMoveStack.top == "BREAK") {
          playerChain = 0
          enemyChain = 0
          playerHpBar.progress() -= 0.14
          enemyHpBar.progress() -= 0.14
        } else if (enemyMoveStack.top == "GUARD") {
          playerChain += 1
          enemyChain = 0
          enemyHpBar.progress() -= 0.2
        }
      } else if (playerMoveStack.top == "GUARD") {
        if (enemyMoveStack.top == "ATTACK") {
          playerChain += 1
          enemyChain = 0
          playerHpBar.progress() -= 0.01
          enemyHpBar.progress() -= 0.19
        } else if (enemyMoveStack.top == "BREAK") {
          playerChain = 0
          enemyChain += 1
          playerHpBar.progress() -= 0.2
        } else if (enemyMoveStack.top == "GUARD") {
          playerChain = 0
          enemyChain = 0
        }
      }

      // reward for finding the correct matchup
      if ( playerChain>0 ) {
        if (playerMoveStack.length == 6) {
          enemyMove1Text.text = enemyMoveStack.top
        } else if (playerMoveStack.length == 5) {
          enemyMove2Text.text = enemyMoveStack.top
        } else if (playerMoveStack.length == 4) {
          enemyMove3Text.text = enemyMoveStack.top
        } else if (playerMoveStack.length == 3) {
          enemyMove4Text.text = enemyMoveStack.top
        } else if (playerMoveStack.length == 2) {
          enemyMove5Text.text = enemyMoveStack.top
        } else if (playerMoveStack.length == 1) {
          enemyMove6Text.text = enemyMoveStack.top
        }
      }

      // death check
      if (playerHpBar.progress() <= 0) {
        MainApp.showDefeat()
      } else if (enemyHpBar.progress() <= 0) {
        MainApp.showStageThree()
      } else {
        // extra move check
        if ( playerChain == 3 ) {
          playerChain = 0
          enemyHpBar.progress() -= 0.15
        } else if ( enemyChain == 3 ) {
          enemyChain = 0
          playerHpBar.progress() -= 0.15
        }
        // death check
        if (playerHpBar.progress() <= 0) {
          MainApp.showDefeat()
        } else if (enemyHpBar.progress() <= 0) {
          MainApp.showStageThree()
        } else {
          // pop stack
          enemyMoveStack.pop()
          playerMoveStack.pop()
        }
      }
    }

    // set up next round of moves
    if (playerHpBar.progress()>0 & enemyHpBar.progress()>0){
      playerMove1Text.text = "1"
      playerMove2Text.text = "2"
      playerMove3Text.text = "3"
      playerMove4Text.text = "4"
      playerMove5Text.text = "5"
      playerMove6Text.text = "6"
      turnNumber.text = (playerMoves.length + 1).toString
      turnHighlighter.layoutX = turnHighlighter.getLayoutX - 180*6

      turnHighlighter.setVisible(true)
      retractLastMove.setVisible(false)
    }
  }
}
