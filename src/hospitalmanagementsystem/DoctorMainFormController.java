/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author WINDOWS 10
 */
public class DoctorMainFormController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button logout_btn;

    @FXML
    private Label date_time;

    @FXML
    private Label current_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_tA;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private TableView<AppointmentDetailData> dashboad_tableView;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_date;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_redate;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentDetailData> appointments_tableView;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_appointment_detailID;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_redate;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_diagnosis;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_treatment;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_col_service;

    @FXML
    private TableColumn<AppointmentDetailData, String> appointments_action;

    @FXML
    private AnchorPane appointments_addForm;

    @FXML
    private TextField appointments_appointmentID;

    @FXML
    private Label appointments_patients_name;

    @FXML
    private Label appointments_mobileNumber;

    @FXML
    private Label appointments_patients_gender;

    @FXML
    private Label appointments_patients_address;

    @FXML
    private Button appointments_search;

    @FXML
    private Label appointments_diagnosis;

    @FXML
    private Label appointments_treatment;

    @FXML
    private Label appointments_description;

    @FXML
    private Label appointments_service_appointmentID;

    @FXML
    private Label appointments_service_patientID;

    @FXML
    private TextField appointments_service_diagnosis;

    @FXML
    private TextField appointments_service_treatment;

    @FXML
    private TextArea appointments_service_description;

    @FXML
    private ComboBox<String> appointment_service;

    @FXML
    private DatePicker appointments_service_redate;

    @FXML
    private Button addNewAppointment_btn;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_name;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

