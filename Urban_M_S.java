import java.util.*;

// ===== Entity Classes =====
class Citizen {
    private String name;
    private String nid;
    private int familySize;
    private double income;
    private String location;

    public Citizen(String name, String nid, int familySize, double income, String location) {
        this.name = name;
        this.nid = nid;
        this.familySize = familySize;
        this.income = income;
        this.location = location;
    }

    // Getters
    public String getName() { return name; }
    public String getNid() { return nid; }
    public int getFamilySize() { return familySize; }
    public double getIncome() { return income; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return name + " (NID: " + nid + ", Location: " + location + ")";
    }
}

class House {
    private String address;
    private Citizen owner;

    public House(String address) {
        this.address = address;
    }

    public String getAddress() { return address; }
    public Citizen getOwner() { return owner; }
    public void setOwner(Citizen owner) { this.owner = owner; }

    @Override
    public String toString() {
        return address + (owner != null ? " [Owner: " + owner.getName() + "]" : " [Available]");
    }
}

class GarbageBin {
    private String location;
    private boolean full;

    public GarbageBin(String location) {
        this.location = location;
        this.full = false;
    }

    public String getLocation() { return location; }
    public boolean isFull() { return full; }
    public void setFull(boolean full) { this.full = full; }

    @Override
    public String toString() {
        return location + " [Status: " + (full ? "FULL" : "EMPTY") + "]";
    }
}

class TrafficSignal {
    private String location;
    private String status;

    public TrafficSignal(String location) {
        this.location = location;
        this.status = "RED";
    }

    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return location + " [Signal: " + status + "]";
    }
}

// ===== Module Classes =====
class HousingModule {
    private List<House> houses = new ArrayList<>();

    public void addHouse(House house) {
        houses.add(house);
    }

    public void allocateHouse(Citizen citizen) {
        for (House h : houses) {
            if (h.getOwner() == null && h.getAddress().toLowerCase().contains(citizen.getLocation().toLowerCase())) {
                h.setOwner(citizen);
                System.out.println("✅ House allocated: " + h);
                return;
            }
        }
        System.out.println("❌ No available houses in " + citizen.getLocation());
    }

    public void listHouses() {
        System.out.println("🏠 Houses List:");
        for (House h : houses) {
            System.out.println("- " + h);
        }
    }
}

class GarbageModule {
    private List<GarbageBin> bins = new ArrayList<>();

    public void addBin(GarbageBin bin) {
        bins.add(bin);
    }

    public void markBinFull(String location) {
        for (GarbageBin bin : bins) {
            if (bin.getLocation().equalsIgnoreCase(location)) {
                bin.setFull(true);
                System.out.println("🗑 Bin marked FULL: " + bin);
                return;
            }
        }
        System.out.println("❌ No bin found at location: " + location);
    }

    public void cleanupBins() {
        for (GarbageBin bin : bins) {
            if (bin.isFull()) {
                bin.setFull(false);
                System.out.println("✅ Cleaned: " + bin.getLocation());
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

class TrafficModule {
    private List<TrafficSignal> signals = new ArrayList<>();

    public void addSignal(TrafficSignal signal) {
        signals.add(signal);
    }

    public void changeSignal(String location, String status) {
        for (TrafficSignal s : signals) {
            if (s.getLocation().equalsIgnoreCase(location)) {
                s.setStatus(status);
                System.out.println("🚦 Signal updated: " + s);
                return;
            }
        }
        System.out.println("❌ No traffic signal at: " + location);
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

// ===== Main App =====
public class Urban_M_S {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HousingModule housing = new HousingModule();
        GarbageModule garbage = new GarbageModule();
        TrafficModule traffic = new TrafficModule();

        // Pre-load data
        housing.addHouse(new House("Mirpur-10"));
        housing.addHouse(new House("Uttara-11"));
        housing.addHouse(new House("Banani-Block-A"));

        garbage.addBin(new GarbageBin("Dhanmondi-27"));
        garbage.addBin(new GarbageBin("Banani"));
        garbage.addBin(new GarbageBin("Mirpur"));

        traffic.addSignal(new TrafficSignal("Shahbagh"));
        traffic.addSignal(new TrafficSignal("Farmgate"));
        traffic.addSignal(new TrafficSignal("Gulshan-2"));

        while (true) {
            System.out.println("\n===== Dhaka Urban Management System (DUMS) =====");
            System.out.println("1. Housing Allocation");
            System.out.println("2. Garbage Management");
            System.out.println("3. Traffic Control");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

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
                    sc.nextLine();
                    System.out.print("Enter Preferred Location (Mirpur/Uttara/Banani): ");
                    String location = sc.nextLine();

                    Citizen citizen = new Citizen(name, nid, famSize, income, location);
                    housing.allocateHouse(citizen);
                    housing.listHouses();
                    break;

                case 2:
                    garbage.listBins();
                    System.out.print("Enter Bin Location to mark FULL: ");
                    String binLocation = sc.nextLine();
                    garbage.markBinFull(binLocation);
                    garbage.cleanupBins();
                    garbage.listBins();
                    break;

                case 3:
                    traffic.listSignals();
                    System.out.print("Enter Signal Location: ");
                    String sigLoc = sc.nextLine();
                    System.out.print("Enter new status (GREEN/RED): ");
                    String status = sc.nextLine();
                    traffic.changeSignal(sigLoc, status);
                    System.out.print("Enter accident location to report: ");
                    String accLoc = sc.nextLine();
                    traffic.reportAccident(accLoc);
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
