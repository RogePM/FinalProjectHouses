package edu.guilford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.guilford.PropertyLists.PropertyList;


public class PropertyScore {

    public static final double FULL_BATHROOM_POINTS = 3;
    public static final double HALF_BATHROOM_POINTS = 2;
    public static final double ONE_BATHROOM_POINTS = 1;

    private final int desiredBedrooms;
    private final double desiredBathrooms;
    private final boolean isForSale;
    private final int desiredPrice;
    private final int desiredClosets;

    public int getDesiredBedrooms() {
        return desiredBedrooms;
    }

    public double getDesiredBathrooms() {
        return desiredBathrooms;
    }

    public boolean isForSale() {
        return isForSale;
    }

    public int getDesiredPrice() {
        return desiredPrice;
    }

    public int getDesiredClosets() {
        return desiredClosets;
    }

    public PropertyScore(int desiredBedrooms, double desiredBathrooms, boolean isForSale, int desiredPrice,
            int desiredClosets) {
        this.desiredBedrooms = desiredBedrooms;
        this.desiredBathrooms = desiredBathrooms;
        this.isForSale = isForSale;
        this.desiredPrice = desiredPrice;
        this.desiredClosets = desiredClosets;
    }

    public List<Property> rankProperties(List<Property> properties) {
        // Create a list to store the scores of all the properties
        List<PropertyScoreResult> propertyScores = new ArrayList<>();

        // Calculate the score for each property
        for (Property property : properties) {
            double score = 0;
            // Check if the property has the desired number of bedrooms
            if (property.getBedrooms() == desiredBedrooms) {
                score += 1;
            }

            // Check the number of bathrooms and add points accordingly
            if (property.getBathrooms() == desiredBathrooms) {
                score += FULL_BATHROOM_POINTS;
            } else if (property.getBathrooms() == desiredBathrooms - 0.5) {
                score += HALF_BATHROOM_POINTS;
            } else if (property.getBathrooms() == 1) {
                score += ONE_BATHROOM_POINTS;
            }

            // Check if the property is for sale or for rent and add points accordingly
            if (property.isForSale() == isForSale) {
                score += 1;
            }

            // Check the price of the property and add points accordingly
            if (isForSale) {
                if (property.getPrice() <= desiredPrice) {
                    score += 1;
                } else if (property.getPrice() <= desiredPrice * 1.1) {
                    score += 0.5;
                }
            } else {
                if (property.getPrice() <= desiredPrice) {
                    score += 1;
                } else if (property.getPrice() <= desiredPrice * 1.05) {
                    score += 0.5;
                }
            }

            // Check the number of closets and add points accordingly
            if (property.getClosets() == desiredClosets) {
                score += 1;
            } else if (property.getClosets() >= desiredClosets - 1 && property.getClosets() < desiredClosets) {
                score += 0.5;
            }

            // Add the property and its score to the list of property scores
            propertyScores.add(new PropertyScoreResult(property, score));
        }

        // Sort the list of property scores based on the scores
        Collections.sort(propertyScores, new Comparator<PropertyScoreResult>() {
            @Override
            public int compare(PropertyScoreResult o1, PropertyScoreResult o2) {
                return Double.compare(o2.getScore(), o1.getScore());
            }
        });

        // Create a list to store the sorted properties
        List<Property> sortedProperties = new ArrayList<>();

        // Add the properties in the sorted order to the list

        // Add the properties in the sorted order to the list
        for (PropertyScoreResult result : propertyScores) {
            sortedProperties.add(result.getProperty());
        }

        // Return the sorted list of properties
        return sortedProperties;
    }
}
