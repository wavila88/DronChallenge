package com.dron.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.function.BiFunction;

import com.dron.models.DronModel;
import com.dron.models.LocationModel;
import com.dron.utils.TripMap;
import com.dron.utils.TripsByDrone;

public class DronController<T> {
	Map<String, List<String>> droneTrips = new HashMap<>();


	
	/**
	 * will return trips by drone
	 * 
	 * @param drones
	 * @param locations
	 * @return TripsByDrone
	 */
	public static TripsByDrone createTripsByDrone(List<DronModel> drones, List<LocationModel> locations) {

		HashMap<String, TripMap> trips = createTrips(drones, locations);

		TripsByDrone tripsByDrone = new TripsByDrone();

		for (Map.Entry<String, TripMap> entry : trips.entrySet()) {
			String tripName = entry.getKey();
			TripMap tripMap = entry.getValue();

			for (Map.Entry<String, List<String>> tripEntry : tripMap.entrySet()) {
				String droneName = tripEntry.getKey();
				List<String> locationsName = tripEntry.getValue();

				if (tripsByDrone.containsKey(droneName)) {
					tripsByDrone.get(droneName).add(locationsName);
				} else {
					List<List<String>> tripsList = new ArrayList<>();
					tripsList.add(locationsName);
					tripsByDrone.put(droneName, tripsList);
				}
			}
		}
		printOutPutMessage(tripsByDrone);
		return tripsByDrone;
	}

	/**
	 * Get a map of TripMap
	 * 
	 * @param drones
	 * @param locations
	 * @return HashMap<String,TripMap>
	 */
	public static HashMap<String, TripMap> createTrips(List<DronModel> drones, List<LocationModel> locations) {
		// Sort from bigger to lower dron
		// Collections.sort(drones, Comparator.comparingInt(DronModel::getMaxWeight).reversed());
		HashMap<String, TripMap> trips = new HashMap<>();
		var tripNumber = 1;
		while (locations.size() > 0) {
			TripMap tripsByDrone = createDeliversForTrip(drones, locations);
			trips.put("Trip " + tripNumber, tripsByDrone);
			tripNumber++;
		}
		return trips;
	}

	public static TripMap createDeliversForTrip(List<DronModel> drones, List<LocationModel> locations) {
    // Fill up first trip for first drone.
    TripMap tripsByDrone = new TripMap();

    for (int i = 0; i < drones.size(); i++) {
        DronModel drone = drones.get(i);
        List<String> trips = setDeliveryForDrone(drone, locations);
        tripsByDrone.put(drone.getDronName(), trips);

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
		var newWeight = 0;
		List<String> trips = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		while (true) {
			// start with the heaviest deliveries
			var location = getHeaviestLocation(locations);
			// validate if Dron can support new weight, if is ok added to trip,
			if (location != null && (newWeight + location.getPackageWeight()) <= drone.getMaxWeight()) {
				trips.add(location.getLocationName());
				locations.remove(location);
				newWeight = newWeight + location.getPackageWeight();
				builder.append(location.getLocationName());
				// if not more locations finish trips
			} else {
				break;
			}

		}
		return trips;
	}

	private static LocationModel getHeaviestLocation(List<LocationModel> locations) {
		Optional<LocationModel> location = locations.stream().max(
				(loc1, loc2) -> Integer.compare(loc1.getPackageWeight(), loc2.getPackageWeight()));
		if (location.isPresent()) {
			return location.get();
		}
		return null;
	}

	private static void printOutPutMessage(TripsByDrone tripsByDrone) {
		for (Map.Entry<String, List<List<String>>> entry : tripsByDrone.entrySet()) {
			String droneName = entry.getKey();
			List<List<String>> trips = entry.getValue();

			System.out.println("[" + droneName + "]");

			int tripCount = 1;
			for (List<String> trip : trips) {
				System.out.println("Trip #" + tripCount);
				for (String location : trip) {
					System.out.print("[" + location + "], ");
				}
				System.out.println();
				tripCount++;
			}
			System.out.println();
		}

	}
}
