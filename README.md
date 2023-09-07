# sec_c_sec_c_aakash.balamurugan__corejava_project_2
# MILESTONE 1
###  ** EVENT MODULE**
- Event Model
- Event Validator
- Event Dao
- Event ServiceLayer
- Event Test cases

 ###  ** EVENT MODULE FEATURES**
-Add event
-Read all event
-Filter events between date
-Filter events by date
-update event
-Delete Event
# DATABASE_DESIGN
### Events Table Structure

| Field            | Type         | Null | Key | Default           | Extra           |
|------------------|--------------|------|-----|-------------------|-----------------|
| id               | INT          | NO   | PRI |                   | auto_increment  |
| event_name       | VARCHAR(100) | NO   | UNI |                   |                 |
| event_description| VARCHAR(1000)| NO   |     |                   |                 |
| event_address    | VARCHAR(500) | NO   |     |                   |                 |
| img_url          | VARCHAR(500) | NO   |     |                   |                 |
| date             | DATE         | NO   |     |                   |                 |
| time             | TIME         | NO   |     |                   |                 |
| price            | DOUBLE       | NO   |     |                   |                 |
| status           | TINYINT      |      |     | 1                 |                 |
| host_id          | INT          | NO   | MUL |                   |                 |
| created_at       | TIMESTAMP    |      |     | CURRENT_TIMESTAMP |                 |
| modified_at      | TIMESTAMP    |      |     | CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | |
|                  |              |      |     |                   |                 |
| FOREIGN KEY (host_id) REFERENCES hosts(id) | |   |             |                 |






# Host Table

###  ** Host MODULE**
- Host Model
- Host Validator
- Host Dao
- Host ServiceLayer
- Host Test cases

 ###  ** Host MODULE FEATURES**
-Add Host
-Read all Host
-update Host
-Delete Host

# Host Table Structure

| Field            | Type         | Null | Key | Default           | Extra           |
|------------------|--------------|------|-----|-------------------|-----------------|
| id               | INT          | NO   | PRI |                   | auto_increment  |
| host_name        | VARCHAR(100) | NO   |     |                   |                 |
| mobile_number    | BIGINT       | NO   |     |                   |                 |
| email            | VARCHAR(100) | NO   | UNI |                   |                 |
|                  |              |      |     |                   |                 |
|                  |              |      |     |                   |                 |
| CONSTRAINT chk_email          |              |      |     | email LIKE '%_@__%.__%' |  |
| CONSTRAINT chk_mobile_number  |              |      |     | mobile_number REGEXP '^[0-9]{10,15}$' |  |


# User Table - Database Project

This project is a database project that manages user information using the "User" table. It provides a structured and organized way to store and retrieve user data.

## User Table - Table Structure

| Field            | Type         | Null | Key | Default           | Extra           |
|------------------|--------------|------|-----|-------------------|-----------------|
| id               | INT          | NO   | PRI |                   | auto_increment  |
| username         | VARCHAR(50)  | NO   |     |                   |                 |
| mobile_number    | BIGINT       | NO   |     |                   |                 |
| gender           | VARCHAR(6)   | NO   |     |                   |                 |
| email            | VARCHAR(100) |





