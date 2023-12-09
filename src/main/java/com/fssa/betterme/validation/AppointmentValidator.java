package com.fssa.betterme.validation;

import com.fssa.betterme.exception.ValidationException;
import com.fssa.betterme.model.Appoitement;

import java.time.LocalDate;


public class AppointmentValidator {

    public static void validateAppoitement(Appoitement appoitement) throws ValidationException {
        if (appoitement == null) {
            throw new ValidationException("Appoitement object is null");
        }

        validateTrainerId(appoitement.getTrainerId());
        validateUserId(appoitement.getUserId());
        validateAppoitementDate(appoitement.getAppointmentDate());
        validateCategory(appoitement.getCategory());
        validateType(appoitement.getType());
   
    }

    public static boolean isValidAppoitement(Appoitement appoitement) {
        try {
            validateAppoitement(appoitement);
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    private static void validateTrainerId(int trainerId) throws ValidationException {
        if (trainerId <= 0) {
            throw new ValidationException("Trainer ID must be greater than 0");
        }
    }

    private static void validateUserId(int userId) throws ValidationException {
        if (userId <= 0) {
            throw new ValidationException("User ID must be greater than 0");
        }
    }

    private static void validateAppoitementDate(LocalDate appointmentDate) throws ValidationException {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateToValidate = appointmentDate;
        if (dateToValidate.isBefore(currentDate) || dateToValidate.isAfter(currentDate.plusWeeks(1))) {
            throw new ValidationException("Appoitement date must be in the future and within one week prior");
        }
    }

    private static void validateCategory(String category) throws ValidationException {
        if (category == null || category.isEmpty()) {
            throw new ValidationException("Category is required");
        }
    }

    private static void validateType(String type) throws ValidationException {
        if (type == null || type.isEmpty()) {
            throw new ValidationException("Type is required");
        }
    }


}
