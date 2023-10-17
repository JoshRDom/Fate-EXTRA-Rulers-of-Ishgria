package ch.makery.address.view
import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml
import java.awt.Button
import scalafx.event.ActionEvent

// Controller for game main menu
@sfxml
class MainMenuController( 
    private val startGame : Button,
    private val quitGame : Button,
    private val tutorialButton : Button
    ) {
    
    // function to start the game by showing the first stage 
    def startGame(action : ActionEvent) = {
        MainApp.showStageOne()
    }

    def tutorial(action : ActionEvent) = {
        MainApp.showTutorial()
    }

    // function to exit the game
    def quitGame(action : ActionEvent) = {
        MainApp.stage.close
    }
}