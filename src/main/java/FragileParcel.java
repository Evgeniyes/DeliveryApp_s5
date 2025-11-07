
//Хрупкая посылка
public class FragileParcel extends Parcel {
    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка <<" + getDescription()  + ">> обёрнута в защитную плёнку");
        packagingComplete();
    }
}
