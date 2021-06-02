/*
Class: FXMLLoginVewController
Description: handles interaction with the FXMLLoginView.fxml file. Essentially
             handles input on the login screen.
Created: 30/02/2020.
Updated: 19/03/2020.
Authors: Asia Benyadilok
*/
package hospmansys.controllers_and_fxml;

import hospmansys.PatientMakeAppointmentClass.ExternalPatient;
import hospmansys.PatientMakeAppointmentClass.LoginForPatient;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

 
public class FXMLLoginViewController {
    
//create all attributes by using fxid from LoginView
@FXML private TextField surnameField;
@FXML private DatePicker dateOfBirthPicker;

//create patient object for patient that signed in
private static ExternalPatient user ;

//generate alert popup to display erroe messages
private Alert alert = new Alert(Alert.AlertType.ERROR);

    
 //method: changeScreenButtonClicked
 //parameters: ActionEvent event - parsed by javafx.
 //Description: Activates when the login button is clicked, evaluating if the account details are correct before
 //taking the user to the calendar view interface.

    @FXML
 public void changeScreenButtonClicked(ActionEvent event) throws Exception {
        dateOfBirthPicker.setShowWeekNumbers(false);
        //get inputs from gui
        String sur = surnameField.getText();
        String birthDate = String.valueOf(dateOfBirthPicker.getValue());
        boolean accessible = LoginForPatient.login(sur, birthDate);
        user = LoginForPatient.getCurrentPatient();
        //print out date input
        System.out.println(birthDate);
        
        //check to see if the login was valid and this patient has not made the appointment 
        if (accessible == true && user.getBookingStatus().equals("incomplete"))
        {
        //bring to CalendarView
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCalendarView.fxml"));
        Scene scene = new Scene(root);
        //set stage to window and get stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Appointment System");
        window.setScene(scene);
        window.show();
        }
        
        //if this patient has already made the appointment 
        else if(accessible == true && user.getBookingStatus() != "incomplete")
        {
            //show error messages
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("This user already made appointment!");
            alert.showAndWait();
        }
        //show error message
        else 
        {
            //show error messages
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("surname or date of birth is incorrect!");       
            alert.showAndWait();
        }
    }
}
   