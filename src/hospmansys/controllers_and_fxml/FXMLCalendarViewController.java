/*
Class: FXMLCalendarVewController
Description: handles interaction with the FXMLCalendarView.fxml file. Essentially
             handles input on the calendar view screen.
Created: 30/02/2020.
Updated: 19/03/2020.
Authors: Asia Benyadilok
*/
package hospmansys.controllers_and_fxml;



import hospmansys.PatientMakeAppointmentClass.AppointmentCalendar;
import hospmansys.PatientMakeAppointmentClass.ExternalPatient;
import hospmansys.database.DatabaseManagementSystem;
import hospmansys.database.Firewall;
import hospmansys.PatientMakeAppointmentClass.LoginForPatient;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.text.View;


public class FXMLCalendarViewController implements Initializable {
 //create all attributes by using fxid from CalendarView    
@FXML private DatePicker appointmentDatePicker;
@FXML private Button logoutButton;
@FXML private Button confirmButton;
@FXML private TableView<Property> tableView;
@FXML private TableColumn<Property, String> doctorCol;
@FXML private TableColumn<Property, String> timeCol;
@FXML private Label docterNameLabel;
@FXML private Label dateLabel;
@FXML private Label timeLabel;
@FXML private Label nameLabel;
@FXML private Label emailLabel;
@FXML private Label dateLabel1;
@FXML private Label timeLabel1;
 
//get patient object that logged in from LoginView
private ExternalPatient user = LoginForPatient.getCurrentPatient();

//generate alert popup to display erroe messages
Alert alert = new Alert(Alert.AlertType.ERROR);

//construct the list of time and doctor that need to be shown on tableView
ObservableList<Property> observableList = FXCollections.observableArrayList
(
  AppointmentCalendar.getInstance().getAvailableTimeAndDoctor()
);

 
    //method: changeScreenButtonClicked
    //parameters: ActionEvent event - parsed by javafx.
    //Description: Activates when the logout button is clicked, taking the user to the login view interface.
    @FXML
    public void changeScreenButtonClicked(ActionEvent event) throws Exception {
        alert = new Alert(AlertType.CONFIRMATION, " Are you sure to logout? ", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK)
        {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLLoginView.fxml"));
            Scene scene = new Scene(root);
            //set stage to window and get stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Appointment System");
            window.setScene(scene);
            window.show();
        }
    }
    
    //method: getDayCellFactory
    //parameters: none 
    //Description: method to interact with each date on date picker as date cell
    
    // Factory to create Cell of DatePicker
    private Callback<DatePicker, DateCell> getDayCellFactory() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
 
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        //get current date, month, and year
                        LocalDate today = LocalDate.now();
                        int thisMonth = today.getMonthValue();
                        int thisYear = today.getYear();
                        int nextYear = thisYear +1;
                        //disable date that less than today
                        setDisable(empty || item.compareTo(today) < 0);
                        setDisable(true);
                        
