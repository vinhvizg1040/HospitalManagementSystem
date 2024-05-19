package hospitalmanagementsystem;
import java.sql.Timestamp;

public class PaymentData {
    private int id;
    private int patientId;
    private String patientName;
    private String doctor;
    private int totalDays;
    private int totalPrice;
    private String services;
    private Timestamp date;
    private Timestamp dateCheckout;
    private String statusPay;
    private Timestamp datePay;

    // Constructor
    public PaymentData(int id, int patientId, String patientName, String doctor, int totalDays, int totalPrice,
                       String services, Timestamp date, Timestamp dateCheckout, String statusPay, Timestamp datePay) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctor = doctor;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
        this.services = services;
        this.date = date;
        this.dateCheckout = dateCheckout;
        this.statusPay = statusPay;
        this.datePay = datePay;
    }

    public PaymentData() {

    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctor() {
        return doctor;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getServices() {
        return services;
    }

    public Timestamp getDate() {
        return date;
    }

    public Timestamp getDateCheckout() {
        return dateCheckout;
    }

    public String getStatusPay() {
        return statusPay;
    }

    public Timestamp getDatePay() {
        return datePay;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setDateCheckout(Timestamp dateCheckout) {
        this.dateCheckout = dateCheckout;
    }

    public void setStatusPay(String statusPay) {
        this.statusPay = statusPay;
    }

    public void setDatePay(Timestamp datePay) {
        this.datePay = datePay;
    }
}
