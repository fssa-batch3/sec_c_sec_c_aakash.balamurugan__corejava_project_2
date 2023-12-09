package com.fssa.betterme.dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.model.Appoitement;
import com.fssa.betterme.util.ConnectionUtil;

public class AppoitementDao {

    public boolean addAppointment(Appoitement appointment) throws DAOException {
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO appointments (trainer_id, user_id, appointment_date, time_slot, number, category, type) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, appointment.getTrainerId());
            ps.setInt(2, appointment.getUserId());
            ps.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
            ps.setString(4, appointment.getTimeSlot());
            ps.setString(5, appointment.getNumber());
            ps.setString(6, appointment.getCategory());
            ps.setString(7, appointment.getType());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
            	 throw new DAOException("Failed to add appointment");
			} else {
				return rowsInserted == 1;
			}
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    public List<Appoitement> getAppointmentsByUser(int userId) throws DAOException {
        List<Appoitement> appointments = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM appointments WHERE user_id = ?")) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Appoitement appointment = createAppoitement(rs);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to get appointments for user");
        }
        return appointments;
    }
    
    public List<Appoitement> getAppointmentsByTrainer(int trainerId) throws DAOException {
        List<Appoitement> appointments = new ArrayList<>();
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM appointments WHERE trainer_id = ?")) {
            ps.setInt(1, trainerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Appoitement appointment = createAppoitement(rs);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        return appointments;
    }


    public boolean deleteAppointment(int appointmentId) throws DAOException {
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM appointments WHERE id = ?")) {
            ps.setInt(1, appointmentId);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
            	 throw new DAOException("Failed to add appointment");
			} else {
				return rowsInserted == 1;
			}
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
    
    private static Appoitement createAppoitement(ResultSet rs) throws SQLException {
    	Appoitement appointment = new Appoitement();
        appointment.setId(rs.getInt("id"));
        appointment.setTrainerId(rs.getInt("trainer_id"));
        appointment.setUserId(rs.getInt("user_id"));
        appointment.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
        appointment.setTimeSlot(rs.getString("time_slot"));
        appointment.setNumber(rs.getString("number"));
        appointment.setCategory(rs.getString("category"));
        appointment.setType(rs.getString("type"));
		return appointment;
        
    	
    }
}
