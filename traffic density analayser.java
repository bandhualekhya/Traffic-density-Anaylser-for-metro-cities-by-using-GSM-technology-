import java.util.*;

public class TrafficDensityAnalyzer {

    static class Road {
        String name;
        int vehicles;

        Road(String name) {
            this.name = name;
            this.vehicles = 0;
        }

        void addVehicles(int count) {
            vehicles += count;
        }

        void clearVehicles() {
            vehicles = 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // Four roads of a metro intersection
        Road north = new Road("North");
        Road east  = new Road("East");
        Road south = new Road("South");
        Road west  = new Road("West");

        Road[] roads = { north, east, south, west };
        Random random = new Random();

        System.out.println("=== TRAFFIC DENSITY ANALYZER WITH SIGNAL SYSTEM ===");
        System.out.println("Simulating adaptive traffic signals for metro city...\n");

        for (int cycle = 1; cycle <= 10; cycle++) {  // run for 10 cycles
            System.out.println("\n------ Cycle " + cycle + " ------");

            // Step 1: Randomly generate vehicle counts
            for (Road r : roads) {
                int newVehicles = random.nextInt(20); // 0–19 new vehicles
                r.addVehicles(newVehicles);
                System.out.println(r.name + " Road → Vehicles waiting: " + r.vehicles);
            }

            // Step 2: Find road with highest traffic density
            Road maxRoad = roads[0];
            for (Road r : roads) {
                if (r.vehicles > maxRoad.vehicles) {
                    maxRoad = r;
                }
            }

            // Step 3: Set signal green for that road
            System.out.println("\nSignal GREEN for: " + maxRoad.name + " road");
            int greenTime = 5 + maxRoad.vehicles / 3;  // more vehicles → more green time
            if (greenTime > 15) greenTime = 15;
            System.out.println("Green light duration: " + greenTime + " seconds");

            // Step 4: Vehicles pass during green
            for (int t = greenTime; t > 0; t--) {
                System.out.print("  " + t + " ");
                Thread.sleep(500); // half-second per step (for demo)
            }
            System.out.println("\nSignal RED for: " + maxRoad.name + " road");

            // Step 5: Vehicles cleared
            System.out.println("Vehicles cleared from " + maxRoad.name + " road.\n");
            maxRoad.clearVehicles();

            // Small pause before next cycle
            Thread.sleep(1000);
        }

        System.out.println("\nSimulation finished. Traffic signals adjusted dynamically.");
        sc.close();
    }
}