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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author WINDOWS 10
 */
public class EditAppointmentDetailFormController implements Initializable {

    @FXML
    private Label editApp_create_date;

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private TextField editApp_gender;

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

    @FXML
    private ComboBox<String> editApp_service;

    @FXML
    private DatePicker editApp_redate;

    private Map<String, Integer> serviceMap = new HashMap<>();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage alert = new AlertMessage();

    public void updateBtn() {

        if (editApp_appointmentID.getText().isEmpty()
                || editApp_description.getText().isEmpty()
                || editApp_diagnosis.getText().isEmpty()
                || editApp_treatment.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            String updateData = "UPDATE appointment_detail SET description = ? "
                    + ", diagnosis = ?, treatment = ?, re_examination_date = ?"
                    + ", service_id = ?, price = ?"
                    + "WHERE appointment_detail_id = '"
                    + Data.temp_appDID + "'";
            connect = Database.connectDB();
            try {
                if (alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: " + Data.temp_appDID
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, editApp_description.getText());
                    prepare.setString(2, editApp_diagnosis.getText());
                    prepare.setString(3, editApp_treatment.getText());
                    prepare.setString(4, "" + editApp_redate.getValue());

                    int serviceId = serviceMap.get(editApp_service.getSelectionModel().getSelectedItem());
                    double price = getServicePrice(serviceId);

                    prepare.setInt(5, serviceId);
                    prepare.setDouble(6, price);
                    prepare.executeUpdate();

                    updatePriceAppointment(Data.temp_serviceID, price, Data.temp_appID);

                    alert.successMessage("Updated Successfully!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void updatePriceAppointment(int service_id, double new_price, String appointment_id) {
        try {
            // Establish database connection
            connect = Database.connectDB();

            String sql = "UPDATE appointment SET total_pay = total_pay - ? + ? WHERE appointment_id = ?";
            double oldPrice = getServicePrice(service_id);
            prepare = connect.prepareStatement(sql);
            prepare.setDouble(1, oldPrice);
            prepare.setDouble(2, new_price);
            prepare.setString(3, appointment_id);

            // Execute the update
            int rowsUpdated = prepare.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update successful!");
            } else {
                System.out.println("No appointment found with the given id.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayFields() {
        editApp_appointmentID.setText(Data.temp_appID);

        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.setText(Data.temp_appGender);

        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_create_date.setText(String.valueOf(Data.temp_appDate));

        // Chuyển đổi Date sang LocalDate
        LocalDate localDate = Instant.ofEpochMilli(Data.temp_appReDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        editApp_redate.setValue(localDate);
        setDefaultService(Data.temp_serviceID);
    }

    private void setDefaultService(int service_id) {
        for (Map.Entry<String, Integer> entry : serviceMap.entrySet()) {
            if (entry.getValue().equals(service_id)) {
                editApp_service.setValue(entry.getKey());
                break;
            }
        }
    }

    public Double getServicePrice(int service_id) {
        String selectData = "SELECT price FROM services WHERE service_id ="
                + service_id;
        Double price = null;
        connect = Database.connectDB();
        PreparedStatement pricePreparedStatement;
        try {
            pricePreparedStatement = connect.prepareStatement(selectData);
            ResultSet rs;
            rs = pricePreparedStatement.executeQuery();

            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public void loadServicesData() {
        List<String> services = new ArrayList<>();

        String selectData = "SELECT * FROM services";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                String name = result.getString("service_name");
                int id = result.getInt("service_id");
                serviceMap.put(name, id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList listData = FXCollections.observableArrayList(serviceMap.keySet());

        editApp_service.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadServicesData();
        displayFields();

    }

}
