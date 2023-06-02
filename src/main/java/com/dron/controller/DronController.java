package com.dron.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dron.models.DronModel;
import com.dron.models.LocationModel;
import com.dron.utils.TripMap;


public class DronController<T> {

  
	public static HashMap<String, TripMap> createDeliversForDron(List<DronModel> drones, List<LocationModel> locations) {
		// order drones by bigger maxWeight
		Collections.sort(drones, Comparator.comparingInt(DronModel::getMaxWeight).reversed());
		//value to return
		HashMap<String, TripMap> tripsByDroneMap = new HashMap<>();

		TripMap tripsByDrone = new TripMap();
		int i = 1;
		int dronToUse = 0;
		while (true) {


			// get bigger drone
		
			// get bigger drone
			DronModel drone = drones.get(dronToUse);
			//create trips for dron selected
			List<String> trips = createTrips(drone, locations);

			//create Hashmap key= Trip Number, Value= Trips
			tripsByDrone.put("Trip "+i, trips);

		  // get weight sum of delivers that were not assigned
			int totalWeightLocations = locations.stream()
			.mapToInt(LocationModel::getPackageWeight)
			.sum();
			i++;
			// Validate if second dron can make a efficient delivery
			if (totalWeightLocations < drone.getMaxWeight() &&
					dronToUse + 1 < drones.size() &&
					totalWeightLocations <= drones.get(dronToUse + 1).getMaxWeight()) {
				//change of dron 
				dronToUse++;
				i = 1;
				//Add all trips for dron
				tripsByDroneMap.put(drone.getDronName(), tripsByDrone);
				//clean tripsByDrone to fill up with other dron
				tripsByDrone = new TripMap();
			}
			//Loop will end until locations is empty
			if (locations.isEmpty()) {
				if(tripsByDrone.size() > 0){
					tripsByDroneMap.put(drone.getDronName(), tripsByDrone);
				}
				break;
			}
		}
		printOutPutMessage(tripsByDroneMap);
		return tripsByDroneMap;
	}

	/**
	 * Set all deliveries for a drone in one trip. and remove this deliveries from locations list
	 * @param drone
	 * @param locations
	 * @return
	 */
	public static List<String> createTrips(DronModel drone, List<LocationModel> locations) {
		int remainingWeight = drone.getMaxWeight();
		List<String> trips = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		while (remainingWeight > 0) {
			LocationModel bestFitLocation = findBestFitLocation(locations, remainingWeight);

			if (bestFitLocation != null) {
				trips.add(bestFitLocation.getLocationName());
				builder.append(bestFitLocation.getLocationName());
				remainingWeight -= bestFitLocation.getPackageWeight();
				//remove locations by reference
				locations.remove(bestFitLocation);
			} else {
				break;
			}
		}

		return trips;
	}

	/**
	 * Look for the best location to fit with remainingWeight
	 * @param locations
	 * @param remainingWeight
	 * @return LocationModel
	 */
	public static LocationModel findBestFitLocation(List<LocationModel> locations, int remainingWeight) {
		LocationModel bestFit = null;
		int weightDifference = Integer.MAX_VALUE;

		for (LocationModel location : locations) {
			int difference = Math.abs(location.getPackageWeight() - remainingWeight);
			if (difference < weightDifference) {
				bestFit = location;
				weightDifference = difference;
			}
		}

		return bestFit != null && bestFit.getPackageWeight() > remainingWeight ? null: bestFit;
	}

/**
 * Print expected output
 * @param tripsByDrone
 */
	private static void printOutPutMessage(HashMap<String, TripMap> tripsByDrone) {
		for (Map.Entry<String, TripMap> entry : tripsByDrone.entrySet()) {
			String droneName = entry.getKey();
			TripMap trips = entry.getValue();

			System.out.println("[" + droneName + "]");

			int tripCount = 1;
			for (Map.Entry<String, List<String>> tripEntry : trips.entrySet()) {
				String tripName = tripEntry.getKey();
				List<String> locations = tripEntry.getValue();

				System.out.println("Trip #" + tripCount);
				for (String location : locations) {
					System.out.print("[" + location + "], ");
				}
				System.out.println();
				tripCount++;
			}

			System.out.println();
		}

	}
}
