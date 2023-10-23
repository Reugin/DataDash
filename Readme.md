# Java DATA DASH Application

> The Java Data Table Application is a Java Spring Boot project that provides a user-friendly web interface for managing and visualizing tabular data stored in JSON, CSV, and Excel file formats. Users can log in to the application, and upon authentication, they have the option to upload data files in these formats. Once the data is uploaded, it is parsed and displayed in a table format for easy viewing and analysis.

### Setup DB Details in /config.yaml
>1. MongoDB should be up and running in your local and add related config details in mentioned file to run application


```bash
dbHost: localhost
dbPort: 27017
dbUsername: admin
dbPassword: admin
database: admin

```

### Provide Config.yaml path in src/main/java/com/datadash/configuration/Configuration.java


```bash
 	String configPath = "/home/abhishek/workspace/test/DataDash/config.yaml";
```

### Run Application
>1. after setup run the application from with main from DataDashApplication.java 




