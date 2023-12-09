create database betterme ;
USE `betterme` ;



CREATE TABLE IF NOT EXISTS trainers (
        id INT PRIMARY KEY AUTO_INCREMENT,
    person_name VARCHAR(255),
    image_link VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    login_pass VARCHAR(255),
    occupation VARCHAR(255),
    content TEXT,
    startTime VARCHAR(11),
    endTime VARCHAR(11),
    
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%')
);



CREATE TABLE IF NOT EXISTS events (
  id int AUTO_INCREMENT PRIMARY KEY,
  event_name varchar(255) NOT NULL,
  short_intro text NOT NULL,
  event_description text NOT NULL,
  date date NOT NULL,
  time time NOT NULL,
  event_address varchar(500) NOT NULL,
  images blob NOT NULL,
  price double NOT NULL,
  status tinyint NOT NULL DEFAULT 1,
  trainer_id int NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY unique_event (event_name,date),
  CONSTRAINT events_ibfk_1 FOREIGN KEY (trainer_id) REFERENCES trainers (id)
) ;




CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    mobile_number BIGINT NOT NULL ,
    gender VARCHAR(6) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    CONSTRAINT gender_chk CHECK (gender IN ('male', 'female', 'other')),
    CONSTRAINT mobile_number CHECK (mobile_number REGEXP '^[0-9]{10,15}$')
);





CREATE TABLE IF NOT EXISTS event_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    status tinyint DEFAULT 1,

    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);



CREATE TABLE IF NOT EXISTS recorded_video(
	id INT AUTO_INCREMENT PRIMARY KEY,
    trainer_id INT NOT NULL,
    user_id INT NOT NULL,
    video BLOB NOT NULL,

    FOREIGN KEY (trainer_id) REFERENCES trainers(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);




CREATE TABLE education (
    id INT PRIMARY KEY AUTO_INCREMENT,
    trainer_id INT NOT NULL,
    degree VARCHAR(255) NOT NULL,
    
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
);


CREATE TABLE experience (
    id INT PRIMARY KEY AUTO_INCREMENT,
    trainer_id INT NOT NULL,
    position VARCHAR(255) NOT NULL,

    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
);


CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    trainer_id INT  NOT NULL ,
    user_id INT  NOT NULL,
    appointment_date DATE NOT NULL,
    time_slot VARCHAR(50)  NOT NULL,
    number VARCHAR(20) ,
    category VARCHAR(50) NOT NULL,
    type VARCHAR(20)  NOT NULL, 
	FOREIGN KEY (trainer_id) REFERENCES trainers(id), 
	FOREIGN KEY (user_id) REFERENCES users(id),
   UNIQUE KEY unique_Appoitement (trainer_id,appointment_date,time_slot)
);

DELIMITER &&

CREATE PROCEDURE InsertExpertWithEducationExperience(
    IN p_person_name VARCHAR(255),
    IN p_image_link VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_login_pass VARCHAR(255),
    IN p_occupation VARCHAR(255),
    IN p_content TEXT,
    IN p_startTime VARCHAR(50),
    IN p_endTime VARCHAR(50),
    IN p_education_records JSON, -- JSON array of education records
    IN p_experience_records JSON -- JSON array of experience records
)
BEGIN
    -- Insert expert data
    INSERT INTO trainers (person_name, image_link, email, login_pass, occupation, content, startTime, endTime)
    VALUES (p_person_name, p_image_link, p_email, p_login_pass, p_occupation, p_content,  p_startTime, p_endTime);

    -- Get the ID of the newly inserted expert
    SET @expert_id = LAST_INSERT_ID();

    -- Insert education records
    SET @education_count = JSON_LENGTH(p_education_records);
    SET @i = 0;
    
    WHILE @i < @education_count  DO
        INSERT INTO education (trainer_id, degree)
        VALUES (@expert_id, JSON_UNQUOTE(JSON_EXTRACT(p_education_records, CONCAT('$[', @i, '].education'))));
        SET @i = @i + 1;
    END WHILE;

    -- Insert experience records
    SET @experience_count = JSON_LENGTH(p_experience_records);
    SET @j = 0;

    WHILE @j < @experience_count DO
        INSERT INTO experience (trainer_id, position)
        VALUES (@expert_id, JSON_UNQUOTE(JSON_EXTRACT(p_experience_records, CONCAT('$[', @j, '].experience'))));
        SET @j = @j + 1;
    END WHILE;
    
END &&

DELIMITER ;


DELIMITER &&
CREATE PROCEDURE GetTrainerDetails(
   IN p_trainer_id INT
)
BEGIN
    SELECT
        t.id AS trainer_id,
        t.person_name AS trainer_name,
        t.image_link AS image_link,
        t.email AS email,
        t.login_pass AS login_pass,
        t.occupation AS occupation,
        t.content AS content,
        t.startTime AS start_time,
        t.endTime AS end_time,
        (SELECT JSON_ARRAYAGG(
            JSON_OBJECT('degree',  e.degree)
            )
            FROM education AS e
            WHERE e.trainer_id = t.id
        ) AS education,
        (SELECT JSON_ARRAYAGG(
            JSON_OBJECT('position',  x.position)
            )
            FROM experience AS x
            WHERE x.trainer_id = t.id
        ) AS experience
    FROM trainers AS t
    WHERE t.id = p_trainer_id
    GROUP BY t.id;

