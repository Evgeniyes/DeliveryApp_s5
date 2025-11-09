import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PerishableParcelTest {
    public String description = "Тестовое описание";
    public String address = "Тестовый адрес";
    public int weight = 5;

    @Test
    public void PerishableParcelTimeToLive_0_0_day0(){
        PerishableParcel parcel = new PerishableParcel(description, weight, address, 0, 0);
        Assertions.assertFalse(parcel.isExpired(0));
    }

    @Test
    public void PerishableParcelTimeToLive_0_0_day1(){
        PerishableParcel parcel = new PerishableParcel(description, weight, address, 1, 0);
        Assertions.assertFalse(parcel.isExpired(0));
    }

    @Test
    public void PerishableParcelTimeToLive_1_1_day1(){
        PerishableParcel parcel = new PerishableParcel(description, weight, address, 1, 1);
        Assertions.assertFalse(parcel.isExpired(1));
    }

    @Test
    public void PerishableParcelTimeToLive_5_3_day1(){
        PerishableParcel parcel = new PerishableParcel(description, weight, address, 1, 5);
        Assertions.assertFalse(parcel.isExpired(3));
    }

    @Test
    public void PerishableParcelTimeToLive_2_5_day10(){
        PerishableParcel parcel = new PerishableParcel(description, weight, address, 2, 5);
        Assertions.assertTrue(parcel.isExpired(10));
    }
}
