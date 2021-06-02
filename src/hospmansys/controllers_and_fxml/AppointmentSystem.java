/*
 Class: AppointmentSystem.
 Description: The launching point for this application. Launches the login window.
Created: 30/02/2020.
Updated: 19/03/2020.
 Authors: Asia Benyadilok
 */
package hospmansys.controllers_and_fxml;


import hospmansys.PatientMakeAppointmentClass.ExternalPatient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AppointmentSystem extends Application {
    
     /*
    Method: start
    Description: creates the gui.
    Parameters: Stage stage - used by JavaFX, please ignore.
    */
   @Override
    public void start(Stage stage) throws Exception {
         //construct patients
        ExternalPatient chris = new ExternalPatient("1","Chris","Wang","2000-01-01","C.Wang@email.com","incomplete");
        ExternalPatient adam = new ExternalPatient("2","Adam","Wayne","2001-01-01","A.Wanye@email.com","incomplete");
        ExternalPatient max = new ExternalPatient("3","Max","Mad","2002-01-01","M.Mad@email.com","incomplete");
        ExternalPatient tomas = new ExternalPatient("4","Tomas","Hang","2003-01-01","T.Hang@email.com","incomplete");
        ExternalPatient michael = new ExternalPatient("5","Michael","Yang","1999-01-01","M.Yang@email.com","incomplete");
        //add patients to patient list
        ExternalPatient.setExternalPatientList(michael);
        ExternalPatient.setExternalPatientList(chris);
        ExternalPatient.setExternalPatientList(adam);
        ExternalPatient.setExternalPatientList(max);
        ExternalPatient.setExternalPatientList(tomas);

        Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Appointment System");
        Image icon = new Image(getClass().getResourceAsStream("/hospmansys/Image/icon.png"));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    //run file
    public static void main(String[] args) {
        launch(args);
    }
    
}
