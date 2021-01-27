<p align="center">
    <h4 align="center">MOH Master Facility List REST API</h4>
    <br>
</p>


DEPENDENCIES
-------------------
 * Java 8 or higher -- Refer to the installation documentation for each operating system
 * Apache Maven -- Refer to the installation documentation for each operating system


SETUP AND INSTALLATION STEPS
----------------------------
```
- Clone the project to the installation directory of your choice
- Update the application.properties file located in "src/main/resources" with the following
  * Database details: DB host IP & port,DB name, password & username
  * Embedded Tomcat Port: The API by default listens on port 8080, if there is an application that is currently 
    listening on port 8080, you can change the port number in the properties file by editing the 
    line "server.port"
- Once the configurations have been updated, navigate into the project root folder and run below command to build the project
  *  mvn clean package  
  * If there are errors, the build will not complete otherwise the project will build successfully
  * java -jar target/jar_name-0.0.1-SNAPSHOT.jar
  * The above command should start the application and you should see some start up lines on the screen which should 
     include the line "Tomcat started on port(s): PORT"
- In production/staging, you can setup the API to run as a service. 

   LINUX
   --------------------------------------------
    * For linux, the simplest setup is using Systemd.
    * Open the file mfl.service that comes with the project using your favorite text editor 
    * When the file is open, modify line 7 "ExecStart" and provide the location of the project jar file 
      and also line 6 "User" and provide the operating system user allowed to run the application.
    * Once the lines have been modified copy the file to /etc/systemd/system directory
    * This installation would have been completed. To run the application you can use below command
       - service mfl start
    * Similarly you can use stop, restart, status

   WINDOWS
   ----------------------------------------
     * For Windows you can use the "Windows Service Wrapper".
     * Please refer Winsw documentation for more details: [Winsw documentation](https://repo.jenkins-ci.org/releases/com/sun/winsw/winsw/)
```


ENDPOINTS 
----------------------------
The project contains a Postman json collection.Sample url: http://IP:8082/mfl/v1/Provinces/lusaka

```
End Point                                                          Verb     Description
-----------                                                        ------   ------------
mfl/v1/Province/{name}                                              GET      Get province details by name
mfl/v1/Provinces                                                    GET      Get all provinces         
mfl/v1/Provinces/{province_id}/Districts                            GET      Get all districts in a province using province id
mfl/v1/Provinces/{province_id}/facilities                           GET      Get all facilities in a province using province id
mfl/v1/Districttypes                                                GET      Get all district types
mfl/v1/Districttypes/{districttype_id}/Districts                    GET      Get all districts by district type id
mfl/v1/Districts/{name}                                             GET      Get districts by name
mfl/v1/Districts/{district_id}/Constituencies                       GET      Get all constituencies in a district
mfl/v1/Districts/{district_id}/Wards                                GET      Get all wards in a district
mfl/v1/Districts/{district_id}/facilities                           GET      Get all facilities in a district
mfl/v1/Constituencies/{name}                                        GET      Get constituencies by name
mfl/v1/Constituencies/{constituency_id}/Wards                       GET      Get all wards in a constituency
mfl/v1/Constituencies/{constituency_id}/facilities                  GET      Get all facilities in a constituency
mfl/v1/Wards/{name}                                                 GET      Get wards by name
mfl/v1/Wards/{ward_id}/facilities                                   GET      Get all facilities in a ward
mfl/v1/Facilitytypes                                                GET      Get all facility types
mfl/v1/Facilitytypes/{name}                                         GET      Get facility type by name
mfl/v1/Facilitytypes/{facility_type_id}/facilities                  GET      Get all facilities by type
mfl/v1/Facilities/{name}                                            GET      Get facilities by name
mfl/v1/FacilityOperationStatus                                      GET      Get all facility operation statuses
mfl/v1/FacilityOperationStatus/{name}                               GET      Get all facility operation status by name
mfl/v1/FacilityOperationStatus/{facility_opstatus_id}/Facilities    GET      Get all facilities by operation status
mfl/v1/FacilityOwnership                                            GET      Get all facility ownerships
mfl/v1/FacilityOwnership/{name}                                     GET      Get all facility ownerships by name
mfl/v1/FacilityOwnership/{facility_ownership_id}/Facilities         GET      Get all facilities by ownership id

```