//    DATABASE TOOLSs
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    private Map<String, Integer> serviceMap = new HashMap<>();

    private final AlertMessage alert = new AlertMessage();

    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) FROM appointment_detail WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTA() {
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentDetailData> dashboardAppointmentTableView() {

        ObservableList<AppointmentDetailData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment_detail WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentDetailData aData;
            while (result.next()) {
                aData = new AppointmentDetailData(result.getString("appointment_detail_id"), result.getDate("date"),
                        result.getString("description"), result.getDate("re_examination_date"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentDetailData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_date.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_redate.setCellValueFactory(new PropertyValueFactory<>("date"));

        dashboad_tableView.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dashboad_chart_PD.getData().clear();

//        String sql = "SELECT date, COUNT(id) FROM patient WHERE doctor = '"
//                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 8";
        String sql = "SELECT TOP 8 [date], COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY [date] ORDER BY [date] ASC";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardNOA() {

        dashboad_chart_DD.getData().clear();

//        String sql = "SELECT date, COUNT(id) FROM appointment WHERE doctor = '"
//                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 7";
        String sql = "SELECT TOP 7 [date], COUNT(id) FROM appointment WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY [date] ORDER BY [date] ASC";

        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    // TO CLEAR ALL FIELDS
//    public void appointmentClearBtn() {
//        appointment_appointmentID.clear();
//        appointment_name.clear();
//        appointment_gender.getSelectionModel().clearSelection();
//        appointment_mobileNumber.clear();
//        appointment_description.clear();
//        appointment_treatment.clear();
//        appointment_diagnosis.clear();
//        appointment_address.clear();
//        appointment_status.getSelectionModel().clearSelection();
//        appointment_schedule.setValue(null);
//    }
    private Integer appointmentID;

    public void appointmentGetAppointmentID() {
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentDetailData> appointmentGetData() {

        ObservableList<AppointmentDetailData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment_detail WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentDetailData appData;

            while (result.next()) {
//            Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule

                appData = new AppointmentDetailData(
                        result.getInt("appointment_detail_id"),
                        result.getString("appointment_id"),
                        result.getInt("service_id"),
                        result.getDate("date"),
                        result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getDate("re_examination_date"));
                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<AppointmentDetailData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_appointment_detailID.setCellValueFactory(new PropertyValueFactory<>("appointmentDetailID"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_treatment.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        appointments_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        appointments_col_service.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        appointments_col_redate.setCellValueFactory(new PropertyValueFactory<>("reExamDate"));

        appointments_tableView.setItems(appoinmentListData);
    }

// TO SELECT THE DATA PER ROW IN THE TABLE
    public void appointmentSearchBtn() throws SQLException {
        //        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointments_appointmentID.getText().isEmpty()) {
            alert.errorMessage("Please fill the Appointment ID");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                // TÌM APPOINTMENT và lấy thông tin patient TỪ data
                String getAppointment = "SELECT * FROM appointment WHERE appointment_id = '"
                        + appointments_appointmentID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(getAppointment);
                if (result.next()) {
                    // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT 
                    appointments_service_appointmentID.setText(appointments_appointmentID.getText());
                    appointments_service_patientID.setText(result.getString("patient_id"));
                    appointments_patients_name.setText(result.getString("name"));
                    appointments_patients_gender.setText(result.getString("gender"));
                    appointments_mobileNumber.setText(result.getString("mobile_number"));
                    appointments_patients_address.setText(result.getString("address"));
                    appointments_diagnosis.setText(result.getString("diagnosis"));
                    appointments_treatment.setText(result.getString("treatment"));
                    appointments_description.setText(result.getString("description"));
                } else {
                    alert.errorMessage(appointments_appointmentID.getText() + " is not exist");
                }
            } catch (Exception e) {
                alert.errorMessage(appointments_appointmentID.getText() + " is not exist");
            }
        }
    }

    public void appointmentConfirmBtn() throws SQLException {
//        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointments_service_appointmentID.getText().isEmpty()
                || appointments_service_patientID.getText().isEmpty()
                || appointments_service_diagnosis.getText().isEmpty()
                || appointments_service_treatment.getText().isEmpty()
                || appointment_service.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String insertData = "INSERT INTO appointment_detail (appointment_id, service_id"
                    + ", date, price, description, diagnosis, treatment, payment_status"
                    + ", re_examination_date, doctor) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            prepare = connect.prepareStatement(insertData);

            prepare.setString(1, appointments_service_appointmentID.getText());

            int serviceId = serviceMap.get(appointment_service.getSelectionModel().getSelectedItem());
            double price = getServicePrice(serviceId);

            prepare.setInt(2, serviceId);
            prepare.setString(3, "" + sqlDate);
            prepare.setDouble(4, price);
            prepare.setString(5, appointments_service_description.getText());
            prepare.setString(6, appointments_service_diagnosis.getText());
            prepare.setString(7, appointments_service_treatment.getText());
            //Default of doctor: 
            prepare.setString(8, "Pending");
            prepare.setString(9, "" + appointments_service_redate.getValue());
            prepare.setString(10, "" + Data.doctor_id);

            prepare.executeUpdate();

            appointmentShowData();
//            appointmentAppointmentID();
//            appointmentClearBtn();
            alert.successMessage("Successully added!");
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

        appointment_service.setItems(listData);
    }

    private ObservableList<AppointmentDetailData> appointmentListData;

    public void appointmentActionButton() {

        connect = Database.connectDB();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<AppointmentDetailData, String>, TableCell<AppointmentDetailData, String>> cellFactory = (TableColumn<AppointmentDetailData, String> param) -> {
            final TableCell<AppointmentDetailData, String> cell = new TableCell<AppointmentDetailData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                AppointmentDetailData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                                int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_appDID = String.valueOf(aData.getAppointmentDetailID());
                                Data.temp_appID = aData.getAppointmentID();
                                Data.temp_serviceID = aData.getServiceID();
                                Data.temp_appDate = aData.getDate();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appReDate = aData.getReExamDate();

                                // NOW LETS CREATE FXML FOR EDIT APPOINTMENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentDetailForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            AppointmentDetailData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                            int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "DELETE FROM appointment_detail WHERE appointment_detail_id = '"
                                    + aData.getAppointmentDetailID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Appointment ID: " + aData.getAppointmentDetailID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);

                                    prepare.executeUpdate();

                                    alert.successMessage("Deleted Successfully!");
                                    
                                    appointmentGetData();
                                    appointmentShowData();
                                    appointments_tableView.refresh();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    public void profileUpdateBtn() {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialized.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            // CHECK IF THE PATH IS NULL 
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("src\\Directory\\"
                            + Data.doctor_id + ".jpg");

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialized.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIDNumberName() {

        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            appointments_addForm.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
            appointments_addForm.setVisible(false);

            appointmentShowData();
            appointmentActionButton();
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
            appointments_addForm.setVisible(false);
        } else if (event.getSource() == addNewAppointment_btn) {
            dashboard_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            appointments_addForm.setVisible(true);
        }
    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctorPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {
        new Thread() {
            public void run() {
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayAdminIDNumberName();
        runTime();

        dashbboardDisplayTP();
        dashbboardDisplayAP();
        dashbboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        // TO SHOW THE DATA IMMEDIATELY ONCE YOU LOGGED IN YOUR ACCOUNT
        appointmentShowData();
//        PROFILE
        profileLabels();
        profileFields(); // TO DISPLAY ALL DETAILS TO THE FIELDS
        profileGenderList();
        profileSpecializedList();
        profileStatusList();
        profileDisplayImages(); // TO DISPLAY THE PROFILE PICTURE OF THE DOCTOR

        loadServicesData();
    }

}
