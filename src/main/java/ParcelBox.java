import java.util.ArrayList;


public class ParcelBox<T extends Parcel> {

    private ArrayList<T> parcelsInBox = new ArrayList<>();
    private int maxWeight;

    public ParcelBox(int maxweight) {
        this.maxWeight = maxweight;
    }

    public void addParcel(T parcel){
        parcelsInBox.add(parcel);
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
