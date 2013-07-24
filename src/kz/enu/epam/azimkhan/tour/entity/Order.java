package kz.enu.epam.azimkhan.tour.entity;

import java.util.Date;

/**
 *
 */
public class Order extends Entity{

    /**
     * client
     */
    private User user;

    /**
     * ordered tour
     */
    private Tour tour;

    /**
     * date and time of purchase
     */
    private Date dateTime;

    /**
     * amount paid
     */
    private double amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.amount, amount) != 0) return false;
        if (!dateTime.equals(order.dateTime)) return false;
        if (!tour.equals(order.tour)) return false;
        if (!user.equals(order.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = user.hashCode();
        result = 31 * result + tour.hashCode();
        result = 31 * result + dateTime.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
