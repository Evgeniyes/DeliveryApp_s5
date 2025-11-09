import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Parcel> allTrackableParcels = new ArrayList<>();

    //Создаем коробки
    private static ParcelBox boxForStandard = new ParcelBox<>(36);
    private static ParcelBox boxForParishable = new ParcelBox<>(36);
    private static ParcelBox boxForFragile = new ParcelBox<>(36);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    setReportStatus();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Задать локации для отслеживаемых посылок");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels

        ParcelBox box = null; //Текущая коробка для добавления

        showAddParcelMenu();
        int parcelType = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите вес посылки:");
        int weight = Integer.parseInt(scanner.nextLine());

        if (parcelType == 1) {
            box = boxForStandard;
        } else if (parcelType == 2) {
            box = boxForParishable;
        } else if (parcelType == 3) {
            box = boxForFragile;
        } else {
            System.out.println("Такого типа посылки не существует");
        }

        if (box != null && addParcelInBox(parcelType, weight, box)) {
            System.out.println("Посылка успешно добавлена");
            System.out.println("-".repeat(25));
        } else {
            System.out.println("Добавить посылку не удалось");
            System.out.println("-".repeat(25));
        }

    }

    private static boolean addParcelInBox(int parcelType, int weight, ParcelBox box) {

        if (box.freeWeight() > weight) {
            System.out.println("Введите описание посылки:");
            String description = scanner.nextLine();
            System.out.println("Введите адрес доставки:");
            String deliveryAddress = scanner.nextLine();
            System.out.println("Введите день отправки:");
            int sendDay = Integer.parseInt(scanner.nextLine());

            if (parcelType == 1) {
                FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(parcel);
                box.addParcel(parcel);
                return true;
            } else if (parcelType == 2) {
                System.out.println("Введите срок годности в днях:");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel parcel = new PerishableParcel(description, weight,
                        deliveryAddress, sendDay, timeToLive);
                allParcels.add(parcel);
                box.addParcel(parcel);
                return true;
            } else if (parcelType == 3) {
                FragileParcel parcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allTrackableParcels.add(parcel);
                box.addParcel(parcel);
                return true;
            }
        } else {
            System.out.println("В коробку с этим типом посылок можно добавить не более "
                    + box.freeWeight() + " кг.");
            return false;
        }
        return false;
    }


    private static void showAddParcelMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить стандартную посылку");
        System.out.println("2 — Добавить скоропортящуюся посылку");
        System.out.println("3 — Добавить хрупкую посылку");
        System.out.println("0 — Отмена");
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        if (!allParcels.isEmpty() || !allTrackableParcels.isEmpty()) {
            for (Parcel parcel : allParcels) {
                parcel.packageItem();
                parcel.deliver();
            }

            for (Parcel parcel : allTrackableParcels) {
                parcel.packageItem();
                parcel.deliver();
            }

            System.out.println("Все посылки отправлены");
            allParcels.clear();
            allTrackableParcels.clear();
        } else {
            System.out.println("Нет посылок для отправки");
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sum = 0;

        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }
        for (Parcel trackableParcel : allTrackableParcels) {
            sum += trackableParcel.calculateDeliveryCost();
        }

        System.out.println("Стоимость доставки всех посылок = " + sum);
    }

    private static void setReportStatus() {
        if (!allTrackableParcels.isEmpty()) {
            for (Parcel parcel : allTrackableParcels) {
                if (parcel instanceof Trackable) {
                    System.out.println("Введите новую локацию для посылки " + parcel.getDescription());
                    String newLocation = scanner.nextLine();
                    ((Trackable) parcel).reportStatus(newLocation);
                }
            }
        } else {
            System.out.println("Нет отслеживаемых посылок.");
        }
        System.out.println("-".repeat(25));
    }
}
