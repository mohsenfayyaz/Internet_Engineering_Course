package MzFoodDelivery.Delivery;

import MzFoodDelivery.Delivery.Delivery;
import MzFoodDelivery.MzFoodDelivery;
import MzFoodDelivery.User.Cart;
import schedulers.BackgroundJobManager;

import java.time.Duration;
import java.time.LocalTime;

public class Order {


    private static double max_id = 0;

    private double id;
    private Cart cart;
    private Status status;
    private Delivery delivery;
    private LocalTime startingDeliveryTime;


    public Order(Cart cart) {
        this.id = max_id ++;
        this.status = Status.SEARCHING;
        this.cart = cart;
    }


    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        setDeliveringStatus();
        double distance = MzFoodDelivery.getInstance().calcDeliveryDistanceToGo(cart.getRestaurant(), delivery);
        double time = distance / delivery.getVelocity();
        startingDeliveryTime = LocalTime.now().plusSeconds((long) time);
        BackgroundJobManager.waitForArriving((int) time, this);
    }

    public Duration getRemainingArrivingTime() {
        return Duration.between(LocalTime.now(), startingDeliveryTime);
    }

    public LocalTime getStartingDeliveryTime() {
        return startingDeliveryTime;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Cart getCart() {
        return cart;
    }

    public double getId() {
        return id;
    }

    public void setDeliveringStatus() {
        status = Status.DELIVERING;
    }

    public void setDeliveredStatus() {
        status = Status.DELIVERED;
    }

    public void setSearchingStatus() {
        status = Status.SEARCHING;
    }


    public Status getStatus() {
        return status;
    }
}
