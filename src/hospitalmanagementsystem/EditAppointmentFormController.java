/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author WINDOWS 10
 */
public class EditAppointmentFormController implements Initializable {

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_patientID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private TextField editApp_gender;

    @FXML
    private TextField editApp_mobileNumber;

    @FXML
    private TextArea editApp_address;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public void updateBtn() {

        if (editApp_appointmentID.getText().isEmpty()
                || editApp_patientID.getText().isEmpty()
                || editApp_fullName.getText().isEmpty()
                || editApp_gender.getText().isEmpty()
                || editApp_mobileNumber.getText().isEmpty()
                || editApp_address.getText().isEmpty()
                || editApp_description.getText().isEmpty()
                || editApp_diagnosis.getText().isEmpty()
                || editApp_treatment.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            String updateData = "UPDATE appointment SET mobile_number = ?"
                    + ", address = ?, date_modify = ?, description = ? "
                    +", diagnosis = ?, treatment = ?"
                    + "WHERE appointment_id = '"
                    + editApp_appointmentID.getText() + "'";
            connect = Database.connectDB();
            try {
                if (alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: " + editApp_appointmentID.getText()
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, editApp_mobileNumber.getText());
                    prepare.setString(2, editApp_address.getText());
                    prepare.setString(3, String.valueOf(sqlDate));
                    prepare.setString(4, editApp_description.getText());
                    prepare.setString(5, editApp_diagnosis.getText());
                    prepare.setString(6, editApp_treatment.getText());

                    prepare.executeUpdate();
                    alert.successMessage("Updated Successfully!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void displayFields() {
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_patientID.setText(String.valueOf(Data.temp_patID));
        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.setText(Data.temp_appGender);
        editApp_mobileNumber.setText(String.valueOf(Data.temp_appMobileNumber));
        editApp_address.setText(Data.temp_appAddress);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayFields();
    }

}
