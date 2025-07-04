package Services;

import Interfaces.IShippable;

import java.util.Map;

public class ShippingService {
    public void ship(Map<IShippable, Integer> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (Map.Entry<IShippable, Integer> entry : items.entrySet()) {
            IShippable item = entry.getKey();
            int frequency = entry.getValue();
            double itemWeight = item.getWeight() * frequency; // Calculate total weight for this item

            System.out.printf("%dx %s\t %.1fg%n", frequency, item.getName(), itemWeight);
            totalWeight += itemWeight;
        }

        System.out.printf("Total package weight: %.1fkg%n", totalWeight / 1000); // Convert to kg for display
    }
}

