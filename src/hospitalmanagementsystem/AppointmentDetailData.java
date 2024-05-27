/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author PCvinhvizg
 */
public class AppointmentDetailData {

    private Integer appointmentDetailID;
    private String appointmentID;
    private Integer serviceID;
    private Date date;
    private String paymentStatus;
    private BigDecimal price;
    private String description;
    private String diagnosis;
    private String treatment;
    private Date reExamDate;
    private String doctor;

    public AppointmentDetailData(Integer appointmentDetailID, String appointmentID, Integer serviceID, Date date, String description, String diagnosis, String treatment, Date reExamDate) {
        this.appointmentDetailID = appointmentDetailID;
        this.appointmentID = appointmentID;
        this.serviceID = serviceID;
        this.date = date;
        this.description = description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.reExamDate = reExamDate;
    }

    public AppointmentDetailData(String appointmentID, Date date, String description, Date reExamDate) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.description = description;
        this.reExamDate = reExamDate;
    }

    
    
    public Integer getAppointmentDetailID() {
        return appointmentDetailID;
    }

    public String getAppointmentID() {
        return appointmentID;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public Date getDate() {
        return date;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public BigDecimal getPrice() {
        return price;
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

    public Date getReExamDate() {
        return reExamDate;
    }

    public String getDoctor() {
        return doctor;
    }
    
    
}

