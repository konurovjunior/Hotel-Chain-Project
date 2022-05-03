package dreamteam.hotelchainproject.models;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @Column(name = "service_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
