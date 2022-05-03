package dreamteam.hotelchainproject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

public class HotelOffersServicePK implements Serializable {

    @Column(name = "hotel_id")
    private int hotelId;

    @Column(name = "service_id")
    private int serviceId;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

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
        HotelOffersServicePK that = (HotelOffersServicePK) o;
        return hotelId == that.hotelId &&
                serviceId == that.serviceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, serviceId);
    }
}