                        //loop through available date list from AppointmentCalendar class
                        for (int i =0;i< AppointmentCalendar.getInstance().getAvailableDate().size();i++){       
                            //if date is match with date available in the list
                            if (( item.getDayOfMonth() ==  AppointmentCalendar.getInstance().getAvailableDate().get(i) && 
                                    item.getMonthValue() >= thisMonth && 
                                    item.getYear() == thisYear &&
                                    item.compareTo(today) > 0 ) ||
                                    (item.getDayOfMonth() ==  AppointmentCalendar.getInstance().getAvailableDate().get(i) && 
                                    item.getYear() == thisYear +1) 
                                    ) 
                            {
                                //undisable date and add hightlight to that date
                                setStyle("-fx-background-color:  #00c3ff");   
                                setDisable(false);
                            }
                        }

                    }
                };
            }
        };
        //return each day cell
        return dayCellFactory;
    }
    
    //method: showPropertyDetails
    //parameters: TestProperty - property object that show on tableView
    //Description: method to show details on the screen
    private void showPropertyDetails(Property TestProperty) {
        //if object is not empty
        if (TestProperty != null) 
        {
            // Fill the labels with info from the property object.
            docterNameLabel.setText(TestProperty.getDoctorName());
            timeLabel.setText(TestProperty.getTime());
        } 
        else 
        {
            // object is null, remove all the text.
            docterNameLabel.setText("");
            timeLabel.setText("");

        }
    }
    
     //method: datePickerClicked
    //parameters: none
    //Description: method to show details on tableView when user choose the date
    @FXML
    public void datePickerClicked() {
         //show detail of the date on the screen 
         dateLabel.setText(String.valueOf(appointmentDatePicker.getValue()));
         
         //construct temporally array list to show time and doctor that available on that specific date
         ObservableList<Property> showList = FXCollections.observableArrayList();
         // loop through the list and add only available time on that date
         for (int i = 0 ;i<observableList.size();i++)
          {
             if(observableList.get(i).checkAvailability(appointmentDatePicker.getValue())){
              showList.add(observableList.get(i));
             }
         }
         //show the list on tableView
          tableView.setItems(showList);
    }
    
     //method: confirmButtonClicked
    //parameters: none
    //Description: method to save preference date of patient and send booking to database
    @FXML
    public void confirmButtonClicked() {
        
        //show confirmation popup to make sure that patient want to proceed with the date chosen
        if (String.valueOf(appointmentDatePicker.getValue()).equals("")||timeLabel.getText().equals(""))
        {
           alert = new Alert(Alert.AlertType.ERROR); 
           alert.setTitle("Invalid Fields");
           alert.setHeaderText("Please correct invalid fields");
           alert.setContentText("Please select valid date and time!");
           alert.showAndWait();
        }
        else
        {
            alert = new Alert(AlertType.CONFIRMATION, " Once you have confirmed this it cannot be changed", ButtonType.OK, ButtonType.CANCEL);
            alert.setHeaderText("Once you have confirmed this it cannot be changed");
            alert.setContentText("Appointment date: "+appointmentDatePicker.getValue()+"\n"+"Appointment time: "+timeLabel.getText());
            alert.showAndWait();

            //if user click ok
            if (alert.getResult() == ButtonType.OK) {
               //set preference date and time that user chose
               user.setAppointment(String.valueOf(appointmentDatePicker.getValue()), timeLabel.getText());

               //set booking status to complete, so that the same user cannot login to make appointment again
               user.setBookingStatus("complete");

               //set time selected to unavailable, so next user cannot choose the same date and time
               Property selectedProperty = tableView.getSelectionModel().getSelectedItem();
               selectedProperty.setUnavailableDate(appointmentDatePicker.getValue());

                 //show popup that the booking has sucessfully updated
                 alert = new Alert(AlertType.CONFIRMATION, "Your booking has been sucessfully updated", ButtonType.OK);
                 alert.showAndWait();

                 //disable datepicker, tableView, and confirmButton
                 tableView.setDisable(true);
                 appointmentDatePicker.setDisable(true);
                 confirmButton.setDisable(true);
                 
                 //print out put to command line
                 System.out.println("Appointment date: "+user.getPrefDate());
                 System.out.println("Appointment time: "+user.getPrefTime());

                 //call database and connect to it
                 DatabaseManagementSystem db = new DatabaseManagementSystem();
                 Firewall firewall = new Firewall();
                 db.connectToDB();

              //check if data is secure or not
              if (firewall.scanData(user.getBookingStatus()+","+user.getPrefDate()+","+user.getPrefTime()+","+Integer.parseInt(user.getPatientID())))
              {
              //if yes updated table in the database
              db.updateEntry("external_patient","bookingstatus = \""+user.getBookingStatus()+"\",prefdate = \""+user.getPrefDate()+"\", preftime = \""+user.getPrefTime()+"\"",Integer.parseInt(user.getPatientID()));
              }
            }
        }
    }

     /*
    Method: intialize
    Description: populates the screen with details from system data and relevant editting buttons.
    Parameters: Handled by javafx, do not change.
    Warning: Do not attempt to call this method anywhere else in the program.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //undisable tableView, datepicker, and confirmButton
        tableView.setDisable(false);
        appointmentDatePicker.setDisable(false);
        confirmButton.setDisable(false);
        
        //show patient name and email 
        nameLabel.setText(user.getFirstname()+" "+user.getSurname());
        emailLabel.setText(user.getEmail());
        
        //call up daycell and set in on date picker
        Callback<DatePicker, DateCell> dayCellFactory = this.getDayCellFactory();
        appointmentDatePicker.setDayCellFactory(dayCellFactory);
        
        //set columns of tableView
        doctorCol.setCellValueFactory(new PropertyValueFactory<>("DoctorName"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("Time"));
        
        //call show details on tableView
        showPropertyDetails(null);
         // Listen for selection changes and show the property details when changed.
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPropertyDetails(newValue));


    }
    
}
