package ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.FoodParty;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Food;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.PartyFood;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.domain.MzFoodDelivery.Restaurant.Restaurant;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.repository.MzRepository;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.CustomPair;
import ir.ac.ut.ie.CA_07_mzFoodDelivery.utils.HTTPRequestHandler.HTTPRequestHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodPartyManager {

//    private List<PartyFood> partyFoods = new ArrayList<>();

    public void addFood(PartyFood partyFood) throws SQLException{
        MzRepository.getInstance().insertFood(partyFood);
    }

    private void deleteOldParty() throws SQLException {
        MzRepository.getInstance().deletePartyFoods();
    }

    public void importFoodPartyFromWeb() throws Exception {
        deleteOldParty();

        String foodPartyJsonString = HTTPRequestHandler.getRequest("http://138.197.181.131:8080/foodparty");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<FoodPartyWebItem> foodPartyWeb = gson.fromJson(foodPartyJsonString, new TypeToken<List<FoodPartyWebItem>>() {
        }.getType());
        for (FoodPartyWebItem item : foodPartyWeb) {
            Restaurant restaurant = new Restaurant(item.id, item.name, "", item.location, new ArrayList<Food>(), item.logo);
            try {
                restaurant = MzRepository.getInstance().findRestaurantById(restaurant.getId());
            } catch (SQLException ex) {
                MzRepository.getInstance().insertRestaurant(restaurant);
                System.out.println("Inserted new restaurant for foodParty");
            }

            PartyFood partyFood = new PartyFood(item.menu.get(0).name, item.menu.get(0).description,
                    item.menu.get(0).popularity, item.menu.get(0).oldPrice, item.menu.get(0).image,
                    item.menu.get(0).price, item.menu.get(0).count, restaurant.getId());

//            partyFood.print();
            try {
                List<String> args = new ArrayList<>();
                args.add(restaurant.getId());
                args.add(partyFood.getName());
                MzRepository.getInstance().deleteFood(new CustomPair(args));
            } catch (SQLException ignored) {
            }
            MzRepository.getInstance().insertFood(partyFood);
        }
    }

    public List<PartyFood> getPartyFoods() throws SQLException{
        return MzRepository.getInstance().getPartyFoods();
    }
}



