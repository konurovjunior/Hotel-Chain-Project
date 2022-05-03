package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(ServiceInReservationPK.class)
@Table(name = "service_in_reservation")
public class ServiceInReservation {

    @Id
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "count")
    private int count;

    @Id
    @Column(name = "service_id")
    private int serviceId;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

}
