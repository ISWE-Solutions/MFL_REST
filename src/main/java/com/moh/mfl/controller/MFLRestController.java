package com.moh.mfl.controller;

import com.moh.mfl.config.SecurityConfig;
import com.moh.mfl.model.ApiUsers;
import com.moh.mfl.model.Constituencies;
import com.moh.mfl.model.Districttypes;
import com.moh.mfl.model.Districts;
import com.moh.mfl.model.Facilities;
import com.moh.mfl.model.Facility;
import com.moh.mfl.model.FacilitySave;
import com.moh.mfl.model.FacilityServices;
import com.moh.mfl.model.FacilityTypes;
import com.moh.mfl.model.Provinces;
import com.moh.mfl.model.ServiceArea;
import com.moh.mfl.model.ServiceScope;
import com.moh.mfl.model.Wards;
import com.moh.mfl.repository.ApiUserRepo;
import com.moh.mfl.repository.ConstituenciesRepository;
import com.moh.mfl.repository.CountFacilitiesByProvinceRepository;
import com.moh.mfl.repository.CountFacilitiesByTypeRepository;
import com.moh.mfl.repository.DistrictTypesRepository;
import com.moh.mfl.repository.DistrictsRepository;
import com.moh.mfl.repository.FacilitiesRepository;
import com.moh.mfl.repository.FacilityRepository;
import com.moh.mfl.repository.FacilitySaveRepository;
import com.moh.mfl.repository.FacilityServicesRepository;
import com.moh.mfl.repository.FacilityTypesRepository;
import com.moh.mfl.repository.OperationStatusRepository;
import com.moh.mfl.repository.OwnershipRepository;
import com.moh.mfl.repository.ProvincesRepository;
import com.moh.mfl.repository.ServiceAreaRepository;
import com.moh.mfl.repository.ServiceScopeRepository;
import com.moh.mfl.repository.WardsRepository;
import com.moh.mfl.request.Request;
import com.moh.mfl.response.ApiResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Francis Chulu
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/mfl/v2")
public class MFLRestController {

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
    private Provinces provinces;
    @Autowired
    ApiUserRepo userRepo;
    private final SecurityConfig config;
    @Autowired
    FacilitySaveRepository facilityRepository;
    FacilitySave facility;
    @Autowired
    ServiceAreaRepository serviceAreaRepository;
    @Autowired
    ServiceScopeRepository serviceScopeRepository;
    @Autowired
    FacilityServicesRepository facilityServicesRepository;
    //FacilityServices facilityServices;

    public MFLRestController() {
        this.config = new SecurityConfig();

        //this.facilityServices = new FacilityServices();
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
        } catch (Exception ex) {
            return new ResponseEntity(new ApiResponse(false, "Internal server error occured. Error is::" + ex.getCause().getMessage(), ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;

    }

    @GetMapping("/encode/{key}")
    public ResponseEntity<String> encodeKey(@PathVariable(value = "key") String key
    ) {
        String response = "";
        if (!key.isEmpty()) {
            response = this.config.encodeKey(key);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}
