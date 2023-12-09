package com.fssa.betterme.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.fssa.betterme.exception.BookDAOException;
import com.fssa.betterme.model.Event;
import com.fssa.betterme.util.ConnectionUtil;

public class BookingDao {
	
	
	static final String EVENTNAME_TAB = "event_name";
	static final String EVENTADDR_TAB = "event_address";
	static final String PRICEVALUE_TAB = "price";
	static final String IMG_TAB = "images";
	static final String DES_TAB = "event_description";
	static final String ID_TAB = "id";
	static final String DATE_TAB = "date";
	static final String TIME_TAB = "time";
	static final String STATUS_TAB = "status";
	static final String EVENTABT_TAB = "short_intro";

	public boolean createBooking(int eventId, int userId) throws BookDAOException {
		String query = "INSERT INTO event_user (event_id , user_id) VALUES (? ,?)";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, eventId);
			pst.setInt(2, userId);

			int rowsInserted = pst.executeUpdate();

			if (rowsInserted == 0) {
				throw new BookDAOException("Booking failed");
			} else {
				return rowsInserted == 1;
			}

		} catch (SQLException e) {
			throw new BookDAOException(e.getMessage());
		}

	}

	public boolean bookingExists(int eventId, int userId) throws BookDAOException {

		String query = "SELECT COUNT(*) FROM event_user WHERE event_id = ? AND user_id = ? ";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, eventId);
			pst.setInt(2, userId);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count == 1; // If count ==1 , event exists; otherwise, it doesn't.
				}
			}

			return false;
		} catch (SQLException e) {
			throw new BookDAOException(e.getMessage());
		}
	}

	public boolean deleteBooking(int eventId, int userId) throws BookDAOException {
		
		String query = "UPDATE event_user SET status = 0 WHERE event_id = ? AND user_id = ?";

		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			pst.setInt(1, eventId);
			pst.setInt(2, userId);

			int rowsInserted = pst.executeUpdate();

			if (rowsInserted == 0) {
				throw new BookDAOException("deleted failed");
			} else {
				return rowsInserted == 1;
			}

		} catch (SQLException e) {
			throw new BookDAOException(e.getMessage());
		}

	}
	

public int getBookingCount(int eventId) throws BookDAOException {
    String query = "SELECT COUNT(*) FROM betterme.event_user WHERE event_id = ? AND status = 1";
    int bookingCount = 0;

    try (Connection con = ConnectionUtil.getConnection(); 
         PreparedStatement pst = con.prepareStatement(query)) {
        
        pst.setInt(1, eventId);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                bookingCount = rs.getInt(1);
            }
        }

    } catch (SQLException e) {
        throw new BookDAOException(e.getMessage());
    }

    return bookingCount;
}
	
	public List<Event> getEventForUser( int userId) throws BookDAOException {
		List<Event> events = new ArrayList<>();
		String query = "SELECT events.event_name, events.date, events.time ,events.event_description,events.event_address,events.images ,events.price, events.short_intro ,events.id ,event_user.status FROM events INNER JOIN event_user ON events.id = event_user.event_id WHERE event_user.user_id =?";
		//SELECT event_id FROM event_user WHERE user_id = ?
		try (Connection con = ConnectionUtil.getConnection(); PreparedStatement pst = con.prepareStatement(query)) {

			
			pst.setInt(1, userId);

			ResultSet res = pst.executeQuery();

	
				while(res.next()) {
					Event event = createEvent(res);
					events.add(event);
				}
				
			return events;

		} catch (SQLException e) {
			throw new BookDAOException(e.getMessage());
		}

	}
	
	private static Event createEvent(ResultSet rs) throws SQLException {
		int eventId = rs.getInt(ID_TAB);
		String eventName = rs.getString(EVENTNAME_TAB);
		String eventAddress = rs.getString(EVENTADDR_TAB);
		String eventAbout = rs.getString(EVENTABT_TAB);
		String eventDescription = rs.getString(DES_TAB);
		String Img_url = rs.getString(IMG_TAB);
		Date eventDate = rs.getDate(DATE_TAB);
		Time eventTime = rs.getTime(TIME_TAB);
		double price = rs.getDouble(PRICEVALUE_TAB);
		boolean status = rs.getBoolean("status"); 

		
		return new Event( eventId,  eventName,  eventAbout,  eventDescription,  eventAddress,
				 eventDate.toLocalDate(),  eventTime.toLocalTime(),  price, Img_url ,status, null);
	}
}
