USE aakash_balamurugan_corejava_project;




CREATE TABLE IF NOT EXISTS hosts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    host_name VARCHAR(100) NOT NULL UNIQUE,
    mobile_number BIGINT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT chk_email CHECK (email LIKE '%_@__%.__%'),
     CONSTRAINT chk_mobile_number CHECK (mobile_number REGEXP '^[0-9]{10,15}$')
);



CREATE TABLE events (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL UNIQUE,
    event_description VARCHAR(255) NOT NULL,
    event_address VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    price DOUBLE NOT NULL,
    host_id INT NOT NULL,
    FOREIGN KEY (host_id) REFERENCES hosts(id)
);


CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT gender_chk CHECK (gender IN ('male', 'female', 'others'))
);


CREATE TABLE event_user (
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

INSERT INTO events  (event_name,event_description,event_address,date,time,price,host_id) VALUES 
('Bettet me day one', 'it a valid event to be instesrt with  length of 30 characters',
'274 ,M.G.R main road, perugudi , chennai ', '2023-10-30','15:00'
,150.00,2),
('Bettet me day two',  'it a valid event to be instesrt with  length of 30 characters',
'274 ,M.G.R main road, perugudi , chennai ', '2023-10-21','17:00'
,200.00,1),
('Bettet me day three', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-11-20','12:00'
,280.00,4),
('Bettet me day four',  'it a valid event to be instesrt with  length of 30 characters',
'274 ,M.G.R main road, perugudi , chennai ',  '2023-10-30','08:00'
,150.00,2),
('Bettet me day five',  'it a valid event to be instesrt with  length of 30 characters',
'274 ,M.G.R main road, perugudi , chennai ',  '2023-09-14','11:00'
,200.00,4),
('Bettet me day seven',  'it a valid event to be instesrt with  length of 30 characters',
'274 ,M.G.R main road, perugudi , chennai ',  '2024-01-21','16:00'
,200.00,5);