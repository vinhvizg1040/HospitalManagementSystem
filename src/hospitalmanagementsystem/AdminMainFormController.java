package hospitalmanagementsystem;

import hospitalmanagementsystem.Data;
import hospitalmanagementsystem.Database;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

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
import javafx.scene.control.*;
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

public class AdminMainFormController implements Initializable {

    // GIVE NAME OF ALL COMPONENTS
    @FXML
    private AnchorPane main_form;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

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
    private Button doctors_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button appointments_btn;

    @FXML
    private Button payment_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private ComboBox<String> patients_gender;
    @FXML
    private Label dashboard_AD;

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
    private TableView<DoctorData> dashboad_tableView;

    @FXML
    private TableColumn<DoctorData, String> dashboad_col_doctorID;

    @FXML
    private TableColumn<DoctorData, String> dashboad_col_name;

    @FXML
    private TableColumn<DoctorData, String> dashboad_col_specialized;

    @FXML
    private TableColumn<DoctorData, String> dashboad_col_status;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<DoctorData> doctors_tableView;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_doctorID;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_name;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_gender;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_contactNumber;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_email;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_specialization;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_address;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_status;

    @FXML
    private TableColumn<DoctorData, String> doctors_col_action;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private TableView<PatientsData> patients_tableView;

    @FXML
    private TableColumn<PatientsData, String> patients_col_patientID;

    @FXML
    private TableColumn<PatientsData, String> patients_col_name;

    @FXML
    private TableColumn<PatientsData, String> patients_col_gender;

    @FXML
    private TableColumn<PatientsData, String> patients_col_contactNumber;

    @FXML
    private TableColumn<PatientsData, String> patients_col_description;

    @FXML
    private TableColumn<PatientsData, String> patients_col_date;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateModify;

    @FXML
    private TableColumn<PatientsData, String> patients_col_dateDelete;

    @FXML
    private TableColumn<PatientsData, String> patients_col_status;

    @FXML
    private TableColumn<PatientsData, String> patients_col_action;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_action;

    @FXML
    private TableColumn<AppointmentData, String> appointments_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_patientID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_status;

    @FXML
    private TableColumn<AppointmentData, String> appointments_total_pay;

    @FXML
    private TableColumn<AppointmentData, String> appointments_payment_status;

    @FXML
    private TableColumn<AppointmentData, String> appointments_services_quantity;

    @FXML
    private TableColumn<AppointmentData, String> appointments_action;

    @FXML
    private TextField appointment_appointmentID;

    @FXML
    private TextField appointment_patientID;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circle;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_adminIO;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_adminID;

    @FXML
    private TextField profile_username;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private AnchorPane payment_form;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private Button appointments_confirmBtn;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Button patients_PI_addBtn;
    @FXML
    private TableView<PatientsData> payment_tableView;

    @FXML
    private TableColumn<PatientsData, String> payment_col_patientID;

    @FXML
    private TableColumn<PatientsData, String> payment_col_name;

    @FXML
    private TableColumn<PatientsData, String> payment_col_gender;

    @FXML
    private TableColumn<PatientsData, String> payment_col_diagnosis;

    @FXML
    private TableColumn<PatientsData, String> payment_col_doctor;

    @FXML
    private TableColumn<PatientsData, String> payment_col_date;

    @FXML
    private Circle payment_circle;

    @FXML
    private Button payment_checkoutBtn;

    @FXML
    private Label payment_patientID;

    @FXML
    private Label payment_name;

    @FXML
    private Label payment_doctor;

    @FXML
    private Label payment_date;

    @FXML
    private Button logout_btn;

    @FXML
    private Button addNewPatient_btn;

    @FXML
    private Button addNewAppointment_btn;

    @FXML
    private Label appointments_PI_mobileNumber;

    @FXML
    private Label appointments_PI_diagnosis;

    @FXML
    private Label appointments_PI_treatment;

    @FXML
    private Label appointments_PI_address;

    @FXML
    private Label appointments_patients_PI_gender;

    @FXML
    private Label appointments_patients_PA_name;

