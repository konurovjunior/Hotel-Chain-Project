package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@IdClass(HotelOffersServicePK.class)
@Table(name = "hotel_offers_service")
public class HotelOffersService {

    @Id
    @Column(name = "hotel_id")
    private int hotelId;

    @Id
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

}
