USE `aakash_balamurugan_corejava_project` ;


CREATE TABLE IF NOT EXISTS hosts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    host_name VARCHAR(100) NOT NULL ,
    mobile_number BIGINT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%'),
	CONSTRAINT chk_mobile_number CHECK (mobile_number REGEXP '^[0-9]{10,15}$')
);



CREATE TABLE IF NOT EXISTS events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL UNIQUE,
    event_description VARCHAR(1000) NOT NULL,
    event_address VARCHAR(500) NOT NULL,
    img_url VARCHAR(500) NOT NULL ,
    date DATE NOT NULL,
    time TIME NOT NULL,
    price DOUBLE NOT NULL,
    status TINYINT DEFAULT 1, 
    host_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (host_id) REFERENCES hosts(id)
);


CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    mobile_number BIGINT NOT NULL ,
    gender VARCHAR(6) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status TINYINT DEFAULT 1, 
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

-- Default values to the tables



INSERT INTO hosts (host_name, mobile_number, email) VALUES 
('Aakash', 9876543210, 'aakash@gmail.com'),
('Vishlali', 9876543210, 'vishali@gmail.com'),
('Pranaw', 9876543210, 'pranaw@gmail.com'),
('Sandeep', 9876543210, 'sandeep@gmail.com'),
('Yogi', 9876543210, 'yogi@gmail.com')
;


INSERT INTO events  (event_name,event_description,event_address,img_url,date,time,price,host_id) VALUES 
('Mundhanai - Storytelling Special by Srikumar', 'Join us for an enchanting evening of storytelling at "Mundhanai - Storytelling Special" by the eminent storyteller Srikumar brought to you by An Unexplored Mic.
 Unveiling the art of captivating narratives this event promises to transport you to a world of imagination and emotions leaving you spellbound.',
'The ARTery 12 Hanumantha Rd Balaji Nagar Royapettah Chennai Tamil Nadu 600014 India','https://cdn2.allevents.in/thumbs/thumb64f12ba79a90c.jpg', '2023-9-10','15:00'
,150.00,2),
('Virtual Open Mic-Singer-Songwriters',  'Happening Every 3rd Tuesday of Each Month! Come and Showcase your talents. If there is a theme it will be announced before the event.',
'274 ,M.G.R main road, perugudi , chennai ','https://cdn-az.allevents.in/events5/banners/ce49271cf2730c726d2f8400fef106fb95ae88d7cb67887d7dc2b2755c0c3558-rimg-w1080-h1080-gmir.jpg?v=1692414578', '2023-9-20','17:00'
,200.00,1),
('Bettet me day three', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above','https://iili.io/HNOIrnj.jpg', '2023-11-20','12:00'
,280.00,4),
('Poems & Stories - A House of T themed Open Mic', 'As a new month arrives so does your favorite House of Ts OPEN MIC. But this time we are adding a little twist to it.Wondering what the twist is all about?
 This time its a themed OPEN MIC! Are you curious about what the theme is?
The theme is EROTICA.',
'House of T, Mowbrays Road, CIT Colony, Mylapore, Chennai, Tamil Nadu 600018, India, Chennai, India','https://cdn2.allevents.in/thumbs/thumb63428a62596c8.jpg', '2023-9-5','12:00'
,280.00,4)
;

INSERT INTO users (username, mobile_number, email, password, gender) VALUES 
('Aakash', 9876543210, 'aakash@gmail.com', 'Aakash@123','Male'),
('gokul' , 9876543210 , 'gokul@gmail.com', 'Gokul@123' , 'Male'),
('Isac' , 9876543210 , 'Isac@gmail' , 'Isac@123' , 'Male' )
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