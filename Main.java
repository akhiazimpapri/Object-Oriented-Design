import java.util.*;

class Citizen {
    private String name;
    private String nid;
    private int familySize;
    private double income;

    public Citizen(String name, String nid, int familySize, double income) {
        this.name = name;
        this.nid = nid;
        this.familySize = familySize;
        this.income = income;
    }

    public String getName() { return name; }
    public int getFamilySize() { return familySize; }
    public double getIncome() { return income; }

    @Override
    public String toString() {
        return name + " (NID: " + nid + ")";
    }
}

class House {
    private String address;
    private Citizen owner;

    public House(String address) {
        this.address = address;
    }

    public void allocateTo(Citizen citizen) {
        this.owner = citizen;
    }

    @Override
    public String toString() {
        return address + (owner != null ? " [Owner: " + owner.getName() + "]" : " [Available]");
    }
}

class HousingAuthority {
    private List<House> houses = new ArrayList<>();

    public void addHouse(House house) {
        houses.add(house);
    }

    public void allocateHouse(Citizen citizen) {
        for (House h : houses) {
            if (h.toString().contains("Available")) {
                h.allocateTo(citizen);
                System.out.println("✅ House allocated: " + h);
                return;
            }
        }
        System.out.println("❌ No houses available!");
    }

    public void listHouses() {
        System.out.println("🏠 Houses List:");
        for (House h : houses) {
            System.out.println("- " + h);
        }
    }
}

class GarbageBin {
    private String location;
    private boolean isFull;

    public GarbageBin(String location) {
        this.location = location;
        this.isFull = false;
    }

    public void storeWaste() {
        this.isFull = true;
    }

    public void emptyBin() {
        this.isFull = false;
    }

    public boolean isFull() {
        return isFull;
    }

    @Override
    public String toString() {
        return location + " [Status: " + (isFull ? "FULL" : "EMPTY") + "]";
    }
}

class GarbageManagement {
    private List<GarbageBin> bins = new ArrayList<>();

    public void addBin(GarbageBin bin) {
        bins.add(bin);
    }

    public void assignCleanup() {
        for (GarbageBin bin : bins) {
            if (bin.isFull()) {
                bin.emptyBin();
                System.out.println("🗑 Garbage collected from: " + bin);
            }
        }
    }

    public void listBins() {
        System.out.println("🗑 Garbage Bins:");
        for (GarbageBin bin : bins) {
            System.out.println("- " + bin);
        }
    }
}

class TrafficSignal {
    private String location;
    private String status;

    public TrafficSignal(String location) {
        this.location = location;
        this.status = "RED";
    }

    public void changeSignal(String status) {
        this.status = status;
        System.out.println("🚦 Signal at " + location + " changed to " + status);
    }

    @Override
    public String toString() {
        return location + " [Signal: " + status + "]";
    }
}

class TrafficControl {
    private List<TrafficSignal> signals = new ArrayList<>();

    public void addSignal(TrafficSignal signal) {
        signals.add(signal);
    }

    public void listSignals() {
        System.out.println("🚦 Traffic Signals:");
        for (TrafficSignal s : signals) {
            System.out.println("- " + s);
        }
    }

    public void reportAccident(String location) {
        System.out.println("⚠ Accident reported at: " + location);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HousingAuthority housing = new HousingAuthority();
        GarbageManagement garbage = new GarbageManagement();
        TrafficControl traffic = new TrafficControl();

        // Pre-load data
        housing.addHouse(new House("Mirpur-10"));
        housing.addHouse(new House("Uttara-11"));
        garbage.addBin(new GarbageBin("Dhanmondi-27"));
        garbage.addBin(new GarbageBin("Banani"));
        traffic.addSignal(new TrafficSignal("Shahbagh"));
        traffic.addSignal(new TrafficSignal("Farmgate"));

        while (true) {
            System.out.println("\n===== Dhaka Urban Management System (DUMS) =====");
            System.out.println("1. Housing Allocation");
            System.out.println("2. Garbage Management");
            System.out.println("3. Traffic Control");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Citizen Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter NID: ");
                    String nid = sc.nextLine();
                    System.out.print("Enter Family Size: ");
                    int famSize = sc.nextInt();
                    System.out.print("Enter Income: ");
                    double income = sc.nextDouble();

                    Citizen citizen = new Citizen(name, nid, famSize, income);
                    housing.allocateHouse(citizen);
                    housing.listHouses();
                    break;

                case 2:
                    garbage.listBins();
                    System.out.print("Mark a bin as full (index starting 0): ");
                    int idx = sc.nextInt();
                    if (idx >= 0 && idx < 2) {
                        garbage.addBin(new GarbageBin("NewBin-" + idx)); // for demo
                    }
                    garbage.assignCleanup();
                    garbage.listBins();
                    break;

                case 3:
                    traffic.listSignals();
                    System.out.print("Enter Signal index to change (0/1): ");
                    int sIdx = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new status (GREEN/RED): ");
                    String status = sc.nextLine();
                    traffic.addSignal(new TrafficSignal("Custom-" + sIdx));
                    traffic.reportAccident("Mohakhali");
                    break;

                case 0:
                    System.out.println("✅ Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
