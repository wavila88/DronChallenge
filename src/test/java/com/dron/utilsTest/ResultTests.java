package com.dron.utilsTest;

import com.dron.utils.TripMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ResultTests {

  /**
   * Get expected result of output
   * 
   * @return TripsByDrone
   */
  public static HashMap<String, TripMap> getChallengeTripsByDrone() {
    HashMap<String, TripMap> valueTest = new HashMap<>();
    // Trips for dron 1
    TripMap tripsDronA = new TripMap();
    List<String> trip1Dron1Locations = new ArrayList<>();
    trip1Dron1Locations.add("LocationK");
    trip1Dron1Locations.add("LocationN");
    trip1Dron1Locations.add("LocationL");
    trip1Dron1Locations.add("LocationO");
    tripsDronA.put("Trip 1", trip1Dron1Locations);
    // tripsDron1.add(trip2Dron1Locations);

    // Trips for dron 2
    TripMap tripsDronB = new TripMap();
    List<String> trip1Dron2Locations = new ArrayList<>();
    trip1Dron2Locations.add("LocationA");
    trip1Dron2Locations.add("LocationC");
    tripsDronB.put("Trip 1", trip1Dron2Locations);

    List<String> trip2Dron2Locations = new ArrayList<>();
    trip2Dron2Locations.add("LocationF");
    trip2Dron2Locations.add("LocationG");
    tripsDronB.put("Trip 2", trip2Dron2Locations);

    List<String> trip3Dron2Locations = new ArrayList<>();
    trip3Dron2Locations.add("LocationB");
    trip3Dron2Locations.add("LocationE");
    tripsDronB.put("Trip 3", trip3Dron2Locations);

    List<String> trip4Dron2Locations = new ArrayList<>();
    trip4Dron2Locations.add("LocationD");
    trip4Dron2Locations.add("LocationP");
    tripsDronB.put("Trip 4", trip4Dron2Locations);

    List<String> trip5Dron2Locations = new ArrayList<>();
    trip5Dron2Locations.add("LocationH");
    trip5Dron2Locations.add("LocationI");
    trip5Dron2Locations.add("LocationJ");
    trip5Dron2Locations.add("LocationM");
    tripsDronB.put("Trip 5", trip5Dron2Locations);

    valueTest.put("DroneA", tripsDronA);
    valueTest.put("DroneB", tripsDronB);
    return valueTest;
  }

  public static HashMap<String, TripMap> getResponseForTwoDrones() {
    HashMap<String, TripMap> valueTest = new HashMap<>();
    // Trips for dron 1
    TripMap tripsDron1 = new TripMap();
    List<String> trip1Dron1Locations = new ArrayList<>();
    trip1Dron1Locations.add("location 2");
    trip1Dron1Locations.add("location 1");
    tripsDron1.put("Trip 1", trip1Dron1Locations);

    //Trips for dron 2

    TripMap tripsDron2 = new TripMap();
    List<String> trip1Dron2Locations = new ArrayList<>();
    trip1Dron2Locations.add("location 3");
    tripsDron2.put("Trip 1", trip1Dron2Locations);

    valueTest.put("dron1", tripsDron1);
    valueTest.put("dron2", tripsDron2);

    return valueTest;
  }


  public static List<String> getTripsAssert (){
    List<String> response = new ArrayList<>();
    response.add("location 9");
    response.add("location 6");
    return response;
  }
}