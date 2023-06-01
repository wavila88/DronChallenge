package com.dron.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * Purpose to interac with user trought console
 */
public class InteractionController {


  static Scanner scanner = new Scanner(System.in);
  /**
	 * Asks the user through the console for information to create drones.
	 *
	 * @param <T>     The type of element to create.
	 * @param creator The function to create the elements.
	 * @return List of elements representing the created drones.
	 */
	public static <T> List<T> createElementList(BiFunction<String, Integer, T> creator) {
		List<T> elements = new ArrayList<>();

		while (true) {
			System.out.println("Please add the element name");
			String elementName = scanner.nextLine();

			System.out.println("Please add the max weight");
			Integer elementMaxWeight;
			try {
				elementMaxWeight = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input for max weight. Please try again.");
				continue;
			}

			T element = creator.apply(elementName, elementMaxWeight);
			elements.add(element);

			System.out.println("Do you want to add a new element? (Y/N)");
			String addElement = scanner.nextLine().toUpperCase();
			while (!addElement.equals("Y") && !addElement.equals("N")) {
				System.out.println("This is not a valid option. Please select again Y/N");
				addElement = scanner.nextLine().toUpperCase();
			}
			if (addElement.equals("N")) {
				break;
			}
		}
		return elements;
	}

  
}
