package ch.makery.address.view
import ch.makery.address.MainApp
import scalafxml.core.macros.sfxml
import java.awt.Button
import scalafx.event.ActionEvent

// Controller for game tutorial (Tutorial.fxml)
@sfxml
class TutorialController( 
    private val back : Button,
    ) {
    
    // function to go back to main menu from tutorial
    def back(action : ActionEvent) = {
        MainApp.showMainMenu()
    }

}