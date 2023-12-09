package com.fssa.betterme.service;

import com.fssa.betterme.dao.AppoitementDao;
import com.fssa.betterme.exception.DAOException;
import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.Appoitement;
import com.fssa.betterme.validation.AppointmentValidator;

import java.util.List;

public class AppoitementService {

    private AppoitementDao appoitementDao = new AppoitementDao();

    public boolean addAppoitement(Appoitement appoitement) throws ValidationException, DAOException {
        AppointmentValidator.validateAppoitement(appoitement);
        return appoitementDao.addAppointment(appoitement);
		
    }

    public List<Appoitement> getAppoitementsByUser(int userId) throws DAOException {
        return appoitementDao.getAppointmentsByUser(userId);
        
    }
    
    public List<Appoitement> getAppoitementsByTrainer(int trainerId) throws DAOException {
        return appoitementDao.getAppointmentsByTrainer(trainerId);
    }

    public boolean deleteAppoitement(int appoitementId) throws DAOException {
       return appoitementDao.deleteAppointment(appoitementId);
    }
}
