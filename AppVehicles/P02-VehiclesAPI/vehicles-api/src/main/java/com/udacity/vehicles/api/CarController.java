package com.udacity.vehicles.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    CarController(CarService carService, CarResourceAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
    @ApiOperation(value = "Returns List of Cars")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully retrieved cars")})
    @GetMapping
    Resources<Resource<Car>> list() {
        List<Resource<Car>> resources = carService.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    /**
     * Gets information of a specific car by ID.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
    @ApiOperation(value = "Returns Car details associated with the id")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully retrieved car associated with id")})
    @GetMapping("/{id}")
    Resource<Car> get(@PathVariable Long id) {
        
	Car car = carService.findById(id);
        return assembler.toResource(car);
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @ApiOperation(value = "Saves new car details")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully saved car details")})
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
  
	Car saveNewCar = carService.save(car);
        Resource<Car> resource = assembler.toResource(saveNewCar);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @ApiOperation(value = "Updates car details")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully updated car details")})
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
       
	car.setId(id);
	Car updatedCar = carService.save(car);
        Resource<Car> resource = assembler.toResource(updatedCar);
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @ApiOperation(value = "Delete car details")
    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully deleted cars details")})
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        
	carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
