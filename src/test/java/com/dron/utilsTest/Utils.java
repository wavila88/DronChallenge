package com.dron.utilsTest;

import com.dron.utils.TripsByDrone;

import java.util.ArrayList;
import java.util.List;

public final class Utils{

  

/**
 * Get expected result of output 
 * @return TripsByDrone
 */
  public static TripsByDrone getTripsByDrone() {
    TripsByDrone valueTest = new TripsByDrone();
    //Trips for dron 1
    List<List<String>> tripsDron1 = new ArrayList<>();
    List<String> trip1Dron1Locations = new ArrayList<>();
    trip1Dron1Locations.add("location 1");
    trip1Dron1Locations.add("location 2");
    trip1Dron1Locations.add("location 3");
    List<String> trip2Dron1Locations = new ArrayList<>();
    trip2Dron1Locations.add("location 6");
    trip2Dron1Locations.add("location 7");
    tripsDron1.add(trip1Dron1Locations);
    tripsDron1.add(trip2Dron1Locations);
    
    //Trips for dron 2
    List<List<String>> tripsDron2 = new ArrayList<>();
    List<String> trip1Dron2Locations = new ArrayList<>();
    trip1Dron2Locations.add("location 4");
    trip1Dron2Locations.add("location 5");
    tripsDron2.add(trip1Dron2Locations);

    valueTest.put("dron 1", tripsDron1);
    valueTest.put("dron 2", tripsDron2);
    return valueTest;
  }
}
