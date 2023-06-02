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
import com.dron.utilsTest.ResultTests;

/**
 * Unit test for simple App.
 */
public class DronTest {

	/**
	 * Evaluate input given by Challenge
	 * [DroneA], [200], [DroneB], [250], [DroneC], [100]
	 * [LocationA], [200]
	 * [LocationB], [150]
	 * [LocationC], [50]
	 * [LocationD], [150]
	 * [LocationE], [100]
	 * [LocationF], [200]
	 * [LocationG], [50]
	 * [LocationH], [80]
	 * [LocationI], [70]
	 * [LocationJ], [50]
	 * [LocationK], [30]
	 * [LocationL], [20]
	 * [LocationM], [50]
	 * [LocationN], [30]
	 * [LocationO], [20]
	 * [LocationP], [90]
	 */
	@Test
	public void evaluateChallenge() {
		HashMap<String, TripMap> valueTest = ResultTests.getChallengeTripsByDrone();
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
				new LocationModel("LocationG", 50),
				new LocationModel("LocationH", 80),
				new LocationModel("LocationI", 70),
				new LocationModel("LocationJ", 50),
				new LocationModel("LocationK", 30),
				new LocationModel("LocationL", 20),
				new LocationModel("LocationM", 50),
				new LocationModel("LocationN", 30),
				new LocationModel("LocationO", 20),
				new LocationModel("LocationP", 90)));
		var trips = DronController.createDeliversForDron(drones, locations);
		Assert.assertEquals(valueTest, trips);
	}

		/**
	 * Validate trips for two drones
	 */
	@Test
	public void shouldMakeTwoTrips() {
		HashMap<String, TripMap> valueTest = ResultTests.getResponseForTwoDrones();

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("dron1", 200),
				new DronModel("dron2", 100)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 90),
				new LocationModel("location 2", 100),
				new LocationModel("location 3", 50)));
		var trips = DronController.createDeliversForDron(drones, locations);

		Assert.assertEquals(valueTest, trips);
	}

	/**
	 * Validate createDeliversForTrip
	 */
	@Test
	public void hugeTest() {

		List<DronModel> drones = new ArrayList<>(Arrays.asList(
				new DronModel("dron 1", 200),
				new DronModel("dron 2", 190),
				new DronModel("dron 3", 100)));
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 100),
				new LocationModel("location 3", 50),
				new LocationModel("location 5", 60),
				new LocationModel("location 6", 80),
				new LocationModel("location 7", 20),
				new LocationModel("location 8", 10),
				new LocationModel("location 9", 120),
				new LocationModel("location 10", 200),
				new LocationModel("location 11", 130),
				new LocationModel("location 12", 110),
				new LocationModel("location 13", 40),
				new LocationModel("location 14", 30),
				new LocationModel("location 15", 20),
				new LocationModel("location 16", 10)));
		DronController.createDeliversForDron(drones, locations);

	}

	@Test
	public void createTripTest() {
		List<String> valueTest = ResultTests.getTripsAssert();
		var dron = new DronModel("dron1", 200);
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 100),
				new LocationModel("location 3", 50),
				new LocationModel("location 5", 60),
				new LocationModel("location 6", 80),
				new LocationModel("location 7", 20),
				new LocationModel("location 8", 10),
				new LocationModel("location 9", 120),
				new LocationModel("location 10", 20)));
		var result = DronController.createTrips(dron, locations);
		Assert.assertEquals(result, valueTest);
	}

	@Test
	public void selectBestFitTest() {
	 var valueTest = new LocationModel("location 5", 60);
		List<LocationModel> locations = new ArrayList<>(Arrays.asList(
				new LocationModel("location 1", 10),
				new LocationModel("location 2", 100),
				new LocationModel("location 3", 50),
				valueTest,
				new LocationModel("location 6", 80),
				new LocationModel("location 7", 20),
				new LocationModel("location 8", 10),
				new LocationModel("location 9", 120),
				new LocationModel("location 10", 20)));
		LocationModel result = DronController.findBestFitLocation(locations,70);
		Assert.assertEquals(result, valueTest);
	}

}
