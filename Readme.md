# BankDataScraper

This SpringBoot app coverts a Bank statement (pdf) file into Excel and extracts the data.

# Installation
### Prerequisites

Before you begin, ensure you have the following installed:

- Java 17 
- Maven 3.9.9
- Docker

### Steps
Clone the repository


```bash
  git clone https://github.com/NambiarVishnu/bank-data-scraper.git

```
Navigate to the project directory:
```bash
 cd bank-data-scraper

```

Build the project using Maven:

```bash
./mvnw clean install

```
## Technologies Used

- Java 17: The core programming language
- Spring Boot: For building the application
- Aspose Pdf: Converting PDF to Excel
- Apache POI: For handling Excel operations
- Maven: For dependency management and build automation
- Docker : For containerization

## API Reference

####  Upload-Statement-v1

```http
  POST /v1/tx-details/upload-statement
```

| Parameter | Type            | Description    |
|:----------|:----------------|:---------------|
| `file`    | `multipartfile` | **Required**.  |





#### Upload-Statement-v2

```http
  POST /v2/tx-details/upload-statement
```

| Parameter | Type            | Description    |
|:----------|:----------------|:---------------|
| `file`    | `multipartfile` | **Required**.  |
| `header1` | `String`        | **Required**   |
| `header2` | `String`        | **Required**   |

## API RESPONSE
The response will contain the extracted data in JSON format(or Excel file will be saved in the local storage).
