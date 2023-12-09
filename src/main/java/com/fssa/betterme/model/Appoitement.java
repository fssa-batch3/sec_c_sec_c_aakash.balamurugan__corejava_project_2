package com.fssa.betterme.model;

import java.sql.Date;
import java.time.LocalDate;

public class Appoitement {
    private int id;
    private int trainerId;
    private int userId;
    private LocalDate appointmentDate;
    private String timeSlot;
    private String number;
    private String category;
    private String type;

   public Appoitement() {
	   
   }; 
    
    public Appoitement(int trainerId, int userId, LocalDate appointmentDate, String timeSlot, String number,
			String category, String type) {
		
		this.trainerId = trainerId;
		this.userId = userId;
		this.appointmentDate = appointmentDate;
		this.timeSlot = timeSlot;
		this.number = number;
		this.category = category;
		this.type = type;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
