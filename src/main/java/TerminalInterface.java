import McFayyaz.McZmo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import McFayyaz.Restaurant.Food;
import McFayyaz.Restaurant.Restaurant;
import com.google.gson.*;

public class TerminalInterface {
    public static void main(String[] args) throws IOException {
        McZmo mcZmo = new McZmo();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            try {
                String[] input_parts = parseInput(line);
//                if(input_parts.length != 2)
//                    throw new Exception("Error: Bad Format");
                String command = input_parts[0];
                String jsonData = "";
                if (input_parts.length == 2) {
                    jsonData = input_parts[1];
                }
                runCommand(command, jsonData, mcZmo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static String runCommand(String command, String jsonData, McZmo mcZmo){
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            if (command.equals("addRestaurant")) {
                System.out.println("Adding Restaurant");
                Restaurant restaurant = gson.fromJson(jsonData, Restaurant.class);
                mcZmo.addRestaurant(restaurant);

            } else if (command.equals("addFood")) {
                System.out.println("Adding Food");
                Food food = gson.fromJson(jsonData, Food.class);
                Properties properties = gson.fromJson(jsonData, Properties.class);
                String restaurantName = properties.getProperty("restaurantName");
//                System.out.println(restaurantName);
                mcZmo.addFood(restaurantName, food);
                food.print();

            } else if (command.equals("getRestaurants")) {
                System.out.println("Getting Restaurants");
                mcZmo.printRestaurants();

            } else if (command.equals("getRestaurant")) {
                System.out.println("Getting Restaurant");
                Properties properties = gson.fromJson(jsonData, Properties.class);
                String restaurantName = properties.getProperty("name");
                Restaurant restaurant = mcZmo.getRestaurant(restaurantName);
                String restaurantDetail = gson.toJson(restaurant);
                System.out.println(restaurantDetail);

            } else if (command.equals("getFood")) {
                System.out.println("Getting Food");
                Properties properties = gson.fromJson(jsonData, Properties.class);
                String restaurantName = properties.getProperty("restaurantName");
                String foodName = properties.getProperty("foodName");
                Food food = mcZmo.getFood(restaurantName, foodName);
                String foodDetail = gson.toJson(food);
                System.out.println(foodDetail);

            } else if (command.equals("addToCart")) {
                System.out.println("Adding to Cart");
                Properties properties = gson.fromJson(jsonData, Properties.class);
                String restaurantName = properties.getProperty("restaurantName");
                String foodName = properties.getProperty("foodName");
                mcZmo.addToCart(restaurantName, foodName);

            } else if (command.equals("getCart")) {
                System.out.println("Getting cart");
                System.out.println(mcZmo.getBriefCartJson());

            } else if (command.equals("finalizeOrder")) {
                System.out.println("Finalizing Order");
                // TODO: 2/5/20

            } else if (command.equals("getRecommendedRestaurant")) {
                System.out.println("Getting Recommended Restaurant");
                mcZmo.printRecommendedRestaurants();

            } else {
                throw new Exception("Error: Bad Format");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "OK";
    }

    private static String[] parseInput(String input) {
        return input.split(" ", 2);
    }
}
