import java.util.ArrayList;


public class ParcelBox<T extends Parcel> {

    private ArrayList<T> parcelsInBox = new ArrayList<>();
    private int maxWeight;

    public ParcelBox(int maxweight) {
        this.maxWeight = maxweight;
    }

    public boolean addParcel(T parcel){
        if(parcel.getWeight() <= freeWeight()){
            parcelsInBox.add(parcel);
            return true;
        } else {
            System.out.println("В корзине для данного вида посылок свободно " + freeWeight() + " кг.");
            return false;
        }

    }

    public ArrayList<T> getAllParcels(){
        return parcelsInBox;
    }

    public int freeWeight(){
        int sum = 0;
        for (T p : parcelsInBox) {
            sum += p.getWeight();
        }
        return maxWeight - sum;
    }
}
