/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.sql.Date;

/**
 *
 * @author WINDOWS 10
 */
public class AppointmentData {

//    private Integer id;
    private Integer appointmentID;
    private Long patientID;
    private String name;
    private String gender;
    private String description;
    private String diagnosis;
    private String treatment;
    private Long mobileNumber;
    private String address;
    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private Integer totalPay;
    private String paymentStatus;
    private Integer quantity;
    private Date schedule;

    public AppointmentData(
//            Integer id,
                           Integer appointmentID, Long patientID, String name, String gender,
            Long mobileNumber, String description, String diagnosis, String treatment, String address,
            Date date, Date dateModify, Date dateDelete, String status, Integer totalPay,
            String paymentStatus, Integer quantity, Date schedule) {
//        this.id = id;
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;
        this.totalPay = totalPay;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
    }

    public AppointmentData(Integer appointmentID, String name, String gender,
            Long mobileNumber, String description, String diagnosis, String treatment, String address,
            Date date, Date dateModify, Date dateDelete, String status, Date schedule) {
        this.patientID = patientID;
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.date = date;
        this.dateModify = dateModify;
        this.dateDelete = dateDelete;
        this.status = status;
        this.schedule = schedule;

    }

    public AppointmentData(Integer appointmentID, String name,
            String description, Date date, String status) {
        this.appointmentID = appointmentID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public AppointmentData(Integer appointmentID, String description,
            String diagnosis, String treatment, Date schedule) {
        this.appointmentID = appointmentID;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.schedule = schedule;
    }

//    public Integer getId() {
//        return id;
//    }

    public Integer getAppointmentID() {
        return appointmentID;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public String getStatus() {
        return status;
    }

    public Date getSchedule() {
        return schedule;
    }

    public Long getPatientID() {
        return patientID;
    }

    public Integer getTotalPay() {
        return totalPay;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
