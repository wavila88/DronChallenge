package com.dron;

import java.util.List;

import com.dron.controller.DronController;
import com.dron.controller.InteractionController;
import com.dron.models.DronModel;
import com.dron.models.LocationModel;

/**
 * Main class to provide personal interaction with customer
 *
 */
public class App {
  public static void main(String[] args) {
    // Read and create store data
    System.out.println("_________________________________________________________________________________");
    System.out.println("Let´s add the drones first ");
    List<DronModel> drones = InteractionController
        .createElementList((dronName, maxWeight) -> new DronModel(dronName, maxWeight));
    System.out.println("_________________________________________________________________________________");
    System.out.println("Now let´s add the locations to deliver drones");
    List<LocationModel> locations = InteractionController
        .createElementList((locationName, packageWeight) -> new LocationModel(locationName, packageWeight));
    System.out.println("_________________________________________________________________________________");
    System.out.println("_________________________________________________________________________________");
     DronController.createDeliversForDron(drones, locations);
  }
}
