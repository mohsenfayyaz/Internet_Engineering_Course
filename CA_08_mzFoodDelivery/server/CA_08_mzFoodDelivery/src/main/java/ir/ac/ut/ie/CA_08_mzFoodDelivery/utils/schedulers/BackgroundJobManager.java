package ir.ac.ut.ie.CA_08_mzFoodDelivery.utils.schedulers;// your package



import ir.ac.ut.ie.CA_08_mzFoodDelivery.domain.MzFoodDelivery.Delivery.Order;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@WebListener
public class BackgroundJobManager implements ServletContextListener {


    public static void startJob() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new SecJob(scheduler), 0, 5, TimeUnit.SECONDS);
    }

    public static void stopJob(ScheduledExecutorService scheduler) {
        scheduler.shutdown();
    }


    public static void waitForArriving(int seconds, Order order) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleWithFixedDelay(new waitJob(scheduler, order), 0, seconds, TimeUnit.SECONDS);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
//        System.out.println("init");
//        scheduler = Executors.newSingleThreadScheduledExecutor();
        // scheduler.scheduleAtFixedRate(new DailyJob(), 0, 1, TimeUnit.DAYS);
//        scheduler.scheduleAtFixedRate(new HourlyJob(), 0, 1, TimeUnit.HOURS);
        //scheduler.scheduleAtFixedRate(new MinJob(), 0, 1, TimeUnit.MINUTES);
//         scheduler.scheduleAtFixedRate(new SecJob(), 0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

}