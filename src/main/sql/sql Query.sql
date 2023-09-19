USE `aakash_balamurugan_corejava_project` ;


CREATE TABLE IF NOT EXISTS hosts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    host_name VARCHAR(100) NOT NULL ,
    mobile_number BIGINT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%'),
	CONSTRAINT chk_mobile_number CHECK (mobile_number REGEXP '^[0-9]{10,15}$')
);


ALTER TABLE hosts RENAME TO Trainers;
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
  host_id int NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY unique_event (event_name,date),
  KEY host_id (host_id),
  CONSTRAINT events_ibfk_1 FOREIGN KEY (host_id) REFERENCES trainers (id)
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
    CONSTRAINT gender_chk CHECK (gender IN ('male', 'female', 'others')),
    CONSTRAINT mobile_number CHECK (mobile_number REGEXP '^[0-9]{10,15}$')
);




CREATE TABLE IF NOT EXISTS event_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT NOT NULL,
    user_id INT NOT NULL,

    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

ALTER TABLE event_user  ADD status tinyint DEFAULT 1;

CREATE TABLE IF NOT EXISTS recorded_video(
	id INT AUTO_INCREMENT PRIMARY KEY,
    trainner_id INT NOT NULL,
    user_id INT NOT NULL,
    video BLOB NOT NULL,

    FOREIGN KEY (Trainner_id) REFERENCES Trainers(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Default values to the tables



INSERT INTO trainers (host_name, mobile_number, email) VALUES 
('John', 9783473478, 'John@gmail.com'),
('Stefen', 7832543287, 'stefen@gmail.com'),
('Danom', 7879823432, 'damon@gmail.com'),
('Rafeal', 8732342343, 'rafeal@gmail.com'),
('Diana', 9876543210, 'diana@gmail.com')
;


INSERT INTO events  (event_name,short_intro,event_description,event_address,images,date,time,price,host_id) VALUES 
('Mundhanai  Storytelling Special by Srikumar','short intro for the event' ,'Join us for an enchanting evening of storytelling at "Mundhanai - Storytelling Special" by the eminent storyteller Srikumar brought to you by An Unexplored Mic.
 Unveiling the art of captivating narratives this event promises to transport you to a world of imagination and emotions leaving you spellbound.',
'The ARTery 12 Hanumantha Rd Balaji Nagar Royapettah Chennai Tamil Nadu 600014 India','https://cdn2.allevents.in/thumbs/thumb64f12ba79a90c.jpg', '2023-9-10','15:00'
,150.00,2),
('Virtual Open Mic Singer Songwriters', 'short intro for the event' , 'Happening Every 3rd Tuesday of Each Month! Come and Showcase your talents. If there is a theme it will be announced before the event.',
'274 ,M.G.R main road, perugudi , chennai ','https://cdn-az.allevents.in/events5/banners/ce49271cf2730c726d2f8400fef106fb95ae88d7cb67887d7dc2b2755c0c3558-rimg-w1080-h1080-gmir.jpg?v=1692414578', '2023-9-20','17:00'
,200.00,1),
('Poems and Stories  A House of T themed Open Mic','short intro for the event' , 'As a new month arrives so does your favorite House of Ts OPEN MIC. But this time we are adding a little twist to it.Wondering what the twist is all about?
 This time its a themed OPEN MIC! Are you curious about what the theme is?
The theme is EROTICA.',
'House of T, Mowbrays Road, CIT Colony, Mylapore, Chennai, Tamil Nadu 600018, India, Chennai, India','https://cdn2.allevents.in/thumbs/thumb63428a62596c8.jpg', '2023-9-5','12:00'
,280.00,4)

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