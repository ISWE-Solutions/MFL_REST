package com.moh.mfl.controller;

import com.moh.mfl.config.SecurityConfig;
import com.moh.mfl.model.ApiUsers;
import com.moh.mfl.model.Constituencies;
import com.moh.mfl.model.Districttypes;
import com.moh.mfl.model.Districts;
import com.moh.mfl.model.Facilities;
import com.moh.mfl.model.FacilityByProvince;
import com.moh.mfl.model.FacilityIdAndName;
import com.moh.mfl.model.FacilitySave;
import com.moh.mfl.model.FacilityServices;
import com.moh.mfl.model.FacilityTypeCounts;
import com.moh.mfl.model.FacilityTypes;
import com.moh.mfl.model.OperationStatus;
import com.moh.mfl.model.Ownership;
import com.moh.mfl.model.ProvinceList;
import com.moh.mfl.model.Provinces;
import com.moh.mfl.model.ServiceScope;
import com.moh.mfl.model.Wards;
import com.moh.mfl.repository.ApiUserRepo;
import com.moh.mfl.repository.ConstituenciesRepository;
import com.moh.mfl.repository.CountFacilitiesByProvinceRepository;
import com.moh.mfl.repository.CountFacilitiesByTypeRepository;
import com.moh.mfl.repository.DistrictTypesRepository;
import com.moh.mfl.repository.DistrictsRepository;
import com.moh.mfl.repository.FacilitiesIdAndNameOnlyRepository;
import com.moh.mfl.repository.FacilitiesRepository;
import com.moh.mfl.repository.FacilitySaveRepository;
import com.moh.mfl.repository.FacilityServicesRepository;
import com.moh.mfl.repository.FacilityTypesRepository;
import com.moh.mfl.repository.OperationStatusRepository;
import com.moh.mfl.repository.OwnershipRepository;
import com.moh.mfl.repository.ProvincesIdAndNameOnlyRepository;
import com.moh.mfl.repository.ProvincesRepository;
import com.moh.mfl.repository.ServiceAreaRepository;
import com.moh.mfl.repository.ServiceScopeRepository;
import com.moh.mfl.repository.WardsRepository;
import com.moh.mfl.request.AuthRequest;
import com.moh.mfl.request.Request;
import com.moh.mfl.response.ApiResponse;
import com.moh.mfl.response.AuthResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Francis Chulu
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/mfl/v1")
public class RestController {

    @Autowired
    FacilitiesRepository facilitiesRepository;
    private Facilities facilities;
    @Autowired
    CountFacilitiesByProvinceRepository countFacilitiesByProvinceRepository;
    @Autowired
    CountFacilitiesByTypeRepository countFacilitiesByTypeRepository;
    @Autowired
    OwnershipRepository ownershipRepository;
    @Autowired
    OperationStatusRepository operationStatusRepository;
    @Autowired
    FacilityTypesRepository facilityTypesRepository;
    private FacilityTypes facilityTypes;
    @Autowired
    WardsRepository wardsRepository;
    private Wards wards;
    @Autowired
    ConstituenciesRepository constituenciesRepository;
    private Constituencies constituency;
    @Autowired
    DistrictsRepository districtsRepository;
    private Districts districts;
    @Autowired
    DistrictTypesRepository districtTypesRepository;
    private Districttypes districtType;
    @Autowired
    ProvincesRepository provincesRepository;
    @Autowired
    ProvincesIdAndNameOnlyRepository provincesIdAndNameOnlyRepository;
    private Provinces provinces;
    @Autowired
    FacilitiesIdAndNameOnlyRepository facilitiesIdAndNameOnlyRepository;
    @Autowired
    FacilitySaveRepository facilityRepository;
    FacilitySave facility;
    @Autowired
    ServiceAreaRepository serviceAreaRepository;
    @Autowired
    ServiceScopeRepository serviceScopeRepository;
    @Autowired
    FacilityServicesRepository facilityServicesRepository;
    @Autowired
    ApiUserRepo userRepo;
    private ResponseEntity resp;
    private final RestTemplate restTemplate;
    @Autowired
    Environment env;

