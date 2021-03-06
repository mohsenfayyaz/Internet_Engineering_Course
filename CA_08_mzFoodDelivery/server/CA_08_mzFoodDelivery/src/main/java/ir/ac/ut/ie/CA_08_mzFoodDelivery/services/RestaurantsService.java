package ir.ac.ut.ie.CA_08_mzFoodDelivery.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.HTTPRequestHandler.HTTPRequestHandler;

import java.util.List;

public class RestaurantsService {

    private static RestaurantsService instance;


    private RestaurantsService() {
    }

    public static RestaurantsService getInstance() {
        if (instance == null) {
            instance = new RestaurantsService();
        }
        return instance;
    }

    public void importRestaurantsFromWeb() throws Exception {
        String RestaurantsJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/restaurants");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Restaurant> restaurants = gson.fromJson(RestaurantsJsonString, new TypeToken<List<Restaurant>>() {
        }.getType());
        for (Restaurant restaurant : restaurants) {
            for (Food food : restaurant.getMenu()) {
                food.setRestaurantId(restaurant.getId());
            }
        }

        for (Restaurant restaurant : restaurants) {
            try {
                MzRepository.getInstance().insertRestaurant(restaurant);
            } catch (Exception e) {

            }
            for (Food food : restaurant.getMenu()) {
                try {
                    MzRepository.getInstance().insertFood(food);
                } catch (Exception e) {

                }
            }

        }
//        System.out.println("done");
//
//        for (Restaurant restaurant : restaurants) {
////            System.out.println(counter + "----------------");
////            restaurant.print();
//            try {
//                MzFoodDelivery.getInstance().addRestaurant(restaurant);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }


}
