import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShippingCostTest {
    public String description = "Тестовое описание";
    public String address = "Тестовый адрес";

    @Test
    public void standardParcelShippingCost_weight0(){
        StandardParcel parcel = new StandardParcel(description, 0, address, 1);
        Assertions.assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    public void standardParcelShippingCost_weight5(){
        StandardParcel parcel = new StandardParcel(description, 5, address, 1);
        Assertions.assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    public void standardParcelShippingCost_weight100(){
        StandardParcel parcel = new StandardParcel(description, 100, address, 1);
        Assertions.assertEquals(200, parcel.calculateDeliveryCost());
    }

    //Скоропортящиеся
    @Test
    public void parishableParcelShippingCost_weight0(){
        PerishableParcel parcel = new PerishableParcel(description, 0, address, 1, 10);
        Assertions.assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    public void parishableParcelShippingCost_weight5(){
        PerishableParcel parcel = new PerishableParcel(description, 5, address, 1, 10);
        Assertions.assertEquals(15, parcel.calculateDeliveryCost());
    }

    @Test
    public void parishableParcelShippingCost_weight100(){
        PerishableParcel parcel = new PerishableParcel(description, 100, address, 1, 10);
        Assertions.assertEquals(300, parcel.calculateDeliveryCost());
    }

    //Хрупкие
    @Test
    public void fragileParcelShippingCost_weight0(){
        FragileParcel parcel = new FragileParcel(description, 0, address, 1);
        Assertions.assertEquals(0, parcel.calculateDeliveryCost());
    }

    @Test
    public void fragileParcelShippingCost_weight5(){
        FragileParcel parcel = new FragileParcel(description, 5, address, 1);
        Assertions.assertEquals(20, parcel.calculateDeliveryCost());
    }

    @Test
    public void fragileParcelShippingCost_weight100(){
        FragileParcel parcel = new FragileParcel(description, 100, address, 1);
        Assertions.assertEquals(400, parcel.calculateDeliveryCost());
    }

}
