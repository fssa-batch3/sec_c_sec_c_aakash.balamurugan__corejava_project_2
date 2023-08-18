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
-Filter events by date
-update event
-Delete Event
# DATABASE_DESIGN
### EventList Table

| Data Type      | Field            | Default | Nullable | Extras        |
|----------------|------------------|---------|----------|---------------|
| INT            | id               |         | NOT NULL | AUTO_INCREMENT|
| VARCHAR(100)   | event_name       |         | NOT NULL |               |
| VARCHAR(255)   | event_description|         | NOT NULL |               |
| VARCHAR(255)   | event_address    |         | NOT NULL |               |
| DATE           | date             |         | NOT NULL |               |
| TIME           | time             |         | NOT NULL |               |
| DOUBLE         | price            |         | NOT NULL |               |
| INT            | host_id          |         | NOT NULL |               |




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

| Field          | Type        | Null  | Key | Default | Extra          |
|----------------|-------------|-------|-----|---------|----------------|
| id             | int         | NO    | PRI |         | auto_increment|
| host_name      | varchar(100)| NO    | UNI |         |                |
| mobile_number  | bigint      | NO    |     |         |                |
| email          | varchar(100)| NO    | UNI |         |                |