    private final SecurityConfig config;

    public RestController(RestTemplate restTemplateBuilder) {
        this.config = new SecurityConfig();
        this.restTemplate = restTemplateBuilder;
    }

    /**
     * Save facility coordinates - lat/long
     *
     * @param id
     * @param longitude
     * @param latitude
     * @return ResponseEntity
     */
    @PutMapping(value = "/facility/{id}/latlong/{latitude}/{longitude}", produces = "application/json")
    public ResponseEntity<?> Facility(
            @PathVariable Integer id,
            @PathVariable String latitude,
            @PathVariable String longitude) {
        try {
            facility = facilityRepository.findById(id);
            if (facility != null) {
                facility.setLatitude(latitude);
                facility.setLongitude(longitude);
                this.facilityRepository.save(facility);
                return new ResponseEntity(new ApiResponse(true, "Success", "Facility coordinates updated successfully"), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(new ApiResponse(false, "There is no facility with id:" + id, ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = {"/facility/push"}, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> facility(@Valid @RequestBody Request request, HttpServletRequest servletRequest) {
        ResponseEntity resp = null;
        try {
            String authorizationHeader = servletRequest.getHeader("Authorization");
            if (authorizationHeader != null && authorizationHeader.toLowerCase().startsWith("basic")) {
                String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
                byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
                String credentials = new String(credDecoded, StandardCharsets.UTF_8);
                final String[] values = credentials.split(":", 2);
                String username = values[0];
                String password = values[1];
                Optional<ApiUsers> user = this.userRepo.findByUsername(username);
                if (user.isPresent()) {
                    if (user.get().getStatus() == 1) {
                        if (this.config.verifyKey(password + user.get().getAuthKey(), user.get().getPassword())) {
                            districts = districtsRepository.getDistrict(request.getDistrict());
                            if (districts != null) {
                                //Check if facility exists already
                                Optional<FacilitySave> r = facilityRepository.findByFacilityName(request.getFacilityName());
                                if (!r.isPresent()) {
                                    facility = new FacilitySave();
                                    facility.setDistrict(districts.getId());
                                    facility.setFacilityName(request.getFacilityName());
                                    facility.setOwnershipType(String.valueOf(2));
                                    facility.setLocation(request.getFacilityLocation());
                                    facility.setAccesibility(request.getAccessibility());
                                    facility.setOwnership(request.getOwnership());
                                    facility.setOperationalStatus(request.getOperationalStatus());
                                    facility.setMobilityStatus(request.getMobilityStatus());
                                    facility.setHpczCode(request.getHpczCode());
                                    facility.setStatus(1);
                                    facility.setType(request.getFacilityType());
                                    facility.setEmail(request.getEmail());
                                    facility.setMobileNumber(request.getMobileNumber());
                                    facility.setTelephone(request.getTelephone());
                                    facility.setTown(request.getTown());
                                    facility.setStreet(request.getStreet());
                                    facility.setFax(request.getFax());
                                    facility.setPlotNo(request.getPlotNo());
                                    facility.setPhysicalAddress(request.getPhysicalAddress());
                                    facility.setPostalAddress(request.getPostalAddress());
                                    facility.setLatitude(request.getLatitude());
                                    facility.setLongitude(request.getLongitude());
                                    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                                    facility.setDateCreated(date);
                                    FacilitySave result = this.facilityRepository.save(facility);
                                    Integer id = result.getId();
                                    if (id != null && id > 0) {
                                        //Lets get the services parameter to get the shared id
                                        String[] services = request.getServices().split(",");
                                        if (services.length > 0) {
                                            for (String service : services) {
                                                //Check if service area exist
                                                Optional<ServiceScope> serviceScope = serviceScopeRepository.findBySharedId(Integer.parseInt(service));
                                                if (serviceScope.isPresent()) {
                                                    //Check if facility service exist already
                                                    Optional<FacilityServices> fs = facilityServicesRepository.findByFacilityIdAndServiceId(id, serviceScope.get().getId());
                                                    if (!fs.isPresent()) {
                                                        //We save the service now
                                                        FacilityServices facilityServices = new FacilityServices();
                                                        facilityServices.setFacilityId(id);
                                                        facilityServices.setServiceAreaId(serviceScope.get().getCategoryId());
                                                        facilityServices.setServiceId(serviceScope.get().getId());
                                                        facilityServicesRepository.save(facilityServices);
                                                    }
                                                }
                                            }
                                        }

                                        resp = new ResponseEntity(new ApiResponse(true, "success", id), HttpStatus.ACCEPTED);

                                    } else {
                                        resp = new ResponseEntity(new ApiResponse(false, "Facility could not be saved. Please try again!", ""), HttpStatus.INTERNAL_SERVER_ERROR);
                                    }
                                } else {
                                    resp = new ResponseEntity(new ApiResponse(false, "Facility with facility name:" + request.getFacilityName() + " exists already!", ""), HttpStatus.NOT_ACCEPTABLE);
                                }
                            } else {
                                resp = new ResponseEntity(new ApiResponse(false, "District:" + request.getDistrict() + " not found on the MFL!", ""), HttpStatus.NOT_FOUND);
                            }
                        } else {
                            resp = new ResponseEntity(new ApiResponse(false, "Invalid credentials provided!", ""), HttpStatus.UNAUTHORIZED);
                        }
                    } else {
                        resp = new ResponseEntity(new ApiResponse(false, "User is deactivated!", ""), HttpStatus.UNAUTHORIZED);
                    }
                } else {
                    resp = new ResponseEntity(new ApiResponse(false, "Invalid credentials provided!", ""), HttpStatus.UNAUTHORIZED);
                }
            } else {
                resp = new ResponseEntity(new ApiResponse(false, "Invalid credentials provided!", ""), HttpStatus.BAD_REQUEST);

            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }

    /**
     * Operating Facilities by type counts endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/OperatingFacilityType", produces = "application/json")
    public ResponseEntity<?> FacilityByType() {
        try {
            List<FacilityTypeCounts> list = countFacilitiesByTypeRepository.findByFacilityTypeId();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No Facilities by type were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Operating Facilities by province counts endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/OperatingFacilityProvince", produces = "application/json")
    public ResponseEntity<?> FacilityByProvince() {
        try {
            List<FacilityByProvince> list = countFacilitiesByProvinceRepository.findByDistrictId();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No Facilities by type were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get 10 nearest Facilities by longitude and latitude endpoint
     *
     * @param longitude
     * @param latitude
     * @return ResponseEntity
     */
    @GetMapping(value = "/NearestFacilities/{longitude}/{latitude}", produces = "application/json")
    public ResponseEntity<?> Facility(@PathVariable String longitude, @PathVariable String latitude) {
        try {
            List<Facilities> list = facilitiesRepository.findByLongitudeAndLatitude(longitude, latitude);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "There are no Facilities near "
                        + "coordinates(" + longitude + "," + latitude + ")!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facility by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Facilities/{name}", produces = "application/json")
    public ResponseEntity<?> Facility(@PathVariable String name) {
        try {
            List<Facilities> list = facilitiesRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get facility by id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/facility/{id}", produces = "application/json")
    public ResponseEntity<?> FacilityById(
            @PathVariable Integer id) {
        try {
            Facilities facility = facilitiesRepository.findByFacilitiyId(id);
            if (facility != null) {
                return new ResponseEntity(new ApiResponse(true, "Success", facility), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(new ApiResponse(false, "There is no facility with id:" + id, ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all facilities
     *
     * @return
     */
    @GetMapping(value = "/facilities", produces = "application/json")
    public ResponseEntity<?> Facilities() {
        try {
            List<Facilities> list = facilitiesRepository.findAllFacilitieses();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No Facility was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get facility by name and district
     *
     * @param name
     * @param district_id
     * @return
     */
    @GetMapping(value = "/Facilities/{name}/{district_id}", produces = "application/json")
    public ResponseEntity<?> Facility(@PathVariable String name, Long district_id) {
        try {
            List<Facilities> list = facilitiesRepository.findByNameAndDistrictId(name, district_id);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/FacilityByService/{serviceName}", produces = "application/json")
    public ResponseEntity<?> FacilityByService(@PathVariable String serviceName) {
        try {
            List<Facilities> list = facilitiesRepository.findByName(serviceName);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility: " + serviceName + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facilities by ward id endpoint
     *
     * @param ward_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Wards/{ward_id}/facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByWardId(@PathVariable String ward_id) {
        try {
            Optional<Wards> d = wardsRepository.findById(Long.valueOf(ward_id));
            if (d.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByWardId(d.get().getId());
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no facilities in the system for the ward with id:" + ward_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Ward with id:" + ward_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facilities by constituency id endpoint
     *
     * @param constituency_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Constituencies/{constituency_id}/facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByConstituencyId(@PathVariable String constituency_id) {
        try {
            Optional<Constituencies> d = constituenciesRepository.findById(Long.valueOf(constituency_id));
            if (d.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByConstituencyId(d.get().getId());
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no facilities in the system for the constituency with id:" + constituency_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Constituency with id:" + constituency_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facilities by district id endpoint
     *
     * @param district_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districts/{district_id}/facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByDistrictId(@PathVariable String district_id) {
        try {
            Optional<Districts> d = districtsRepository.findById(Long.valueOf(district_id));
            if (d.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByDistrictId(Long.valueOf(d.get().getId()));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no facilities in the system for the distrcit with id:" + district_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "District with id:" + district_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facilities by province id endpoint
     *
     * @param province_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Provinces/{province_id}/facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByProvinceId(@PathVariable String province_id) {
        try {
            Optional<Provinces> province = provincesRepository.findById(Long.valueOf(province_id));
            if (province.isPresent()) {

                List<Facilities> list = facilitiesRepository.findByProvinceId(Long.valueOf(province_id));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no facilities in the system for the province with id:" + province_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Province with id:" + province_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * All facility ownership endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/FacilityOwnership", produces = "application/json")
    public ResponseEntity<?> FacilityOwnership() {
        try {
            List<Ownership> list = ownershipRepository.findAll();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No facility ownerships were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facility ownership by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/FacilityOwnership/{name}", produces = "application/json")
    public ResponseEntity<?> FacilityOwnership(@PathVariable String name) {
        try {
            List<Ownership> list = ownershipRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility ownership name: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Facilities by ownership id endpoint
     *
     * @param facility_ownership_id
     * @return
     */
    @GetMapping(value = "/FacilityOwnership/{facility_ownership_id}/Facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByOwnershipId(@PathVariable String facility_ownership_id) {
        try {
            Optional<Ownership> d = ownershipRepository.findById(Long.valueOf(facility_ownership_id));
            if (d.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByOwnershipId(Long.valueOf(facility_ownership_id));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "No Facilities found for Facility ownership id: " + facility_ownership_id + "!", ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility ownership with id:" + facility_ownership_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * All facility operation status endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/FacilityOperationStatus", produces = "application/json")
    public ResponseEntity<?> FacilityOperationStatus() {
        try {
            List<OperationStatus> list = operationStatusRepository.findAll();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No facility operation statuses were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facility operation status by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/FacilityOperationStatus/{name}", produces = "application/json")
    public ResponseEntity<?> FacilityOperationStatus(@PathVariable String name) {
        try {
            List<OperationStatus> list = operationStatusRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility operation status name: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Facilities by operation status id endpoint
     *
     * @param facility_opstatus_id
     * @return
     */
    @GetMapping(value = "/FacilityOperationStatus/{facility_opstatus_id}/Facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiesByOperationStatusId(@PathVariable String facility_opstatus_id) {
        try {
            Optional<OperationStatus> d = operationStatusRepository.findById(Long.valueOf(facility_opstatus_id));
            if (d.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByOperationStatusId(Long.valueOf(facility_opstatus_id));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "No Facilities found for Facility operation status id: " + facility_opstatus_id + "!", ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility operation status with id:" + facility_opstatus_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * All facility types endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/Facilitytypes", produces = "application/json")
    public ResponseEntity<?> Facilitytypes() {
        try {
            List<FacilityTypes> list = facilityTypesRepository.findAll();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No facility types were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Facility type by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Facilitytypes/{name}", produces = "application/json")
    public ResponseEntity<?> Facilitytype(@PathVariable String name) {
        try {
            List<FacilityTypes> list = facilityTypesRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility type: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Facilities by facility type id endpoint
     *
     * @param facility_type_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Facilitytypes/{facility_type_id}/facilities", produces = "application/json")
    public ResponseEntity<?> FacilitiestByFacilityType(@PathVariable String facility_type_id) {
        try {
            Optional<FacilityTypes> facilitytypes = facilityTypesRepository.findById(Long.valueOf(facility_type_id));
            if (facilitytypes.isPresent()) {
                List<Facilities> list = facilitiesRepository.findByFacilityTypeId(Long.valueOf(facility_type_id));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "No Facilities found for Facility type id: " + facility_type_id + "!", ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Facility type with id:" + facility_type_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Ward by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Wards/{name}", produces = "application/json")
    public ResponseEntity<?> Ward(@PathVariable String name) {
        try {
            List<Wards> list = wardsRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Ward: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Wards by district id endpoint
     *
     * @param district_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districts/{district_id}/Wards", produces = "application/json")
    public ResponseEntity<?> WardsByDistrictId(@PathVariable String district_id) {
        try {
            Optional<Districts> d = districtsRepository.findById(Long.valueOf(district_id));
            if (d.isPresent()) {
                List<Wards> list = wardsRepository.findByDistrictId(Long.valueOf(d.get().getId()));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no wards in the system for the District with id:" + district_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "District with id:" + district_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Wards by constituency id endpoint
     *
     * @param constituency_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Constituencies/{constituency_id}/Wards", produces = "application/json")
    public ResponseEntity<?> WardsByConstituencyId(@PathVariable String constituency_id) {
        try {
            Optional<Constituencies> d = constituenciesRepository.findById(Long.valueOf(constituency_id));
            if (d.isPresent()) {
                List<Wards> list = wardsRepository.findByConstituencyId(d.get().getId());
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no wards in the system for the Constituency with id:" + constituency_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Constituency with id:" + constituency_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Constituency by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Constituencies/{name}", produces = "application/json")
    public ResponseEntity<?> Constituency(@PathVariable String name) {
        try {
            List<Constituencies> list = constituenciesRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Constituency: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Constituencies by district id endpoint
     *
     * @param district_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districts/{district_id}/Constituencies", produces = "application/json")
    public ResponseEntity<?> ConstituenciesByDistrictId(@PathVariable String district_id) {
        try {
            Optional<Districts> d = districtsRepository.findById(Long.valueOf(district_id));
            if (d.isPresent()) {
                List<Constituencies> list = constituenciesRepository.findByDistrictId(Long.valueOf(d.get().getId()));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no constituencies in the system for the District with id:" + district_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "District with id:" + district_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * District by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districts/{name}", produces = "application/json")
    public ResponseEntity<?> District(@PathVariable String name) {
        try {
            List<Districts> list = districtsRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "District: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * District by id endpoint
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districts/{id}", produces = "application/json")
    public ResponseEntity<?> District(@PathVariable Long id) {
        try {
            Optional<Districts> list = districtsRepository.findById(id);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "District by id: " + id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * All district types endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districttypes", produces = "application/json")
    public ResponseEntity<?> Districttypes() {
        try {
            List<Districttypes> list = districtTypesRepository.findAll();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No District types were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * District type by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districttypes/{name}", produces = "application/json")
    public ResponseEntity<?> Districttype(@PathVariable String name) {
        try {
            List<Districttypes> list = districtTypesRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "District type: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Districts by district type id endpoint
     *
     * @param districttype_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districttypes/{districttype_id}/Districts", produces = "application/json")
    public ResponseEntity<?> DistrictsByDistricttypeId(@PathVariable String districttype_id) {
        try {
            Optional<Districttypes> p = districtTypesRepository.findById(Long.valueOf(districttype_id));
            if (p.isPresent()) {
                List<Districts> list = districtsRepository.findByDistrictTypeId(Long.valueOf(p.get().getId()));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no districts in the system for the District type with id:" + districttype_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "District type with id:" + districttype_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Districts by province id endpoint
     *
     * @param province_id
     * @return ResponseEntity
     */
    @GetMapping(value = "/Provinces/{province_id}/Districts", produces = "application/json")
    public ResponseEntity<?> DistrictsByProvinceId(@PathVariable String province_id) {
        try {
            Optional<Provinces> p = provincesRepository.findById(Long.valueOf(province_id));
            if (p.isPresent()) {
                List<Districts> list = districtsRepository.findByProvinceId(Long.valueOf(p.get().getId()));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no districts in the system for the Province with id:" + province_id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Province with id:" + province_id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * All Provinces endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/provinces", produces = "application/json")
    public ResponseEntity<?> Provinces() {
        try {
            List<Provinces> list = provincesRepository.findAll();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No Provinces were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * All provinces id and name only
     *
     * @return
     */
    @GetMapping(value = "/provinces/idAndName", produces = "application/json")
    public ResponseEntity<?> ProvinceIdAndName() {
        try {
            List<ProvinceList> list = provincesIdAndNameOnlyRepository.getIdAndNameOnly();
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "No Provinces were found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Province by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/provinces/{name}", produces = "application/json")
    public ResponseEntity<?> Province(@PathVariable String name) {
        try {
            List<Provinces> list = provincesRepository.findByName(name);
            if (!list.isEmpty()) {
                return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, "Province: " + name + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Facilities by province id endpoint
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping(value = "/district/{id}/facilitiesIdAndName", produces = "application/json")
    public ResponseEntity<?> FacilityIdAndNameOnlyByProvinceId(@PathVariable String id) {
        try {
            Optional<Districts> district = districtsRepository.findById(Long.valueOf(id));
            if (district.isPresent()) {
                List<FacilityIdAndName> list = facilitiesIdAndNameOnlyRepository.findByDistrictId(Long.valueOf(id));
                if (!list.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(true, "Success", list), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, "There are no facilities in the system for the province with id:" + id, ""), HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, "Province with id:" + id + " was not found!", ""), HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Auth endpoint
     *
     * @param request
     * @param servletRequest
     * @return
     */
    @PostMapping(value = {"/authenticate"}, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequest request, HttpServletRequest servletRequest) {
        try {
            resp = processRequest(request);
        } catch (RestClientException ex) {
            resp = new ResponseEntity(new ApiResponse(false, "Server Error occured", ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    /**
     * Process Auth request
     *
     * @param request
     * @return
     */
    private ResponseEntity processRequest(AuthRequest request) {
        try {
            String url = env.getProperty("url");
            //Set the request headers and specify the response converters to be used
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<AuthRequest> entity = new HttpEntity<>(request, headers);
            List<HttpMessageConverter<?>> converters = new ArrayList<>();
            converters.add(new MappingJackson2HttpMessageConverter());
            restTemplate.setMessageConverters(converters);
            //Send the request and save the result in the response class object
            ResponseEntity<AuthResponse> results = restTemplate.exchange(url, HttpMethod.POST, entity, AuthResponse.class);

            if (results.getStatusCodeValue() == 202 || results.getStatusCodeValue() == 200) {
                if (results.getBody().getSuccess().equals("true")) {
                    resp = new ResponseEntity(new ApiResponse(true, "Success", results.getBody().getData()), HttpStatus.OK);
                } else {
                    resp = new ResponseEntity(new ApiResponse(false, "Invalid username/password provided!", ""), HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                resp = new ResponseEntity(new ApiResponse(false, "Invalid username/password provided!", ""), HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (RestClientException ex) {
            resp = new ResponseEntity(new ApiResponse(false, "Server Error occured", ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}
