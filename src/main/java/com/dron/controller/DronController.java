package com.dron.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.dron.models.DronModel;
import com.dron.models.LocationModel;
import com.dron.utils.TripMap;


public class DronController<T> {
	Map<String, List<String>> droneTrips = new HashMap<>();

	/**
	 * will return trips by drone
	 * 
	 * @param drones
	 * @param locations
	 * @return TripsByDrone
	 */
	public static HashMap<String, TripMap> createTripsByDrone(List<DronModel> drones, List<LocationModel> locations) {
		//order by heavier weight
		Collections.sort(drones, Comparator.comparingInt(DronModel::getMaxWeight).reversed());
		TripMap trips = createDeliversForTrip(drones, locations);

		HashMap<String, TripMap> tripsByDroneMap = new HashMap<>();
		// iterate to separte trips per Dron
		for (Map.Entry<String, List<String>> entry : trips.entrySet()) {
			String tripKey = entry.getKey();
			String droneName = tripKey.substring(0, tripKey.indexOf(" "));
			String tripName = tripKey.substring(tripKey.indexOf(" ") + 1);

			if (tripsByDroneMap.containsKey(droneName)) {
				TripMap tripMap = tripsByDroneMap.get(droneName);
				tripMap.put(tripName, entry.getValue());
			} else {
				TripMap tripMap = new TripMap();
				tripMap.put(tripName, entry.getValue());
				tripsByDroneMap.put(droneName, tripMap);
			}
		}
		printOutPutMessage(tripsByDroneMap);
		return tripsByDroneMap;
	}

	/**
	 * Get a map of TripMap
	 * 
	 * @param drones
	 * @param locations
	 * @return HashMap<String,TripMap>
	 */
	// public static HashMap<String, TripMap> createTrips(List<DronModel> drones,
	// List<LocationModel> locations) {
	// // Sort from bigger to lower dron
	// // Collections.sort(drones,
	// Comparator.comparingInt(DronModel::getMaxWeight).reversed());
	// HashMap<String, TripMap> trips = new HashMap<>();
	// var tripNumber = 1;
	// while (locations.size() > 0) {
	// TripMap tripsByDrone = createDeliversForTrip(drones, locations);
	// trips.put("Trip " + tripNumber, tripsByDrone);
	// tripNumber++;
	// }
	// return trips;
	// }

	public static TripMap createDeliversForTrip(List<DronModel> drones, List<LocationModel> locations) {
		// Fill up first trip for first drone.
		TripMap tripsByDrone = new TripMap();
		int i = 1;
		int dronToUse = 0;
		while (true) {

			// get bigger drone
			DronModel drone = drones.get(dronToUse);
			List<String> trips = setDeliveryForDrone(drone, locations);
			tripsByDrone.put(drone.getDronName() + " Trip " + i, trips);
			// Validate if second dron can make a efficient delivery
			int totalWeightLocations = locations.stream()
					.mapToInt(LocationModel::getPackageWeight)
					.sum();
			i++;
			if (totalWeightLocations < drone.getMaxWeight() &&
					dronToUse + 1 < drones.size() &&
					totalWeightLocations <= drones.get(dronToUse + 1).getMaxWeight()) {
				dronToUse++;
				i = 1;
			}

			if (locations.isEmpty()) {
				break;
			}

		}

		return tripsByDrone;
	}

	/**
	 * Set all deliveries for a drone in one trip.
	 * 
	 * @param drone
	 * @param locations
	 * @return
	 */
	private static List<String> setDeliveryForDrone(DronModel drone, List<LocationModel> locations) {
		int remainingWeight = drone.getMaxWeight();
		List<String> trips = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		while (remainingWeight > 0) {
			LocationModel bestFitLocation = findBestFitLocation(locations, remainingWeight);

			if (bestFitLocation != null) {
				trips.add(bestFitLocation.getLocationName());
				builder.append(bestFitLocation.getLocationName());
				remainingWeight -= bestFitLocation.getPackageWeight();
				locations.remove(bestFitLocation);
			} else {
				break;
			}
		}

		return trips;
	}

	private static LocationModel findBestFitLocation(List<LocationModel> locations, int remainingWeight) {
		LocationModel bestFit = null;
		int weightDifference = Integer.MAX_VALUE;

		for (LocationModel location : locations) {
			int difference = Math.abs(location.getPackageWeight() - remainingWeight);
			if (difference < weightDifference) {
				bestFit = location;
				weightDifference = difference;
			}
		}

		return bestFit;
	}

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
