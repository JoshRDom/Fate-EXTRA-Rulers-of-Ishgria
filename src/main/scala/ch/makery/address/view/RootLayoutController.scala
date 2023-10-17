package ch.makery.address.view
import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml
import javax.swing.text.html.ImageView
import java.awt.Button
import scalafx.scene.input
import scalafx.event.ActionEvent
import java.awt.Desktop.Action

// Controller for the root layout menu bar
@sfxml
class RootLayoutController( 
    private val close : Button,
    private val restartGame : Button,
    private val tutorial : Button
    ) {
    
    // function to exit application
    def close(action : ActionEvent) = {
        MainApp.stage.close
    }

    // function to restart the game
    def restart(action : ActionEvent) = {
        MainApp.showMainMenu()
    }

    // function to dislplay game tutorial
    def tutorial(action : ActionEvent) = {
        MainApp.showTutorial()
    }
}