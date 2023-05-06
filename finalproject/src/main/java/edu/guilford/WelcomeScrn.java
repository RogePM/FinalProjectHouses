package edu.guilford;

import java.io.File;
import java.util.ArrayList;

import edu.guilford.PropertyLists.PropertyList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class WelcomeScrn extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Welcome Screen");

        // Create buttons for owner and customer login
        Button ownerLoginButton = new Button("Owner Login");
        Button customerLoginButton = new Button("Customer Login");

        // Set event handlers for the buttons
        ownerLoginButton.setOnAction(event -> openOwnerLogin());
        customerLoginButton.setOnAction(event -> openCustomerLogin());

        // Create a vertical layout and add the buttons
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(ownerLoginButton, customerLoginButton);

        // Set the layout as the scene content and with a bige size
        Scene scene = new Scene(layout, 600, 400);
        scene.setFill(Color.BLUE);
        // Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        // we want to make the background color of the scene to be blue
        // scene.setFill(Color.BLUE);
        primaryStage.show();

    }

    private void openOwnerLogin() {
        // Create a new stage for the owner screen
        Stage ownerStage = new Stage();
        ownerStage.setTitle("Owner Login");

        // Create GUI components for the owner screen
        Label titleLabel = new Label("Properties Owned:");
        ListView<String> propertyListView = new ListView<>();
        Button addPropertyButton = new Button("Add Property");

        // Add sample property details to the property list view
        propertyListView.getItems().addAll(
                "Property 1 - For Sale - $250,000",
                "Property 2 - For Rent - $1,200/month",
                "Property 3 - For Sale - $350,000");

        // Create a vertical layout for the owner screen
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, propertyListView, addPropertyButton);

        // Set the layout as the scene content
        Scene scene = new Scene(layout, 400, 300);
        ownerStage.setScene(scene);

        // Show the owner screen
        ownerStage.show();
    }

    private void openCustomerLogin() {
        // Code to open the GUI for customer login
        // Replace with your implementation
        // System.out.println("Customer login screen opened.");
        // list of properties
        Stage customerStage = new Stage();
        customerStage.setTitle("Customer Login");
        Label titleLabel = new Label("Properties Available:");

        // sets number of properties to display
        ArrayList<PropertyList> propertiesList = PropertyList.generateRandomPropertyList(27);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.TOP_CENTER);

        int row = 0; // row and col for gridpane
        int col = 0;
        // int count = 0; // counter for number of properties displayed

        // method to add properties to gridpane
        for (PropertyList property : propertiesList) {
            String propertyString = String.format("%s, %s, %s %s - %s %s", property.getAddress(),
                    property.getCity(), property.getState(), property.getZip(), property.getPrice(),
                    property.getStatus());
            Label propertyLabel = new Label(propertyString);

            // image
            File image = new File(this.getClass().getResource("houses.jpg").getPath());
            ImageView propertyImage = new ImageView(image.getPath());
            propertyImage.setFitWidth(200);
            propertyImage.setFitHeight(200);
            propertyImage.setPreserveRatio(true);

            // vbox for image and label
            VBox propertyBox = new VBox(10);
            propertyBox.getChildren().addAll(propertyImage, propertyLabel);
            // adds vbox to gridpane
            gridPane.add(propertyBox, col, row);

            col++;
            while (col == 3) {
                col = 0;
                row++;
            }
            // add scroller to see all properties
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(gridPane);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            Scene scene = new Scene(scrollPane, 1100, 700);
            customerStage.setScene(scene);
            customerStage.show();
            // eventlistener for property
            propertyLabel.setOnMouseClicked(event -> openPropertyDetails(property));

        }
        // shows gridpane
        Scene scene = new Scene(gridPane, 1100, 700);
        customerStage.setScene(scene);
        customerStage.show();

    }

    private void openPropertyDetails(PropertyList property) {
        Stage propertyStage = new Stage();
        propertyStage.setTitle("Property Details");
        Label titleLabel = new Label("Property Details:");

        // sets number of properties to display
        ArrayList<PropertyList> propertiesList = PropertyList.generateRandomPropertyList(27);

        // method to add properties to gridpane
        String propertyString = String.format("%s, %s, %s %s \n%s \n%s \n%s \n%s\n%s", property.getAddress(),
                property.getCity(), property.getState(), property.getZip(), property.getPrice(),property.getOwner(),
                property.getType() , property.getDuration(),property.getStatus());
                 // NEED TO ADD *property.getBedrooms(), property.getBathrooms(), property.getSquareFootage(), 
                
        Label propertyLabel = new Label(propertyString);
         

        // Create a vertical layout for the owner screen
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, propertyLabel );

        // Set the layout as the scene content
        Scene scene = new Scene(layout, 400, 300);
        propertyStage.setScene(scene);

        // Show the owner screen
        propertyStage.show();


    }
}
