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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author WINDOWS 10
 */
public class EditPatientFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField edit_patientID;

    @FXML
    private TextField edit_name;

    @FXML
    private ComboBox<String> edit_gender;

    @FXML
    private TextField edit_contactNumber;

    @FXML
    private TextArea edit_address;
    @FXML
    private TextArea edit_description;
    @FXML
    private TextField edit_bloodGroup;
    @FXML
    private TextField edit_ccid;
    @FXML
    private TextField edit_emergancyNumber;
    @FXML
    private TextField edit_insurance;
    @FXML
    private DatePicker edit_DOB;
    @FXML
    private ComboBox<String> edit_status;

    @FXML
    private Button edit_updateBtn;
    @FXML
    private Label edit_date_created;

    @FXML
    private Label edit_date_deleted;

    @FXML
    private Label edit_date_modified;
    private AlertMessage alert = new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public void updateBtn() {

        if (edit_patientID.getText().isEmpty() || edit_name.getText().isEmpty()
                || edit_gender.getSelectionModel().getSelectedItem() == null
                || edit_contactNumber.getText().isEmpty()
                || edit_address.getText().isEmpty()
                || edit_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            String updateData = "UPDATE patient SET full_name = ?, gender = ?"
                    + ", mobile_number = ?, address = ?, status = ?, date_modify = ?,date = ?,patients_EmergencyNumber = ?, patients_ccid = ?, patients_bloodGroup = ?, patients_insurance =?, description = ? "
                    + "WHERE patient_id = '"
                    + edit_patientID.getText() + "'";
            connect = Database.connectDB();
            try {
                if (alert.confirmationMessage("Are you sure you want to UPDATE Patient ID: " + edit_patientID.getText()
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, edit_name.getText());
                    prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(3, edit_contactNumber.getText());
                    prepare.setString(4, edit_address.getText());
                    prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.setString(7, edit_DOB.getValue().toString());
                    prepare.setString(8, edit_emergancyNumber.getText());
                    prepare.setString(9, edit_ccid.getText());
                    prepare.setString(10, edit_bloodGroup.getText());
                    prepare.setString(11, edit_insurance.getText());
                    prepare.setString(12, edit_description.getText());

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

    // CLOSE THE EDITPATIENTFORM FXML FILE AND OPEN IT AGAIN
    public void setField() {
        edit_patientID.setText(Data.temp_PatientID != null ? String.valueOf(Data.temp_PatientID) : "");
        edit_name.setText(Data.temp_name != null ? Data.temp_name : "");
        edit_gender.getSelectionModel().select(Data.temp_gender);
        edit_contactNumber.setText(Data.temp_number != null ? String.valueOf(Data.temp_number) : "");
        edit_address.setText(Data.temp_address != null ? Data.temp_address : "");
        edit_status.getSelectionModel().select(Data.temp_status);
        edit_insurance.setText(Data.temp_insurance != null ? String.valueOf(Data.temp_insurance) : "");
        edit_emergancyNumber.setText(Data.temp_emergancyNumber != null ? String.valueOf(Data.temp_emergancyNumber) : "");
        edit_ccid.setText(Data.temp_ccid != null ? String.valueOf(Data.temp_ccid) : "");
        edit_bloodGroup.setText(Data.temp_bloodGroup != null ? String.valueOf(Data.temp_bloodGroup) : "");
        edit_date_created.setText(Data.temp_date_created != null ? Data.temp_date_created : "");
        edit_date_deleted.setText(Data.temp_date_deleted != null ? String.valueOf(Data.temp_date_deleted) : "");
        edit_date_modified.setText(Data.temp_date_modified != null ? String.valueOf(Data.temp_date_modified) : "");
        edit_description.setText(Data.temp_description != null ? String.valueOf(Data.temp_description) : "");

        if (Data.temp_date != null) {
            edit_DOB.setValue(LocalDate.parse(Data.temp_date));
        } else {
            edit_DOB.setValue(null);
        }
    }


    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        genderList();
        statusList();
    }

}