END &&
DELIMITER ;










call GetTrainerDetails(2);


DELIMITER //
CREATE PROCEDURE UpdateTrainerWithEducationExperience(
    IN p_trainer_id INT,
    IN p_trainer_name VARCHAR(255),
    IN p_image_link VARCHAR(255),
    IN p_email VARCHAR(255),
    IN p_login_pass VARCHAR(255),
    IN p_occupation VARCHAR(255),
    IN p_content TEXT,
    IN p_startTime VARCHAR(50),
    IN p_endTime VARCHAR(50),
    IN p_education_records JSON, -- JSON array of education records
    IN p_experience_records JSON -- JSON array of experience records
)
BEGIN
    -- Update trainer data
    UPDATE trainers
    SET
        TrainerName = p_trainer_name,
        ImageLink = p_image_link,
        Email = p_email,
        LoginPass = p_login_pass,
        Occupation = p_occupation,
        Content = p_content,
        StartTime = p_startTime,
        EndTime = p_endTime
    WHERE TrainerID = p_trainer_id;

    -- Delete existing education and experience records for the trainer
    DELETE FROM education WHERE expert_id = p_trainer_id;
    DELETE FROM experience WHERE expert_id = p_trainer_id;

    -- Insert education records
    SET @education_count = JSON_LENGTH(p_education_records);
    SET @i = 0;
    
    WHILE @i < @education_count DO
        INSERT INTO education (expert_id, degree, institution)
        VALUES (p_trainer_id, JSON_UNQUOTE(JSON_EXTRACT(p_education_records, CONCAT('$[', @i, '].degree'))));
        SET @i = @i + 1;
    END WHILE;

    -- Insert experience records
    SET @experience_count = JSON_LENGTH(p_experience_records);
    SET @j = 0;

    WHILE @j < @experience_count DO
        INSERT INTO experience (expert_id, position, organization)
        VALUES (p_trainer_id, JSON_UNQUOTE(JSON_EXTRACT(p_experience_records, CONCAT('$[', @j, '].position'))));
        SET @j = @j + 1;
    END WHILE;
    
END //
DELIMITER ;




-- Default values to the tables



INSERT INTO trainers (person_name, mobile_number, email) VALUES 
('John', 9783473478, 'John@gmail.com'),
('Stefen', 7832543287, 'stefen@gmail.com'),
('Danom', 7879823432, 'damon@gmail.com'),
('Rafeal', 8732342343, 'rafeal@gmail.com'),
('Diana', 9876543210, 'diana@gmail.com')
;


INSERT INTO events  (event_name,short_intro,event_description,event_address,images,date,time,price,trainer_id) VALUES 
('Mundhanai  Storytelling Special by Srikumar','short intro for the event' ,'Join us for an enchanting evening of storytelling at "Mundhanai - Storytelling Special" by the eminent storyteller Srikumar brought to you by An Unexplored Mic.
 Unveiling the art of captivating narratives this event promises to transport you to a world of imagination and emotions leaving you spellbound.',
'The ARTery 12 Hanumantha Rd Balaji Nagar Royapettah Chennai Tamil Nadu 600014 India','https://cdn2.allevents.in/thumbs/thumb64f12ba79a90c.jpg', '2023-9-10','15:00'
,150.00,4),
('Virtual Open Mic Singer Songwriters', 'short intro for the event' , 'Happening Every 3rd Tuesday of Each Month! Come and Showcase your talents. If there is a theme it will be announced before the event.',
'274 ,M.G.R main road, perugudi , chennai ','https://cdn-az.allevents.in/events5/banners/ce49271cf2730c726d2f8400fef106fb95ae88d7cb67887d7dc2b2755c0c3558-rimg-w1080-h1080-gmir.jpg?v=1692414578', '2023-9-20','17:00'
,200.00,1),
('Poems and Stories  A House of T themed Open Mic','short intro for the event' , 'As a new month arrives so does your favorite House of Ts OPEN MIC. But this time we are adding a little twist to it.Wondering what the twist is all about?
 This time its a themed OPEN MIC! Are you curious about what the theme is?
The theme is EROTICA.',
'House of T, Mowbrays Road, CIT Colony, Mylapore, Chennai, Tamil Nadu 600018, India, Chennai, India','https://cdn2.allevents.in/thumbs/thumb63428a62596c8.jpg', '2023-9-5','12:00'
,280.00,3)

;


INSERT INTO users ( username, mobile_number, gender, email, password) VALUES 
('Aakash', 9876543210, 'male' ,'aakash@gmail.com', 'Aakash!123'),
('Vishlali', 9876543210,'female' ,'vishali@gmail.com', 'Vishalil@30events'),
('Pranaw', 9876543210,'Male' ,'pranaw@gmail.com','Pranaw@123'),
('Sandeep', 9876543210, 'Male','sandeep@gmail.com','Sandeep@123'),
('Yogi', 9876543210, 'Female','yogi@gmail.com','Yogi@22')
;

-- call update_status_before_date_expiiires();
-- use betterme;
-- DELIMITER //
-- CREATE TRIGGER update_status_before_date_expiiires
-- BEFORE UPDATE ON events
-- FOR EACH ROW
-- BEGIN
--     IF NEW.date < '2023-12-30' THEN
--         SET NEW.status = 0;
--     END IF;
-- END;
-- //
-- DELIMITER ;