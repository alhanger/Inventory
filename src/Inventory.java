import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by alhanger on 10/12/15.
 */
public class Inventory {
    public static void main (String[] args) throws Exception {

        HashMap<String, String> users = new HashMap<>();
        ArrayList<Item> invList = new ArrayList();
        Scanner scanner = new Scanner(System.in);

        users.put("Alex", "abcd");
        users.put("Steve", "1234");
        users.put("Edward", "wxyz");

        System.out.println("Please enter your username.");
        String name = scanner.nextLine();
        if (users.containsKey(name)) {
            System.out.println("Please enter your password.");
            String userInput = scanner.nextLine();
            String pw = users.get(name);
            if (pw.equals(userInput)) {
                runInventory(invList, scanner);
            }
        }
    }

    static Item createItem(String itemCategory) throws Exception {
        if (itemCategory.equals("instrument")) {
            return new Instrument();
        }
        else if (itemCategory.equals("equipment")) {
            return new Equipment();
        }
        else if (itemCategory.equals("tool")) {
            return new Tool();
        }
        else if (itemCategory.equals("music book")) {
            return new MusicBook();
        }
        else if (itemCategory.equals("merchandise")) {
            return new Merchandise();
        }
        else {
            throw new Exception("That is an invalid item.");
            }
        }

    static void runInventory(ArrayList<Item> invList, Scanner scanner) throws Exception {
        while (true) {
            int itemNum = 1;
            System.out.println("INVENTORY:");
            for (Item stuff : invList) {
                System.out.println(String.format("%d. [%s] %s (%d)", itemNum, stuff.category, stuff.itemName, stuff.quantity));
                itemNum++;
            }

            System.out.println("Options:");
            System.out.println("[1] Create a new item");
            System.out.println("[2] Remove an item");
            System.out.println("[3] Update quantity");

            String option = scanner.nextLine();
            int optionNum = Integer.valueOf(option);

            if (optionNum == 1) {
                System.out.println("What kind of item would you like to add?");
                System.out.println("Select from the following options:");
                System.out.println("-----Instrument");
                System.out.println("-----Equipment");
                System.out.println("-----Tool");
                System.out.println("-----Music Book");
                System.out.println("-----Merchandise");
                String newItem = scanner.nextLine();
                newItem = newItem.toLowerCase();
                Item newThing = createItem(newItem);
                System.out.println(String.format("What %s would you like to add?", newThing.category));
                String newInput = scanner.nextLine();
                newThing.itemName = newInput;
                invList.add(newThing);
                System.out.println(String.format("Added to the Inventory: %s [%s]", newInput, newThing.category));
            }
            else if (optionNum == 2) {
                System.out.println("Which item would you like to remove? Please select by the item number.");
                String itemRemove = scanner.nextLine();
                int selectNum = Integer.valueOf(itemRemove);
                Item p = invList.get(selectNum - 1);
                invList.remove(p);
            }
            else if (optionNum == 3) {
                System.out.println("Which item would you like to update?");
                String f = scanner.nextLine();
                int pos = 0;
                for (Item thing : invList) {
                    if (thing.itemName.equals(f)) {
                        break;
                    }
                    pos++;
                }
                System.out.println("What is the new quantity?");
                String newQuant = scanner.nextLine();
                int p = Integer.valueOf(newQuant);
                Item object = invList.get(pos);
                object.quantity = p;
            }
        }
    }
}