    @FXML
    private Label appontments_PA_patientID;

    @FXML
    private Button appointments_PI_addBtn;

    @FXML
    private AnchorPane patients_addForm;

    @FXML
    private AnchorPane appointments_addForm;

//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private Image image;

    public void dashboardAD() {

        String sql = "SELECT COUNT(id) FROM doctor WHERE status = 'Active' AND delete_date IS NULL";

        connect = Database.connectDB();

        int tempAD = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAD = result.getInt(1);
            }

            dashboard_AD.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_delete IS NULL";

        connect = Database.connectDB();

        int tempTP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTP = result.getInt(1);
            }

            dashboard_TP.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardAP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_delete IS NULL AND status = 'Active'";

        connect = Database.connectDB();

        int tempAP = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempAP = result.getInt(1);
            }

            dashboard_AP.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTA() {

        String sql = "SELECT COUNT(id) FROM appointment WHERE date_delete IS NULL";

        connect = Database.connectDB();

        int tempTA = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempTA = result.getInt(1);
            }

            dashboard_tA.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> dashboardGetDoctorData() {

        ObservableList<DoctorData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            DoctorData dData;

            while (result.next()) {
//                DoctorData(String doctorID, String fullName, String specialized, String status)
                dData = new DoctorData(result.getString("doctor_id"),
                        result.getString("full_name"), result.getString("specialized"),
                        result.getString("status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DoctorData> dashboardGetDoctorListData;

    public void dashboardGetDoctorDisplayData() {
        dashboardGetDoctorListData = dashboardGetDoctorData();

        dashboad_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dashboad_col_specialized.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetDoctorListData);

    }

    public void dashboardPatientDataChart() {
        dashboad_chart_PD.getData().clear();

        String selectData = "SELECT TOP 8 CONVERT(date, date) AS converted_date, COUNT(id) "
                + "FROM patient "
                + "WHERE date_delete IS NULL "
                + "GROUP BY CONVERT(date, date) "
                + "ORDER BY CONVERT(date, date) ASC";

        connect = Database.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDoctorDataChart() {
        dashboad_chart_DD.getData().clear();

        String selectData = "SELECT TOP 6 [date], COUNT(id) "
                + "FROM doctor "
                + "WHERE delete_date IS NULL "
                + "GROUP BY [date] "
                + "ORDER BY [date] ASC";

        connect = Database.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> doctorGetData() {
        ObservableList<DoctorData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM doctor";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            DoctorData dData;
            while (result.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
                dData = new DoctorData(result.getInt("id"), result.getString("doctor_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getString("email"), result.getString("gender"),
                        result.getLong("mobile_number"), result.getString("specialized"),
                        result.getString("address"), result.getString("image"),
                        result.getDate("date"), result.getDate("modify_date"),
                        result.getDate("delete_date"), result.getString("status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<DoctorData> doctorListData;

    public void doctorDisplayData() {
        doctorListData = doctorGetData();

        doctors_col_doctorID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        doctors_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        doctors_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        doctors_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        doctors_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        doctors_col_specialization.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        doctors_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        doctors_tableView.setItems(doctorListData);

    }

    public void doctorActionButton() {

        connect = Database.connectDB();
        doctorListData = doctorGetData();

        Callback<TableColumn<DoctorData, String>, TableCell<DoctorData, String>> cellFactory = (TableColumn<DoctorData, String> param) -> {
            final TableCell<DoctorData, String> cell = new TableCell<DoctorData, String>() {
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

                                DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                                int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_doctorID = pData.getDoctorID();
                                Data.temp_doctorName = pData.getFullName();
                                Data.temp_doctorEmail = pData.getEmail();
                                Data.temp_doctorPassword = pData.getPassword();
                                Data.temp_doctorSpecialized = pData.getSpecialized();
                                Data.temp_doctorGender = pData.getGender();
                                Data.temp_doctorMobileNumber = String.valueOf(pData.getMobileNumber());
                                Data.temp_doctorAddress = pData.getAddress();
                                Data.temp_doctorStatus = pData.getStatus();
                                Data.temp_doctorImagePath = pData.getImage();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditDoctorForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            DoctorData pData = doctors_tableView.getSelectionModel().getSelectedItem();
                            int num = doctors_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE doctor SET delete_date = ? WHERE doctor_id = '"
                                    + pData.getDoctorID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

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
            doctorDisplayData();
            return cell;
        };

        doctors_col_action.setCellFactory(cellFactory);
        doctors_tableView.setItems(doctorListData);

    }

    public ObservableList<PatientsData> patientGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PatientsData pData;

            while (result.next()) {
//                PatientsData(Integer id, Integer patientID, String password, String fullName, Long mobileNumber
//            , String address, String image, String description, String diagnosis, String treatment
//            , String doctor, String specialized, Date date, Date dateModify
//            , Date dateDelete, String status)
                pData = new PatientsData(result.getInt("id"), result.getInt("patient_id"),
                        result.getString("password"), result.getString("full_name"),
                        result.getLong("mobile_number"), result.getString("gender"),
                        result.getString("address"),
                        result.getString("image"), result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        //                        result.getString("doctor"),result.getString("specialized"), 
                        result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patients_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patients_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patients_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patients_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patients_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        patients_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patients_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patients_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableView.setItems(patientListData);
    }

    public void patientActionButton() {

        connect = Database.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<PatientsData, String>, TableCell<PatientsData, String>> cellFactory = (TableColumn<PatientsData, String> param) -> {
            final TableCell<PatientsData, String> cell = new TableCell<PatientsData, String>() {
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

                                PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                                int num = patients_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_PatientID = pData.getPatientID();
                                Data.temp_address = pData.getAddress();
                                Data.temp_name = pData.getFullName();
                                Data.temp_gender = pData.getGender();
                                Data.temp_number = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditPatientForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            PatientsData pData = patients_tableView.getSelectionModel().getSelectedItem();
                            int num = patients_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "DELETE FROM patient WHERE patient_id = '"
                                    + pData.getPatientID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Patient ID: " + pData.getPatientID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

//                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    patientGetData();
                                    alert.successMessage("Deleted Successfully!");

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
            doctorDisplayData();
            return cell;
        };

        patients_col_action.setCellFactory(cellFactory);
        patients_tableView.setItems(patientListData);

    }

    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
//            AppointmentData(Integer id, Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule)
                aData = new AppointmentData(result.getInt("id"), result.getInt("appointment_id"),
                        result.getLong("patient_id"),
                        result.getString("name"), result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("description"), result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("address"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getInt("total_pay"), result.getString("payment_status"),
                        result.getInt("quantity"),
                        result.getDate("schedule"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        appointments_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        appointments_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        appointments_total_pay.setCellValueFactory(new PropertyValueFactory<>("totalPay"));
        appointments_payment_status.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        appointments_services_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        appointments_tableView.setItems(appointmentListData);

    }

    public void appointmentActionButton() {

        connect = Database.connectDB();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<AppointmentData, String>, TableCell<AppointmentData, String>> cellFactory = (TableColumn<AppointmentData, String> param) -> {
            final TableCell<AppointmentData, String> cell = new TableCell<AppointmentData, String>() {
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

                                AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                                int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please select item first");
                                    return;
                                }

                                Data.temp_appID = String.valueOf(aData.getAppointmentID());
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                                Data.temp_appStatus = aData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT APPOINTMENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            AppointmentData aData = appointments_tableView.getSelectionModel().getSelectedItem();
                            int num = appointments_tableView.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Please select item first");
                                return;
                            }

                            String deleteData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = '"
                                    + aData.getAppointmentID() + "'";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Appointment ID: " + aData.getAppointmentID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    doctorGetData();
                                    alert.successMessage("Deleted Successfully!");

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
            doctorDisplayData();
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    public ObservableList<PatientsData> paymentGetData() {

        ObservableList<PatientsData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient WHERE date_delete IS NULL AND status_pay IS NULL";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            PatientsData pData;
            while (result.next()) {
//                (Integer id, Integer patientID, String fullName, String gender
//            , String description, String diagnosis, String treatment
//            , String doctor, String image, Date date)
                pData = new PatientsData(result.getInt("id"),
                        result.getInt("patient_id"), result.getString("full_name"),
                        result.getString("gender"), result.getString("description"),
                        result.getString("diagnosis"), result.getString("treatment"),
                        //                        result.getString("doctor"), 
                        result.getString("image"), result.getDate("date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PatientsData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        payment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        payment_tableView.setItems(paymentListData);

    }

    public void paymentSelectItems() {

        PatientsData pData = payment_tableView.getSelectionModel().getSelectedItem();
        int num = payment_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        if (pData.getImage() != null) {
            String path = "File:" + pData.getImage();
            image = new Image(path, 144, 105, false, true);
            payment_circle.setFill(new ImagePattern(image));

            Data.temp_path = pData.getImage();
        }

        Data.temp_PatientID = pData.getPatientID();
        Data.temp_name = pData.getFullName();
        Data.temp_date = String.valueOf(pData.getDate());

        payment_patientID.setText(String.valueOf(pData.getPatientID()));
        payment_name.setText(pData.getFullName());
//        payment_doctor.setText(pData.getDoctor());
        payment_date.setText(String.valueOf(pData.getDate()));

    }

    public void patientConfirmBtn() throws SQLException {
        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            Database.connectDB();
            try {
                // Xác định năm hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);

                // Tìm số thứ tự của bản ghi patient cuối cùng trong cơ sở dữ liệu
                String lastPatientIDQuery = "SELECT MAX(CAST(RIGHT(patient_id, 5) AS INT)) AS last_patient_id FROM patient";
                statement = connect.createStatement();
                result = statement.executeQuery(lastPatientIDQuery);
                int lastPatientID = 0;
                if (result.next()) {
                    lastPatientID = result.getInt("last_patient_id");
                }

                // Tạo patient_id mới
                String newPatientID = String.format("%d%05d", year, lastPatientID + 1);

                // Kiểm tra xem patient_id mới đã tồn tại chưa
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = ?";
                prepare = connect.prepareStatement(checkPatientID);
                prepare.setString(1, newPatientID);
                result = prepare.executeQuery();
                if (result.next()) {
                    alert.errorMessage(newPatientID + " is already exist");
                } else {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
                    patients_PA_patientID.setText(newPatientID);
                    patients_PA_password.setText(patients_password.getText());
                    patients_PA_dateCreated.setText(String.valueOf(sqlDate));

                    // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
                    patients_PI_patientName.setText(patients_patientName.getText());
                    patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
                    patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
                    patients_PI_address.setText(patients_address.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appointmentConfirmBtn() throws SQLException {
        //        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointmentID.getText().isEmpty()
                || appointment_patientID.getText().isEmpty()
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                //|| appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            try {
                // TÌM PATIENT và lấy thông tin patient TỪ data
                // Kiểm tra xem patient_id mới đã tồn tại chưa
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + appointment_patientID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
//                    patient_name = result.getString("full_name");
//                    patient_gender = result.getString("gender");

                    // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT 
                    appontments_PA_patientID.setText(appointment_patientID.getText());
                    appointments_patients_PA_name.setText(result.getString("full_name"));
                    appointments_PI_mobileNumber.setText(appointment_mobileNumber.getText());
                    appointments_patients_PI_gender.setText(result.getString("gender"));
                    // TO DISPLAY THE DATA FROM PERSONAL INFORMATION 
                    appointments_PI_diagnosis.setText(appointment_diagnosis.getText());
                    appointments_PI_treatment.setText(appointment_treatment.getText());
                    appointments_PI_address.setText(appointment_address.getText());
                } else {
                    alert.errorMessage(appointment_patientID.getText() + " is not exist");
                }
            } catch (Exception e) {
//                alert.errorMessage(appointment_patientID.getText() + " is not exist");
                alert.errorMessage(e.getMessage());
            }

        }
    }

    public void appointmentInsertBtn() {

//        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointmentID.getText().isEmpty()
                || appointment_patientID.getText().isEmpty()
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                //|| appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointment_appointmentID.getText();
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    alert.errorMessage(appointment_appointmentID.getText() + " was already taken");
                } else {

                    String insertData = "INSERT INTO appointment (appointment_id, patient_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, status, schedule) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    long patientID = Long.parseLong(appointment_patientID.getText());
                    long mobileNumber = Long.parseLong(appointment_mobileNumber.getText());

                    prepare.setString(1, appointment_appointmentID.getText());
                    prepare.setLong(2, patientID);
                    prepare.setString(3, appointments_patients_PA_name.getText());
                    prepare.setString(4, appointments_patients_PI_gender.getText());
                    prepare.setString(5, appointment_description.getText());
                    prepare.setString(6, appointment_diagnosis.getText());
                    prepare.setString(7, appointment_treatment.getText());
                    prepare.setLong(8, mobileNumber);
                    prepare.setString(9, appointment_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(10, "" + sqlDate);
                    prepare.setString(11, (String) appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(12, "" + appointment_schedule.getValue());

                    prepare.executeUpdate();

//                    appointmentShowData();
//                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successully added!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appoinmentListData);
    }
// TO SELECT THE DATA PER ROW IN THE TABLE

    public void patientClearFields() {
//        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

//        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void patientAddBtn() {
        if (patients_PA_password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            alert.errorMessage("Something went wrong");
        } else {
            Database.connectDB();
            try {
                // Xác định năm hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);

                // Tìm số thứ tự của bản ghi patient cuối cùng trong cơ sở dữ liệu
                String lastPatientIDQuery = "SELECT MAX(CAST(RIGHT(patient_id, 5) AS INT)) AS last_patient_id FROM patient";
                statement = connect.createStatement();
                result = statement.executeQuery(lastPatientIDQuery);
                int lastPatientID = 0;
                if (result.next()) {
                    lastPatientID = result.getInt("last_patient_id");
                }

                // Tạo patient_id mới
                String newPatientID = String.format("%d%05d", year, lastPatientID + 1);

                // Kiểm tra xem patient_id mới đã tồn tại chưa
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = ?";
                prepare = connect.prepareStatement(checkPatientID);
                prepare.setString(1, newPatientID);
                result = prepare.executeQuery();
                if (result.next()) {
                    alert.errorMessage(newPatientID + " is already exist");
                } else {
                    // Thêm bản ghi patient vào cơ sở dữ liệu
                    String insertData = "INSERT INTO patient (patient_id, password, full_name, mobile_number,gender, "
                            + "address, date, "
                            + "status) "
                            + "VALUES(?,?,?,?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, newPatientID);
                    prepare.setString(2, patients_PA_password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_mobileNumber.getText());
                    prepare.setString(5, patients_PI_gender.getText());

                    prepare.setString(6, patients_PI_address.getText());
                    prepare.setString(7, "" + sqlDate);
                    prepare.setString(8, "Active");
                    prepare.executeUpdate();

                    alert.successMessage("Added successfully!");

                    // Xóa tất cả các trường và một số nhãn
                    patientClearFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paymentCheckOutBtn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("CheckOutPatient.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Check-Out");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileUpdateBtn() {
        connect = Database.connectDB();
        if (profile_adminID.getText().isEmpty()
                || profile_username.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE admin SET username = ?, email = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;

                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    profileDisplayInfo();

                    alert.successMessage("Updated Successfully");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                String updateData = "UPDATE admin SET username = ?, email = ?, image = ?, gender = ? "
                        + "WHERE admin_id = " + Data.admin_id;
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_username.getText());
                    prepare.setString(2, profile_email.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("D:\\Aptech\\HK2\\Project\\code\\HospitalManagementSystem\\src\\Admin_Directory\\"
                            + Data.admin_id + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    prepare.setString(3, copy.toAbsolutePath().toString());
                    prepare.setString(4, profile_status.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();
                    profileDisplayInfo();
                    profileDisplayImages();
                    alert.successMessage("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        connect = Database.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                tempPath1 = "File:" + result.getString("image");
                tempPath2 = "File:" + result.getString("image");

                if (result.getString("image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_circle.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileInsertImage() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_circle.setFill(new ImagePattern(image));
        }

    }

    public void profileDisplayInfo() {

        String sql = "SELECT * FROM admin WHERE admin_id = " + Data.admin_id;
        System.out.println(Data.admin_id);
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_adminID.setText(result.getString("admin_id"));
                profile_username.setText(result.getString("username"));
                profile_email.setText(result.getString("email"));
                profile_status.getSelectionModel().select(result.getString("gender"));

                profile_label_adminIO.setText(result.getString("admin_id"));
                profile_label_name.setText(result.getString("username"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Data.gender) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    // MAKE SURE THAT THE ID OF EVERY COMPONENTS TO THE OTHER IS DIFFERENT
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            dashboardAD();
            dashboardTP();
            dashboardAP();
            dashboardTA();
            dashboardGetDoctorDisplayData();

            current_form.setText("Dashboard Form");
        } else if (event.getSource() == doctors_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
            doctorDisplayData();
            doctorActionButton();

            current_form.setText("Doctor's Form");
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
            patientDisplayData();
            patientActionButton();
            current_form.setText("Patient's Form");
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            appointmentDisplayData();

            current_form.setText("Appointment's Form");
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            appointments_addForm.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            payment_form.setVisible(true);

            paymentDisplayData();

            current_form.setText("Payment Form");
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            profile_form.setVisible(true);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Profile Form");
        } else if (event.getSource() == addNewPatient_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            appointments_addForm.setVisible(false);
            patients_addForm.setVisible(true);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Add New Patient Form");
        } else if (event.getSource() == addNewAppointment_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(true);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            current_form.setText("Add New Appointment Form");
        }

    }

    public void displayAdminIDUsername() {

        String sql = "SELECT * FROM admin WHERE username = '"
                + Data.admin_username + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nav_adminID.setText(result.getString("admin_id"));
                String tempUsername = result.getString("username");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                nav_username.setText(tempUsername);
                top_username.setText(tempUsername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Are you sure you want to logout?")) {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();
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

                        Thread.sleep(1000); // 1000 ms = 1s

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

    // TO CLEAR ALL FIELDS
    public void appointmentClearBtn() {
        appointment_appointmentID.clear();
        appointment_patientID.clear();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }

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

    public void appointmentAppointmentID() {
        appointmentGetAppointmentID();

        appointment_appointmentID.setText("" + appointmentID);
        appointment_appointmentID.setDisable(true);

    }

    public void registerAppointmentID() {
        String appointmentID = "";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM appointment";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                tempID = result.getInt(1);
            }

            if (tempID == 0) {
                tempID += 1;
                appointmentID += tempID;
            } else {
                appointmentID += (tempID + 1);
            }

            appointment_appointmentID.setText(appointmentID);
            appointment_appointmentID.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        runTime();
        displayAdminIDUsername();

//        DASHBOARD
        dashboardAD();
        dashboardTP();
        dashboardAP();
        dashboardTA();
        dashboardGetDoctorDisplayData();
        dashboardPatientDataChart();
        dashboardDoctorDataChart();

        // TO DISPLAY IMMEDIATELY THE DATA OF DOCTORS IN TABLEVIEW
        doctorDisplayData();
        doctorActionButton();

        // TO DISPLAY IMMEDIATELY THE DATA OF PATIENTS IN TABLEVIEW
        patientDisplayData();
        patientActionButton();

        // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
        appointmentDisplayData();
        appointmentActionButton();
        registerAppointmentID();
        // TO DISPLAY IMMEDIATELY THE DATA OF PAYMENT IN TABLEVIEW
        paymentDisplayData();
        patientGenderList();
        profileStatusList();
        profileDisplayInfo();
        profileDisplayImages();
    }

}

// THATS IT FOR THESE VIDEOS, THANKS FOR WATCHING
// SUBSCRIBE OUR CHANNEL FOR THE SUPPORT
// THANK YOU!! :)))))))
