package com.moh.mfl.controller;

import com.moh.mfl.model.Constituencies;
import com.moh.mfl.model.DistrictTypes;
import com.moh.mfl.model.Districts;
import com.moh.mfl.model.Facilities;
import com.moh.mfl.model.FacilityTypes;
import com.moh.mfl.model.Provinces;
import com.moh.mfl.model.Wards;
import com.moh.mfl.repository.ConstituenciesRepository;
import com.moh.mfl.repository.DistrictTypesRepository;
import com.moh.mfl.repository.DistrictsRepository;
import com.moh.mfl.repository.FacilitiesRepository;
import com.moh.mfl.repository.FacilityTypesRepository;
import com.moh.mfl.repository.ProvincesRepository;
import com.moh.mfl.repository.WardsRepository;
import com.moh.mfl.response.ApiResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private DistrictTypes districtType;
    @Autowired
    ProvincesRepository provincesRepository;
    private Provinces provinces;

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
                List<Facilities> list = facilitiesRepository.findByDistrictId(d.get().getId());
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
                List<Wards> list = wardsRepository.findByDistrictId(d.get().getId());
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
                List<Constituencies> list = constituenciesRepository.findByDistrictId(d.get().getId());
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
     * All district types endpoint
     *
     * @return ResponseEntity
     */
    @GetMapping(value = "/Districttypes", produces = "application/json")
    public ResponseEntity<?> Districttypes() {
        try {
            List<DistrictTypes> list = districtTypesRepository.findAll();
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
            List<DistrictTypes> list = districtTypesRepository.findByName(name);
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
            Optional<DistrictTypes> p = districtTypesRepository.findById(Long.valueOf(districttype_id));
            if (p.isPresent()) {
                List<Districts> list = districtsRepository.findByDistrictTypeId(p.get().getId());
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
                List<Districts> list = districtsRepository.findByProvinceId(p.get().getId());
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
    @GetMapping(value = "/Provinces", produces = "application/json")
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
     * Province by name endpoint
     *
     * @param name
     * @return ResponseEntity
     */
    @GetMapping(value = "/Provinces/{name}", produces = "application/json")
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
}
