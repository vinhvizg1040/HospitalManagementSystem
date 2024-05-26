package hospitalmanagementsystem;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    private Button services_btn;
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
    public AnchorPane doctors_form;
    @FXML
    private DatePicker doctor_DOB;
    @FXML
    private TextField password_ShowPassword;

    @FXML
    private PasswordField password_password;

    @FXML
    private CheckBox passwordcf_checkBox;

    @FXML
    private PasswordField passwordcf_password;

    @FXML
    private TextField passwordcf_showPassword;
    @FXML
    private Label doctor_PA_dateCreated;

    @FXML
    private Label doctor_PA_doctorID;
    @FXML
    private Label doctor_PA_password;

    @FXML
    private Label doctor_PI_DOB;

    @FXML
    private Button doctor_PI_addBtn;

    @FXML
    private Label doctor_PI_address;

    @FXML
    private Label doctor_PI_doctorName;

    @FXML
    private Label doctor_PI_email;

    @FXML
    private Label doctor_PI_gender;

    @FXML
    private Label doctor_PI_mobileNumber;

    @FXML
    private Label doctor_PI_specialized;

    @FXML
    private TextArea doctor_address;
    @FXML
    private Button addNewDoctor_btn;
    @FXML
    private Button doctor_confirmBtn;

    @FXML
    private TextField doctor_email;

    @FXML
    private ComboBox<?> doctor_gender;

    @FXML
    private TextField doctor_mobileNumber;

    @FXML
    private TextField doctor_name;

    @FXML
    private ComboBox<String> doctor_specialized;

    @FXML
    private AnchorPane doctors_addForm;

    @FXML
    private AnchorPane servicesPane;

    @FXML
    public TableView<DoctorData> doctors_tableView;

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
    private TextArea appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextArea appointment_address;

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
    private TableColumn<?, ?> payment_col_status;

    @FXML
    private TableColumn<?, ?> payment_col_totalPay;
    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private TextField patients_mobileNumber;
    @FXML
    private TextField patients_bloodGroup;
    @FXML
    private TextField patients_ccid;
    @FXML
    private DatePicker patients_DOB;
    @FXML
    private TextField patients_EmercencyNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextArea patients_address;
    @FXML
    private TextField patients_insurance;

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
    private Label patients_PI_description;
    @FXML
    private Label patients_PI_CCID;

    @FXML
    private Label patients_PI_DOB;
    @FXML
    private Label patients_PI_bloodGroup;

    @FXML
    private Label patients_PI_emergencyNumber;

    @FXML
    private Label patients_PI_insuranceNumber;

    @FXML
    private Button patients_PI_addBtn;
    @FXML
    private TableView<PaymentData> payment_tableView;

    @FXML
    private TableColumn<PaymentData, String> payment_col_patientID;

    @FXML
    private TableColumn<PaymentData, String> payment_col_name;

    @FXML
    private TableColumn<PaymentData, String> payment_col_gender;
    @FXML
    private TableColumn<PaymentData, String> payment_col_services;

    @FXML
    private TableColumn<PaymentData, String> payment_col_diagnosis;

    @FXML
    private TableColumn<PaymentData, String> payment_col_doctor;

    @FXML
    private TableColumn<PaymentData, String> payment_col_date;

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
    private TextArea patients_description;

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
    private TableView<Services> servicesTable;
    @FXML
    private TableColumn<Services, Integer> columnServiceId;
    @FXML
    private TableColumn<Services, String> columnServiceName;
    @FXML
    private TableColumn<Services, String> columnDescription;
    @FXML
    private TableColumn<Services, Double> columnPrice;
    @FXML
    private TableColumn<Services, Void> columnAction;
    @FXML
    private TextField service_name;
    @FXML
    private TextField service_description;
    @FXML
    private TextField service_price;

    @FXML
    private Button service_confirmBtn;
    @FXML
    private Button service_addBtn;
    @FXML
    private Button service_editBtn;
    @FXML
    private Button service_deleteBtn;

    private ObservableList<Services> serviceList;
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

        String sql = "SELECT COUNT(appointment_id) FROM appointment WHERE date_delete IS NULL";

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

    public void doctorConfirmBtn() {

        if (doctor_name.getText().isEmpty()
                || doctor_gender.getSelectionModel().getSelectedItem() == null
                || doctor_mobileNumber.getText().isEmpty()
                || password_password.getText().isEmpty()
                || passwordcf_password.getText().isEmpty()
                || doctor_email.getText().isEmpty()
                || doctor_DOB.getValue() == null
                || doctor_specialized.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
//            patients_mobileNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!doctor_mobileNumber.getText().matches("-?([0-9][0-9]*)?") || doctor_mobileNumber.getText().length() > 10) {
                // Show an error message or handle invalid input
                alert.errorMessage("Phone Number input error, just number and <10 character");

            } else if (passwordcf_password.getText().length() < 8 || password_password.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
                alert.errorMessage("Invalid Password, at least 8 characters needed");
            } else {
                LocalDate dob = doctor_DOB.getValue();
                LocalDate currentDate = LocalDate.now();
                if (dob == null) {
                    alert.errorMessage("Date of Birth cannot be null");
                } else if (Period.between(dob, currentDate).getYears() < 24) {
                    alert.errorMessage("Age must be greater than 24 years");
                } else {
                    String password = password_password.getText();
                    String passwordcf = passwordcf_password.getText();
                    String passwordShowcf = passwordcf_showPassword.getText();
                    String passwordShow = password_ShowPassword.getText();
                    if (!(password.equals(passwordcf)) || !(passwordShow.equals(passwordShowcf))) {
                        System.out.println(password);
                        System.out.println(passwordcf);
                        alert.errorMessage("Confirm Password is not correct");
                    } else {
                        Database.connectDB();
                        try {

                            // Tìm số thứ tự của bản ghi doctor cuối cùng trong cơ sở dữ liệu
//                         String lastDoctorIDQuery = "SELECT MAX(CAST(RIGHT(doctor_id, 5) AS INT)) AS last_doctor_id FROM doctor";
                            String lastDoctorIDQuery = "SELECT MAX(CAST(SUBSTRING(doctor_id, 5, LEN(doctor_id)) AS INT)) AS last_doctor_id FROM doctor";

                            statement = connect.createStatement();
                            result = statement.executeQuery(lastDoctorIDQuery);
                            int lastDoctorID = 0;
                            if (result.next()) {
                                lastDoctorID = result.getInt("last_doctor_id");
                            }

                            // Tạo doctor mới
                            String newDoctorID = String.format("DID-%d", lastDoctorID + 1);
                            System.out.println(newDoctorID);

                            // Kiểm tra xem patient_id mới đã tồn tại chưa
                            String checkdoctorID = "SELECT * FROM doctor WHERE doctor_id = ?";
                            prepare = connect.prepareStatement(checkdoctorID);
                            prepare.setString(1, newDoctorID);
                            result = prepare.executeQuery();
                            if (result.next()) {
                                alert.errorMessage(newDoctorID + " is already exist");
                            } else {
                                Date date = new Date();
                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
                                doctor_PA_doctorID.setText(newDoctorID);
                                doctor_PA_password.setText(password_password.getText());
                                doctor_PA_dateCreated.setText(sqlDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                                // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
                                doctor_PI_doctorName.setText(doctor_name.getText());
                                doctor_PI_gender.setText((String) doctor_gender.getSelectionModel().getSelectedItem());
                                doctor_PI_mobileNumber.setText(doctor_mobileNumber.getText());
                                doctor_PI_address.setText(doctor_address.getText());
                                doctor_PI_DOB.setText(doctor_DOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                                doctor_PI_email.setText(doctor_email.getText());
                                doctor_PI_specialized.setText((String) doctor_specialized.getSelectionModel().getSelectedItem());
                                patients_PI_CCID.setText(patients_ccid.getText());
                                patients_PI_insuranceNumber.setText(patients_insurance.getText());
                                patients_PI_description.setText(patients_description.getText());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
//            }
//        );

        }
    }
    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist", "Gynecologist", "Cardiologist"};

//    public void doctorSpecializedList() {
//
//        List<String> listS = new ArrayList<>();
//
//        for (String data : specialization) {
//            listS.add(data);
//        }
//
//        ObservableList listData = FXCollections.observableArrayList(listS);
//        doctor_specialized.setItems(listData);
//    }
    private void doctorGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        doctor_gender.setItems(listData);

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
                        result.getDate("date"), result.getDate("date_created"), result.getDate("modify_date"),
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
//        doctors_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
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
                                Data.temp_date_created = String.valueOf(pData.getDateCreated());
                                Data.temp_date_modified = String.valueOf(pData.getDateModify());
                                Data.temp_date_deleted = String.valueOf(pData.getDateDelete());
                                Data.temp_date = String.valueOf(pData.getDate());

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

                            String deleteData = "UPDATE doctor SET delete_date = ?, status = 'Deleted' WHERE doctor_id = ?";

                            try {
                                if (alert.confirmationMessage("Are you sure you want to delete Doctor ID: " + pData.getDoctorID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setDate(1, sqlDate);
                                    prepare.setString(2, pData.getDoctorID());

                                    prepare.executeUpdate();
                                    alert.successMessage("Deleted Successfully!");

                                    doctorGetData();
                                    doctorDisplayData();
                                    doctors_tableView.refresh();
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
                        result.getDate("date"),
                        result.getDate("date_modify"), result.getDate("date_delete"),
                        result.getString("status"), result.getLong("patients_EmergencyNumber"),
                        result.getString("patients_ccid"), result.getString("patients_bloodGroup"), result.getString("patients_insurance"), result.getDate("date_created")
                );

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
        patients_col_date.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Date date = cellData.getValue().getDate();
            if (date != null) {
                LocalDate localDate = ((java.sql.Date) date).toLocalDate();
                property.setValue(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            } else {
                property.setValue("");
            }
            return property;
        });

        patients_col_dateModify.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Date dateModify = cellData.getValue().getDateModify();
            if (dateModify != null) {
                LocalDate localDateModify = ((java.sql.Date) dateModify).toLocalDate();
                property.setValue(localDateModify.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            } else {
                property.setValue("");
            }
            return property;
        });

        patients_col_dateDelete.setCellValueFactory(cellData -> {
            SimpleStringProperty property = new SimpleStringProperty();
            Date dateDelete = cellData.getValue().getDateDelete();
            if (dateDelete != null) {
                LocalDate localDateDelete = ((java.sql.Date) dateDelete).toLocalDate();
                property.setValue(localDateDelete.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            } else {
                property.setValue("");
            }
            return property;
        });

        patients_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        patients_tableView.setItems(patientListData);
    }
    @FXML
    private TextField searchPatientByNameField;


    @FXML
    private TextField searchPatientByNameField;

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
                                Data.temp_date = String.valueOf(pData.getDate());
                                Data.temp_emergancyNumber = String.valueOf(pData.getPatientsEmergencyNumber());
                                Data.temp_ccid = pData.getPatientsCCID();
                                Data.temp_insurance = pData.getPatientsInsurance();
                                Data.temp_bloodGroup = pData.getPatientsBloodGroup();
                                Data.temp_description = pData.getDescription();
                                Data.temp_date_created = String.valueOf(pData.getDateCreated());
                                Data.temp_date_modified = String.valueOf(pData.getDateModify());
                                Data.temp_date_deleted = String.valueOf(pData.getDateDelete());
                                System.out.println("Data.temp_date" + Data.temp_date);
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
                aData = new AppointmentData(
                        result.getString("appointment_id"),
                        result.getLong("patient_id"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getString("address"),
                        result.getDate("date"),
                        result.getDate("date_modify"),
                        result.getDate("date_delete"),
                        result.getBigDecimal("total_pay"),
                        result.getString("payment_status"),
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
                                Data.temp_patID = aData.getPatientID();
                                Data.temp_appName = aData.getName();
                                Data.temp_appGender = aData.getGender();
                                Data.temp_appAddress = aData.getAddress();
                                Data.temp_appDescription = aData.getDescription();
                                Data.temp_appDiagnosis = aData.getDiagnosis();
                                Data.temp_appTreatment = aData.getTreatment();
                                Data.temp_appMobileNumber = aData.getMobileNumber();

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

                                    alert.successMessage("Deleted Successfully!");

                                    appointmentGetData();
                                    appointmentDisplayData();
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
            appointmentDisplayData();
            return cell;
        };

        appointments_action.setCellFactory(cellFactory);
        appointments_tableView.setItems(appointmentListData);

    }

    public ObservableList<PaymentData> paymentGetData() {

        ObservableList<PaymentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM payment";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
//                (Integer id, Integer patientID, String fullName, String gender
//            , String description, String diagnosis, String treatment
//            , String doctor, String image, Date date)
                PaymentData pData = new PaymentData(
                        result.getInt("id"),
                        result.getInt("patient_id"),
                        result.getString("patient_name"),
                        result.getString("doctor"),
                        result.getInt("total_days"),
                        result.getInt("total_price"),
                        result.getString("services"),
                        result.getTimestamp("date"), // Sử dụng result.getTimestamp cho kiểu Timestamp
                        result.getTimestamp("date_checkout"),
                        result.getString("status_pay"),
                        result.getTimestamp("date_pay")
                );
                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<PaymentData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        payment_col_patientID.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        payment_col_name.setCellValueFactory(new PropertyValueFactory<>("patientName"));
//        payment_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
//        payment_col_diagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        payment_col_services.setCellValueFactory(new PropertyValueFactory<>("services"));
        payment_col_doctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        payment_col_totalPay.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        payment_col_status.setCellValueFactory(new PropertyValueFactory<>("statusPay"));
        payment_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        payment_tableView.setItems(paymentListData);

    }

    public void paymentSelectItems() {

        PaymentData pData = payment_tableView.getSelectionModel().getSelectedItem();
        int num = payment_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

//
//        Data.temp_PatientID = pData.getPatientID();
//        Data.temp_name = pData.getFullName();
        Data.temp_date = String.valueOf(pData.getDate());

        payment_patientID.setText(String.valueOf(pData.getPatientId()));
//        payment_name.setText(pData.());
        payment_doctor.setText(pData.getDoctor());
        payment_date.setText(String.valueOf(pData.getDate()));

    }

    public void patientConfirmBtn() throws SQLException {

        if (patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_DOB.getValue() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
//            patients_mobileNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!patients_mobileNumber.getText().matches("-?([0-9][0-9]*)?") || patients_mobileNumber.getText().length() > 10) {
                // Show an error message or handle invalid input
                alert.errorMessage("Phone Number input error, just number and <10 character");

            } else {
                if (!patients_EmercencyNumber.getText().matches("-?([0-9][0-9]*)?") || patients_EmercencyNumber.getText().length() > 10) {
                    // Show an error message or handle invalid input
                    alert.errorMessage("Emergency Number input error, just number and <10 character");

                } else {
                    LocalDate dob = patients_DOB.getValue();
                    LocalDate currentDate = LocalDate.now();
                    if (dob.isAfter(currentDate)) {
                        alert.errorMessage("DOB cannot be after the current date");
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
//                    patients_PA_password.setText(patients_password.getText());
                                patients_PA_dateCreated.setText(sqlDate.toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                                // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
                                patients_PI_patientName.setText(patients_patientName.getText());
                                patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
                                patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
                                patients_PI_address.setText(patients_address.getText());
                                patients_PI_DOB.setText(patients_DOB.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                                patients_PI_emergencyNumber.setText(patients_EmercencyNumber.getText());
                                patients_PI_bloodGroup.setText(patients_bloodGroup.getText());
                                patients_PI_CCID.setText(patients_ccid.getText());
                                patients_PI_insuranceNumber.setText(patients_insurance.getText());
                                patients_PI_description.setText(patients_description.getText());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }

            }
//            }
//        );

        }
    }

    public void appointmentConfirmBtn() throws SQLException {
        //        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointment_appointmentID.getText().isEmpty()
                || appointment_patientID.getText().isEmpty()
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_address.getText().isEmpty()) {
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
                || appointment_address.getText().isEmpty()) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = '"
                    + appointment_appointmentID.getText() + "'";
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    alert.errorMessage(appointment_appointmentID.getText() + " was already taken");
                } else {

                    String insertData = "INSERT INTO appointment (appointment_id, patient_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, schedule, payment_status, total_pay, quantity) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
                    
                    LocalDate selectedDate = appointment_schedule.getValue();
                    prepare.setString(11, selectedDate != null ? selectedDate.toString() : null);
                    prepare.setString(12, "Pending");
                    prepare.setDouble(13, 0);
                    prepare.setInt(14, 0);
                    prepare.executeUpdate();

                    appointmentClearBtn();
                    registerAppointmentID();
                    appointments_tableView.refresh();

                    alert.successMessage("Successully added!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ObservableList<AppointmentData> appoinmentListData;

//    public void appointmentShowData() {
//        appoinmentListData = appointmentGetData();
//
//        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
//        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
//        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
//        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
//        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
//
//        appointments_tableView.setItems(appoinmentListData);
//    }
// TO SELECT THE DATA PER ROW IN THE TABLE
    public void patientClearFields() {

        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();

        patients_address.clear();
        patients_EmercencyNumber.clear();
        patients_ccid.clear();
        patients_bloodGroup.clear();
        patients_insurance.clear();
        patients_description.clear();
        patients_DOB.setValue(null);

        patients_PA_patientID.setText("");
//        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");
        patients_PI_CCID.setText("");
        patients_PI_insuranceNumber.setText("");
        patients_PI_bloodGroup.setText("");
        patients_PI_emergencyNumber.setText("");
        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
        patients_PI_description.setText("");
        patients_PI_DOB.setText("");
    }

    public void doctorClearFields() {

        doctor_name.clear();
        doctor_gender.getSelectionModel().clearSelection();
        doctor_mobileNumber.clear();
        doctor_email.clear();
        doctor_address.clear();
        password_password.clear();
        passwordcf_password.clear();
        password_ShowPassword.clear();
        passwordcf_showPassword.clear();
        doctor_specialized.getSelectionModel().clearSelection();
        doctor_DOB.setValue(null);

        doctor_PA_doctorID.setText("");
        doctor_PA_password.setText("");
        doctor_PA_dateCreated.setText("");
        doctor_PI_email.setText("");

        doctor_PI_doctorName.setText("");
        doctor_PI_gender.setText("");
        doctor_PI_mobileNumber.setText("");
        doctor_PI_address.setText("");
//        doctor_PI_description.setText("");
        doctor_PI_DOB.setText("");
    }

    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_gender.setItems(listData);

    }

    public void passwordcf_checkBox() {

        if (passwordcf_checkBox.isSelected()) {
            passwordcf_showPassword.setText(passwordcf_password.getText());
            passwordcf_showPassword.setVisible(true);
            passwordcf_password.setVisible(false);

            password_ShowPassword.setText(password_password.getText());
            password_ShowPassword.setVisible(true);
            password_password.setVisible(false);
        } else {
            passwordcf_password.setText(passwordcf_showPassword.getText());
            passwordcf_showPassword.setVisible(false);
            passwordcf_password.setVisible(true);

            password_password.setText(password_ShowPassword.getText());
            password_ShowPassword.setVisible(false);
            password_password.setVisible(true);
        }

    }

    public void patientAddBtn() {
        if (patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()) {
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
                    String insertData = "INSERT INTO patient (patient_id, password, full_name, mobile_number,gender,patients_EmergencyNumber, patients_ccid, patients_bloodGroup, patients_insurance,date,"
                            + "address,"
                            + "status,date_created,description) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    // Chuyển đổi dateCreated thành java.sql.Date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(patients_PA_dateCreated.getText());
                    java.sql.Date sqlDateCreated = new java.sql.Date(parsedDate.getTime());

                    // Chuyển đổi ngày sinh thành java.sql.Date
                    parsedDate = dateFormat.parse(patients_PI_DOB.getText());
                    java.sql.Date sqlDOB = new java.sql.Date(parsedDate.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, newPatientID);
                    prepare.setString(2, "patient123");
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_mobileNumber.getText());
                    prepare.setString(5, patients_PI_gender.getText());
                    prepare.setString(6, patients_PI_emergencyNumber.getText());
                    prepare.setString(7, patients_PI_CCID.getText());
                    prepare.setString(8, patients_bloodGroup.getText());

                    prepare.setString(9, patients_PI_insuranceNumber.getText());
                    prepare.setDate(10, sqlDOB);

                    prepare.setString(11, patients_PI_address.getText());
                    prepare.setString(12, "Active");

                    prepare.setDate(13, sqlDateCreated);
                    prepare.setString(14, patients_PI_description.getText());

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

    public void doctorAddBtn() {
        if (doctor_PA_dateCreated.getText().isEmpty()
                || doctor_PA_password.getText().isEmpty()
                || doctor_PI_doctorName.getText().isEmpty()
                || doctor_PI_gender.getText().isEmpty()
                || doctor_PI_mobileNumber.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            if (!passwordcf_password.getText().equals(password_password.getText()) || !passwordcf_showPassword.getText().equals(password_ShowPassword.getText())) {
                alert.errorMessage("Confirm Password is not correct");
            } else {

                Database.connectDB();
                try {
                    // Xác định năm hiện tại
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);

                    // Tìm số thứ tự của bản ghi doctor cuối cùng trong cơ sở dữ liệu
                    String lastDoctorIDQuery = "SELECT MAX(CAST(SUBSTRING(doctor_id, 5, LEN(doctor_id)) AS INT)) AS last_doctor_id FROM doctor";
                    statement = connect.createStatement();
                    result = statement.executeQuery(lastDoctorIDQuery);
                    int lastDoctorID = 0;
                    if (result.next()) {
                        lastDoctorID = result.getInt("last_doctor_id");
                    }

                    // Tạo doctor_id mới
                    String newDoctorID = String.format("DID-%d", lastDoctorID + 1);

                    // Kiểm tra xem doctor_id mới đã tồn tại chưa
                    String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = ?";
                    prepare = connect.prepareStatement(checkDoctorID);
                    prepare.setString(1, newDoctorID);
                    result = prepare.executeQuery();
                    if (result.next()) {
                        alert.errorMessage(newDoctorID + " is already exist");
                    } else {
                        // Thêm bản ghi doctor vào cơ sở dữ liệu
                        String insertData = "INSERT INTO doctor (doctor_id, password, full_name, mobile_number, gender, date_created, address, specialized,date,status,email) "
                                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                        // Chuyển đổi dateCreated thành java.sql.Date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date parsedDate = dateFormat.parse(doctor_PA_dateCreated.getText());
                        java.sql.Date sqlDateCreated = new java.sql.Date(parsedDate.getTime());

                        // Chuyển đổi ngày sinh thành java.sql.Date
                        parsedDate = dateFormat.parse(doctor_PI_DOB.getText());
                        java.sql.Date sqlDOB = new java.sql.Date(parsedDate.getTime());

                        prepare = connect.prepareStatement(insertData);
                        prepare.setString(1, newDoctorID);
                        prepare.setString(2, doctor_PA_password.getText());
                        prepare.setString(3, doctor_PI_doctorName.getText());
                        prepare.setString(4, doctor_PI_mobileNumber.getText());
                        prepare.setString(5, doctor_PI_gender.getText());
                        prepare.setDate(6, sqlDateCreated);
                        prepare.setString(7, doctor_PI_address.getText());
                        prepare.setString(8, doctor_PI_specialized.getText());
                        prepare.setDate(9, sqlDOB);
                        prepare.setString(10, "Active");
                        prepare.setString(11, doctor_PI_email.getText());

                        System.out.println("newDoctorID: " + newDoctorID);
                        System.out.println("doctor_PA_password: " + doctor_PA_password.getText());
                        System.out.println("doctor_PI_doctorName: " + doctor_PI_doctorName.getText());
                        System.out.println("doctor_PI_mobileNumber: " + doctor_PI_mobileNumber.getText());
                        System.out.println("doctor_PI_gender: " + doctor_PI_gender.getText());
                        System.out.println("sqlDate: " + sqlDateCreated);
                        System.out.println("doctor_PI_address: " + doctor_PI_address.getText());
                        System.out.println("doctor_PI_specialized: " + doctor_PI_specialized.getText());
                        System.out.println("doctor_PI_DOB: " + doctor_PI_DOB.getText());
                        System.out.println("Status: Active");
                        System.out.println("doctor_PI_email: " + doctor_PI_email.getText());

                        prepare.executeUpdate();

                        alert.successMessage("Doctor added successfully!");

                        // Xóa tất cả các trường và một số nhãn
                        doctorClearFields();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

                    Path copy = Paths.get("src\\Admin_Directory\\"
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
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

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
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

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
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

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
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

            // TO DISPLAY IMMEDIATELY THE DATA OF APPOINTMENTS IN TABLEVIEW
            appointmentDisplayData();
            appointmentActionButton();
            current_form.setText("Appointment's Form");
        } else if (event.getSource() == payment_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            payment_form.setVisible(true);
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);

            paymentDisplayData();

            current_form.setText("Bill Form");
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            servicesPane.setVisible(false);

            profile_form.setVisible(true);
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(false);

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
            doctors_addForm.setVisible(false);
            servicesPane.setVisible(false);

//            profileStatusList();
//            profileDisplayInfo();
//            profileDisplayImages();
            current_form.setText("Add New Patient Form");
        } else if (event.getSource() == addNewDoctor_btn) {

            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            appointments_addForm.setVisible(false);
            doctors_addForm.setVisible(true);
            servicesPane.setVisible(false);

//            profileStatusList();
//            profileDisplayInfo();
//            profileDisplayImages();
            current_form.setText("Add Doctor Form");
        } else if (event.getSource() == addNewAppointment_btn) {

            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            doctors_addForm.setVisible(false);
            appointments_addForm.setVisible(true);

            registerAppointmentID();
//            profileStatusList();
//            profileDisplayInfo();
//            profileDisplayImages();
            current_form.setText("Add Appointment Form");
        } else if (event.getSource() == services_btn) {

            dashboard_form.setVisible(false);
            doctors_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            payment_form.setVisible(false);
            profile_form.setVisible(false);
            patients_addForm.setVisible(false);
            doctors_addForm.setVisible(false);
            servicesPane.setVisible(true);

            getServiceData();
            servicesTable.refresh();
            current_form.setText("Services Form");
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
        appointment_schedule.setValue(null);

        appontments_PA_patientID.setText("");
        appointments_patients_PA_name.setText("");
        appointments_PI_mobileNumber.setText("");
        appointments_PI_diagnosis.setText("");
        appointments_PI_treatment.setText("");
        appointments_PI_address.setText("");
        appointments_patients_PI_gender.setText("");

    }

    private String appointmentID;

    public void registerAppointmentID() {
        String sql = "SELECT MAX(CAST(SUBSTRING(appointment_id, 5, LEN(appointment_id) - 4) AS INT)) AS max_id FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                int maxId = result.getInt("max_id");
                tempAppID = maxId + 1;
            }
            appointmentID = "AID-" + tempAppID;

            appointment_appointmentID.setText(appointmentID);
            appointment_appointmentID.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Services section
    public ObservableList<Services> dashboardGetServiceData() {

        ObservableList<Services> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM services ";

        Connection connect = Database.connectDB();

        try {

            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();

            Services sData;

            while (result.next()) {
                sData = new Services(result.getInt("service_id"),
                        result.getString("service_name"),
                        result.getString("description"),
                        result.getDouble("price"));

                listData.add(sData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void getServiceData() {
        serviceList = dashboardGetServiceData(); // Lấy dữ liệu từ cơ sở dữ liệu

        // Chỉ định kiểu dữ liệu cụ thể cho mỗi trường
        columnServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceId"));
        columnServiceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        servicesTable.setItems(serviceList);
    }

    @FXML
    private void addService() {
        String name = service_name.getText();
        String description = service_description.getText();
        String priceText = service_price.getText();

        if (name.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
            // Hiển thị thông báo lỗi nếu có trường nào đó là null hoặc trống
            alert.errorMessage("Please fill in all fields.");
        } else {
            try {
                // Kiểm tra xem priceText có phải là số và lớn hơn 0 không
                double price = Double.parseDouble(priceText);
                if (price <= 0) {
                    alert.errorMessage("Price must be greater than 0.");
                    return;
                }

                // Kết nối đến cơ sở dữ liệu
                Connection connect = Database.connectDB();

                // Kiểm tra sự tồn tại của service_name trong cơ sở dữ liệu
                String checkQuery = "SELECT COUNT(*) FROM services WHERE service_name = ?";
                PreparedStatement checkPrepare = connect.prepareStatement(checkQuery);
                checkPrepare.setString(1, name);
                ResultSet resultSet = checkPrepare.executeQuery();

                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    // Nếu service_name đã tồn tại, hiển thị thông báo lỗi
                    alert.errorMessage("Service name already exists.");
                } else {
                    // Nếu service_name chưa tồn tại, tiếp tục thêm dịch vụ mới vào cơ sở dữ liệu
                    String insertQuery = "INSERT INTO services (service_name, description, price) VALUES (?, ?, ?)";
                    PreparedStatement prepare = connect.prepareStatement(insertQuery);
                    prepare.setString(1, name);
                    prepare.setString(2, description);
                    prepare.setDouble(3, price);

                    // Thực thi truy vấn và kiểm tra kết quả
                    int rowsAffected = prepare.executeUpdate();
                    if (rowsAffected > 0) {
                        // Nếu dữ liệu được chèn thành công, thêm vào danh sách và hiển thị thông báo thành công
                        getServiceData();
                        servicesTable.refresh();
                        alert.successMessage("Service created successfully!");
                    } else {
                        // Nếu không thành công, hiển thị thông báo lỗi
                        alert.errorMessage("Failed to add service to database.");
                    }

                    // Đóng truy vấn chèn
                    prepare.close();
                }

                // Đóng kết nối và truy vấn kiểm tra
                checkPrepare.close();
                connect.close();
            } catch (NumberFormatException e) {
                // Hiển thị thông báo lỗi nếu giá trị giá không hợp lệ
                alert.errorMessage("Invalid price format. Price must be a number.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Hiển thị thông báo lỗi nếu có lỗi xảy ra trong quá trình thêm dữ liệu vào cơ sở dữ liệu
                alert.errorMessage("Error adding service to database.");
            }
        }
    }

    @FXML
    private void editService(Services service) {
        Services selectedService = servicesTable.getSelectionModel().getSelectedItem();
        if (selectedService != null) {
            // Lấy các giá trị mới từ các TextField
            String newName = service.getServiceName();
            String newDescription = service.getDescription();
            double newPrice = service.getPrice();
            System.out.println(newPrice);
            // Cập nhật dịch vụ trong cơ sở dữ liệu
            String updateQuery = "UPDATE services SET service_name = ?, description = ?, price = ? WHERE service_id = ?";
            Connection connect = null;
            PreparedStatement prepare = null;

            try {
                connect = Database.connectDB();
                prepare = connect.prepareStatement(updateQuery);

                // Thiết lập giá trị cho preparedStatement
                prepare.setString(1, newName);
                prepare.setString(2, newDescription);
                prepare.setDouble(3, newPrice);
                prepare.setInt(4, selectedService.getServiceId());

                int rowsUpdated = prepare.executeUpdate();

                if (rowsUpdated > 0) {
                    // Cập nhật thông tin trong bảng hiển thị
                    selectedService.setServiceName(newName);
                    selectedService.setDescription(newDescription);
                    selectedService.setPrice(newPrice);
                    servicesTable.refresh();

                    // Hiển thị thông báo thành công
                    alert.successMessage("Update successfull");
                } else {
                    // Hiển thị thông báo lỗi nếu không có hàng nào được cập nhật
                    alert.errorMessage("Update Failed");

                }

            } catch (SQLException e) {
                e.printStackTrace();
                // Hiển thị thông báo lỗi
                alert.errorMessage("Update Failed");

            } finally {
                // Đóng kết nối và preparedStatement
                if (prepare != null) {
                    try {
                        prepare.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @FXML
    private void deleteService(Services service) {
        Services selectedService = servicesTable.getSelectionModel().getSelectedItem();
        if (selectedService != null) {
            // Kết nối đến cơ sở dữ liệu
            Connection connect = null;
            PreparedStatement prepare = null;

            try {
                connect = Database.connectDB();

                // Tạo truy vấn SQL để xóa dữ liệu
                String deleteQuery = "DELETE FROM services WHERE service_id = ?";
                prepare = connect.prepareStatement(deleteQuery);
                prepare.setInt(1, selectedService.getServiceId());

                // Thực thi truy vấn và kiểm tra kết quả
                int rowsAffected = prepare.executeUpdate();
                if (rowsAffected > 0) {
                    // Nếu dữ liệu được xóa thành công, xóa khỏi danh sách và hiển thị thông báo thành công
                    serviceList.remove(selectedService);
                    alert.successMessage("Service deleted successfully!");
                } else {
                    // Nếu không thành công, hiển thị thông báo lỗi
                    alert.errorMessage("Failed to delete service from database.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Hiển thị thông báo lỗi nếu có lỗi xảy ra trong quá trình xóa dữ liệu khỏi cơ sở dữ liệu
                alert.errorMessage("Error deleting service from database.");
            } finally {
                // Đóng kết nối và truy vấn
                if (prepare != null) {
                    try {
                        prepare.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connect != null) {
                    try {
                        connect.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            // Hiển thị thông báo nếu không có dịch vụ nào được chọn
            alert.errorMessage("Please select a service to delete.");
        }
    }

    @FXML
    private void serviceConfirmBtn() {
        if (service_name.getText().isEmpty()
                || service_description.getText().isEmpty()
                || service_price.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            try {
                double price = Double.parseDouble(service_price.getText());
                if (price <= 0) {
                    alert.errorMessage("Price must be a positive number");
                } else {
                    Connection connect = Database.connectDB();
                    try {
                        // Tìm số thứ tự của bản ghi service cuối cùng trong cơ sở dữ liệu
                        String lastServiceIDQuery = "SELECT MAX(service_id) AS last_service_id FROM services";
                        Statement statement = connect.createStatement();
                        ResultSet result = statement.executeQuery(lastServiceIDQuery);
                        int lastServiceID = 0;
                        if (result.next()) {
                            lastServiceID = result.getInt("last_service_id");
                        }

                        // Tạo service mới
                        int newServiceID = lastServiceID + 1;
                        String name = service_name.getText();
                        String description = service_description.getText();

                        // Kiểm tra xem service_id mới đã tồn tại chưa
                        String checkServiceID = "SELECT * FROM services WHERE service_id = ?";
                        PreparedStatement prepare = connect.prepareStatement(checkServiceID);
                        prepare.setInt(1, newServiceID);
                        result = prepare.executeQuery();
                        if (result.next()) {
                            alert.errorMessage("Service ID " + newServiceID + " already exists");
                        } else {
                            // Thêm service mới vào cơ sở dữ liệu
                            String insertService = "INSERT INTO services (service_id, service_name, description, price) VALUES (?, ?, ?, ?)";
                            prepare = connect.prepareStatement(insertService);
                            prepare.setInt(1, newServiceID);
                            prepare.setString(2, name);
                            prepare.setString(3, description);
                            prepare.setDouble(4, price);
                            prepare.executeUpdate();

                            // Cập nhật bảng hiển thị
                            Services newService = new Services(newServiceID, name, description, price);
                            serviceList.add(newService);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (NumberFormatException e) {
                alert.errorMessage("Invalid price format");
            }
        }
    }

    private void loadSpecializedServices() {
        Connection connection = Database.connectDB();
        ObservableList<String> specializedList = FXCollections.observableArrayList();

        if (connection != null) {
            try {
                String query = "SELECT service_name FROM services";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    specializedList.add(resultSet.getString("service_name"));
                }

                doctor_specialized.setItems(specializedList);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchDoctorByName(KeyEvent keyEvent) {
        String searchKeyword = ((TextField) keyEvent.getSource()).getText().trim();
        System.out.println(searchKeyword);
        ObservableList<DoctorData> searchResultdoctor = searchDoctorByNameInDatabase(searchKeyword);
        // Cập nhật TableView để hiển thị kết quả tìm kiếm
        updateDoctorTableView(searchResultdoctor);
    }

    // Hàm tìm kiếm bệnh nhân trong cơ sở dữ liệu
    private ObservableList<DoctorData> searchDoctorByNameInDatabase(String name) {
        ObservableList<DoctorData> searchResult = FXCollections.observableArrayList();
        String sql = "SELECT * FROM doctor WHERE full_name LIKE ?";

//        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, "%" + name + "%");
            result = prepare.executeQuery();
            while (result.next()) {

                while (result.next()) {
                    DoctorData dData = new DoctorData(result.getInt("id"), result.getString("doctor_id"),
                            result.getString("password"), result.getString("full_name"),
                            result.getString("email"), result.getString("gender"),
                            result.getLong("mobile_number"), result.getString("specialized"),
                            result.getString("address"), result.getString("image"),
                            result.getDate("date"), result.getDate("date_created"), result.getDate("modify_date"),
                            result.getDate("delete_date"), result.getString("status"));

                    searchResult.add(dData);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            closeConnection();
        }

        return searchResult;
    }
    private void updateDoctorTableView(ObservableList<DoctorData> list) {
        doctors_tableView.setItems(list);

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
        doctorGenderList();
//        doctorSpecializedList();
        loadSpecializedServices();
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
        payment_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Kiểm tra nhấp đúp chuột
                PaymentData selectedPayment = payment_tableView.getSelectionModel().getSelectedItem();
                if (selectedPayment != null) {
                    showPaymentDetail(selectedPayment);
                }
            }
        });

        //Services
        getServiceData();
        columnAction.setCellFactory(param -> new TableCell<Services, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                // Define action for Edit button
                editButton.setOnAction(event -> {
                    Services service = getTableView().getItems().get(getIndex());
                    // Handle Edit action here
                    showAlert("Edit", service);
                });

                // Define action for Delete button
                deleteButton.setOnAction(event -> {
                    Services service = getTableView().getItems().get(getIndex());
                    // Handle Delete action here
                    deleteService(service);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(5);
                    setGraphic(buttons);
                }
            }
        });
    }

    // Method to show alert for Edit action
    private void showAlert(String action, Services service) {
        // Tạo một GridPane để chứa các TextField
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Tạo các TextField và gán giá trị từ đối tượng Services
        TextField serviceNameField = new TextField(service.getServiceName());
        TextField descriptionField = new TextField(service.getDescription());
        TextField priceField = new TextField(String.valueOf(service.getPrice()));

        // Thêm các TextField vào GridPane
        gridPane.addRow(0, new Label("Service Name:"), serviceNameField);
        gridPane.addRow(1, new Label("Description:"), descriptionField);
        gridPane.addRow(2, new Label("Price:"), priceField);

        // Tạo một cửa sổ Alert với layout là GridPane
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(action + " Service");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);

        // Thêm một nút "OK" và đặt hành động cho nút này
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        // Xử lý hành động khi nhấn nút "OK"
        Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
        okBtn.setOnAction(event -> {
            // Gọi phương thức editService() với các giá trị mới từ các TextField
            String newName = serviceNameField.getText();
            String newDescription = descriptionField.getText();
            double newPrice = Double.parseDouble(priceField.getText());
            service.setServiceName(newName);
            service.setDescription(newDescription);
            service.setPrice(newPrice);
            editService(service);
        });

        // Hiển thị cửa sổ Alert và chờ đợi người dùng tương tác
        alert.showAndWait();
    }

    // Định nghĩa hàm showPaymentDetail()
    // Định nghĩa hàm showPaymentDetail()
    private void showPaymentDetail(PaymentData paymentData) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Payment Detail");

        // Tạo GridPane để hiển thị thông tin chi tiết
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        int rowIndex = 0;

        addRow(grid, "Patient ID:", String.valueOf(paymentData.getPatientId()), rowIndex++);
        addRow(grid, "Patient Name:", paymentData.getPatientName(), rowIndex++);
        addRow(grid, "Doctor:", paymentData.getDoctor(), rowIndex++);
        addRow(grid, "Total Days:", String.valueOf(paymentData.getTotalDays()), rowIndex++);
        addRow(grid, "Total Price:", String.valueOf(paymentData.getTotalPrice()), rowIndex++);
        addRow(grid, "Services:", paymentData.getServices(), rowIndex++);
        addRow(grid, "Date:", paymentData.getDate().toString(), rowIndex++);
        addRow(grid, "Date Checkout:", paymentData.getDateCheckout().toString(), rowIndex++);

        // Thêm ComboBox để chọn trạng thái
        grid.add(new Label("Status Pay:"), 0, rowIndex);
        ObservableList<String> options = FXCollections.observableArrayList("Pending", "Paid");
        ComboBox<String> statusComboBox = new ComboBox<>(options);
        statusComboBox.setValue(paymentData.getStatusPay());
        grid.add(statusComboBox, 1, rowIndex++);

        addRow(grid, "Date Pay:", paymentData.getDatePay().toString(), rowIndex++);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(grid);

        // Thêm nút "OK" và "Cancel" để đóng Dialog
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Kiểm tra và cập nhật trạng thái nếu có thay đổi
            String newStatus = statusComboBox.getValue();
            if (!newStatus.equals(paymentData.getStatusPay())) {
                paymentData.setStatusPay(newStatus);
                updatePaymentStatus(paymentData);
            }
        }
    }

    // Hàm trợ giúp để thêm dòng
    private void addRow(GridPane grid, String label, String value, int rowIndex) {
        grid.add(new Label(label), 0, rowIndex);
        grid.add(new Label(value), 1, rowIndex);
    }

    // Hàm cập nhật trạng thái thanh toán vào cơ sở dữ liệu
    private void updatePaymentStatus(PaymentData paymentData) {
        String updateQuery = "UPDATE payment SET status_pay = ? WHERE id = ?";
        Connection connect = null;
        PreparedStatement prepare = null;

        try {
            connect = Database.connectDB();
            prepare = connect.prepareStatement(updateQuery);

            // Thiết lập giá trị cho preparedStatement
            prepare.setString(1, paymentData.getStatusPay());
            prepare.setInt(2, paymentData.getId());

            prepare.executeUpdate();

            // Hiển thị thông báo thành công
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Payment status updated successfully!");
            alert.showAndWait();
            payment_tableView.refresh();

        } catch (SQLException e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update payment status.");
            alert.showAndWait();
        } finally {
            // Đóng kết nối và preparedStatement
            if (prepare != null) {
                try {
                    prepare.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void searchPatientByName(KeyEvent keyEvent) {
        String searchKeyword = ((TextField) keyEvent.getSource()).getText().trim();
        System.out.println(searchKeyword);

        ObservableList<PatientsData> searchResult = searchPatientByNameInDatabase(searchKeyword);
        // Cập nhật TableView để hiển thị kết quả tìm kiếm
        updatePatientTableView(searchResult);
    }

    // Hàm tìm kiếm bệnh nhân trong cơ sở dữ liệu
    private ObservableList<PatientsData> searchPatientByNameInDatabase(String name) {
        ObservableList<PatientsData> searchResult = FXCollections.observableArrayList();
        String sql = "SELECT * FROM patient WHERE full_name LIKE ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, "%" + name + "%");
            result = prepare.executeQuery();

            while (result.next()) {
                PatientsData pData = new PatientsData(
                        result.getInt("id"),
                        result.getInt("patient_id"),
                        result.getString("password"),
                        result.getString("full_name"),
                        result.getLong("mobile_number"),
                        result.getString("gender"),
                        result.getString("address"),
                        result.getString("image"),
                        result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getDate("date"),
                        result.getDate("date_modify"),
                        result.getDate("date_delete"),
                        result.getString("status"),
                        result.getLong("patients_EmergencyNumber"),
                        result.getString("patients_ccid"),
                        result.getString("patients_bloodGroup"),
                        result.getString("patients_insurance"),
                        result.getDate("date_created")
                );
                searchResult.add(pData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return searchResult;
    }

    private void updatePatientTableView(ObservableList<PatientsData> list) {
        patients_tableView.setItems(list);

    }

    // Đóng kết nối
    private void closeConnection() {
        try {
            if (result != null) {
                result.close();
            }
            if (prepare != null) {
                prepare.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

// THATS IT FOR THESE VIDEOS, THANKS FOR WATCHING
// SUBSCRIBE OUR CHANNEL FOR THE SUPPORT
// THANK YOU!! :)))))))
