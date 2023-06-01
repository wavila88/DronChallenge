package com.dron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dron.controller.DronController;
import com.dron.models.DronModel;
import com.dron.models.LocationModel;
import com.dron.utils.TripMap;
import com.dron.utils.TripsByDrone;
import com.dron.utilsTest.Utils;

/**
 * Unit test for simple App.
 */
public class DronTest {



	/**
Evaluate input given by Challenge 
[DroneA], [200], [DroneB], [250], [DroneC], [100]
[LocationA], [200]
[LocationB], [150]
[LocationC], [50]
[LocationD], [150]
[LocationE], [100]
[LocationF], [200]
[LocationG], [50]
[LocationH], [80]
[LocationI], [70]
[LocationJ], [50]
[LocationK], [30]
[LocationL], [20]
[LocationM], [50]
[LocationN], [30]
[LocationO], [20]
[LocationP], [90]
	 */
	@Test
	public void evaluateChallenge() {
		TripsByDrone valueTest = Utils.getTripsByDrone();

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("DroneA", 200),
				new DronModel("DroneB", 250),
				new DronModel("DroneC", 100)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("LocationA", 200),
				new LocationModel("LocationB", 150),
				new LocationModel("LocationC", 50),
				new LocationModel("LocationD", 150),
				new LocationModel("LocationE", 100),
				new LocationModel("LocationF", 200),
				new LocationModel("LocationG", 200),
				new LocationModel("LocationH", 80),
				new LocationModel("LocationI", 70),
				new LocationModel("LocationJ", 50),
				new LocationModel("LocationK", 30),
				new LocationModel("LocationL", 20),
				new LocationModel("LocationM", 50),
				new LocationModel("LocationN", 30),
				new LocationModel("LocationO", 20),
				new LocationModel("LocationP", 90),
			
				new LocationModel("location 7", 10)));
		TripsByDrone trips = DronController.createTripsByDrone(drones, locations);
		// Assert.assertEquals(valueTest, trips);
		System.out.println("DATA");
	}


	/**
	 * Validate trips for two drones
	 */
	@Test
	public void shouldReturnTripsPerDrone() {
		TripsByDrone valueTest = Utils.getTripsByDrone();

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("dron 1", 30),
				new DronModel("dron 2", 20)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 10),
				new LocationModel("location 3", 10),
				new LocationModel("location 4", 10),
				new LocationModel("location 5", 10),
				new LocationModel("location 6", 10),
				new LocationModel("location 7", 10)));
		TripsByDrone trips = DronController.createTripsByDrone(drones, locations);
		Assert.assertEquals(valueTest, trips);
	}

	/**
	 * Validate one trip for tow drones
	 */
	@Test
	public void shouldDeliverOneTripTwoDrones() {
		TripMap valueTest = new TripMap();
		valueTest.put("dron 1", Arrays.asList("location 1", "location 2", "location 3"));
		valueTest.put("dron 2", Arrays.asList("location 4"));

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("dron 1", 30),
				new DronModel("dron 2", 20)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 10),
				new LocationModel("location 3", 10),
				new LocationModel("location 4", 10)));
		var trips = DronController.createDeliversForTrip(drones, locations);
		Assert.assertEquals(valueTest, trips);
	}

	/**
	 * Validate one trip for two drones
	 */
	@Test
	public void shouldDeliverTwoTripsOneDron() {
		TripMap trip1 = new TripMap();
		trip1.put("dron 1", Arrays.asList("location 1", "location 2", "location 3"));
		trip1.put("dron 2", Arrays.asList("location 4", "location 5"));
		TripMap trip2 = new TripMap();
		trip2.put("dron 1", Arrays.asList("location 6", "location 7"));
		HashMap<String, TripMap> valueTest = new HashMap<String, TripMap>();

		valueTest.put("Trip 1", trip1);
		valueTest.put("Trip 2", trip2);

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("dron 1", 30),
				new DronModel("dron 2", 20)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 10),
				new LocationModel("location 3", 10),
				new LocationModel("location 4", 10),
				new LocationModel("location 5", 10),
				new LocationModel("location 6", 10),
				new LocationModel("location 7", 10)));
		var trips = DronController.createTrips(drones, locations);
		Assert.assertEquals(valueTest, trips);
	}
}
