package com.ideas2it.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * This class handles Validation.
 */
public class Validation {
    static Scanner scanner = new Scanner(System.in);

    /**
     * This method is used to display the employee.
     *
     * @param string - accept the string value
     */
    public boolean stringValidation(String string) {
        String regex = "^[a-zA-Z]+([ ][a-zA-Z]+)*$";
        return !Pattern.matches(regex, string);
    }

    /**
     * This method is used to validate the name.
     */
    public String nameValidation() {
        String name;
        do {
            System.out.println("Enter valid Name: ");
            name = scanner.nextLine();
        } while (stringValidation(name));
        return name;
    }

    /**
     * This method is used to validate the place.
     */
    public String placeValidation() {
        String place;
        do {
            System.out.println("Enter valid Place: ");
            place = scanner.nextLine();
        } while (stringValidation(place));
        return place;
    }

    /**
     * This method is used to validate the experience.
     *
     * @param experience - accepts experience
     */
    public boolean experienceValidation(int experience) {
        return experience >= 0;
    }

    /**
     * This method is used to validate the Date of Birth.
     *
     * @param dobString - accepts Date of Birth as a string
     */
    public LocalDate dateOfBirthValidation(String dobString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                LocalDate dob = LocalDate.parse(dobString, dateFormatter);
                if (dob.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Date of birth cannot be in the future.");
                }
                return dob;
            } catch (DateTimeParseException | IllegalArgumentException e) {
                System.out.println("Invalid date format or date in the future. Please enter a valid date (DD/MM/YYYY): ");
                dobString = scanner.nextLine();
            }
        }
    }
}