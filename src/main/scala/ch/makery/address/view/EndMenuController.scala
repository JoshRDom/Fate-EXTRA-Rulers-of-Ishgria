package ch.makery.address.view
import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml
import java.awt.Button
import scalafx.event.ActionEvent

// Controller for game completion menu
@sfxml
class EndMenuController( 
    private val playAgain : Button,
    private val exit : Button
    ) {
    
    // function to allow players to play the game again
    def playAgain(action : ActionEvent) = {
        MainApp.showStageOne()
    }

    // function to exit the game
    def exit(action : ActionEvent) = {
        MainApp.showMainMenu()
    }
}