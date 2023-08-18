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
### Events Table Structure

| Field            | Type        | Null  | Key | Default | Extra          |
|------------------|-------------|-------|-----|---------|----------------|
| id               | INT         | NO    | PRI |         | auto_increment|
| event_name       | VARCHAR(100)| NO    |     |         |                |
| event_description| VARCHAR(255)| NO    |     |         |                |
| event_address    | VARCHAR(255)| NO    |     |         |                |
| date             | DATE        | NO    |     |         |                |
| time             | TIME        | NO    |     |         |                |
| price            | DOUBLE      | NO    |     |         |                |
| host_id          | INT         | NO    | MUL |         |                |





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

