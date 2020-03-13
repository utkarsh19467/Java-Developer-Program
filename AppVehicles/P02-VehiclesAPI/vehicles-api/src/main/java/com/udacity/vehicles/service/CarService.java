package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final PriceClient pClient;
    private final MapsClient mClient;

    public CarService(CarRepository repository, PriceClient pClient, MapsClient mClient) {
        this.pClient = pClient;
        this.mClient = mClient;
        this.repository = repository;
    }

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        List<Car> carList =  repository.findAll();
        for(Car car : carList) {
            car.setPrice(pClient.getPrice(car.getId()));
            car.setLocation(mClient.getAddress(car.getLocation()));
        }
        
        return carList;
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Long id) {
        
	Optional<Car> carOptional = repository.findById(id);
	if(!carOptional.isPresent()) {
	    throw new CarNotFoundException("Car with this id does not exist");
	}
        Car car = carOptional.get();

        String carPrice = pClient.getPrice(id);
        car.setPrice(carPrice);

        Location carLocation = mClient.getAddress(car.getLocation());
        car.setLocation(carLocation);
        
        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the repository
     */
    public Car save(Car car) {
	Car savedCar;
        if (car.getId() != null) {
            savedCar = repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        carToBeUpdated.setCondition(car.getCondition());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        } else {
            
            savedCar = repository.save(car);
        }
        
        if (car.getPrice() != null && !car.getPrice().isEmpty()) {
            pClient.updatePrice(savedCar.getPrice(), savedCar.getId());
        }
        else {
            String price = pClient.getPrice(savedCar.getId());
            savedCar.setPrice(price);
        }
        return savedCar;
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Long id) {
        
	Optional<Car> carOptional = repository.findById(id);
	if(!carOptional.isPresent()) {
	    throw new CarNotFoundException("Car with this id does not exist");
	}

	repository.deleteById(id);
    }
}
