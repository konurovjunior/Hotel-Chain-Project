package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class ServiceInReservationPK implements Serializable {
    private int reservationId;
    private int serviceId;

    @Column(name = "reservation_id")
    @Id
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Column(name = "service_id")
    @Id
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInReservationPK that = (ServiceInReservationPK) o;
        return reservationId == that.reservationId &&
                serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, serviceId);
    }
}
