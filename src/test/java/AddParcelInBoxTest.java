import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AddParcelInBoxTest {

    ParcelBox box = new ParcelBox<>(10);

    public String description = "Тестовое описание";
    public String address = "Тестовый адрес";

    @Test
    public void addPercelWeight0(){
        Assertions.assertTrue(box.addParcel(
                new StandardParcel(description, 0, address, 1)));
    }

    @Test
    public void addPercelWeight5(){
        Assertions.assertTrue(box.addParcel(
                new StandardParcel(description, 5, address, 1)));
    }

    @Test
    public void addPercelWeightMax(){
        Assertions.assertTrue(box.addParcel(
                new StandardParcel(description, 10, address, 1)));
    }

    @Test
    public void addPercelWeightMoreMax(){
        Assertions.assertFalse(box.addParcel(
                new StandardParcel(description, 11, address, 1)));
    }
}
