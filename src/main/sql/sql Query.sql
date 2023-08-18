USE aakash_balamurugan_corejava_project;





CREATE TABLE IF NOT EXISTS hosts (
    id INT NOT NULL AUTO_INCREMENT,
    host_name VARCHAR(100) NOT NULL UNIQUE,
    mobile_number BIGINT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);



CREATE TABLE events (
    id INT NOT NULL AUTO_INCREMENT,
    event_name VARCHAR(100) NOT NULL,
    event_description VARCHAR(255) NOT NULL,
    event_address VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    price DOUBLE NOT NULL,
    host_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (host_id) REFERENCES hosts(id)
);

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);


CREATE TABLE event_user (
    id INT NOT NULL AUTO_INCREMENT,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
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
('Bettet me day 1', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-10-30','15:00'
,150.00,2),
('Bettet me day 2', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-10-21','17:00'
,200.00,1),
('Bettet me day 3', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-11-20','12:00'
,280.00,4),
('Bettet me day 5', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-10-30','08:00'
,150.00,2),
('Bettet me day 6', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2023-09-14','11:00'
,200.00,4),
('Bettet me day 7', 'it a valid event to be instesrt with  length of 30 characters',
'it the event address with charater length of 30 and above', '2024-01-21','16:00'
,200.00,5);