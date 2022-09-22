import java.util.*;
public class ShopMenu {
    private String[] menuItems = {
            "1) Add Element", "2) Edit Price", "3) Delete Element",
            "4) Show All Elements", "5) Exit"
    };
    private NavigableMap<String,Integer> games = new TreeMap<String,Integer>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ShopMenu sm = new ShopMenu();

        // Add new videogames to have more than one
        sm.getGames().put("Fifa", 10);
        sm.getGames().put("COD", 12);
        sm.getGames().put("0.AD", 0);
        sm.getGames().put("WOW", 21);

        while (true) {
            System.out.println("----------------------------\n Shop Menu \n----------------------------");
            for (int i = 0; i < sm.getMenuItems().length; i++) {
                System.out.println(sm.getMenuItems()[i]);
            }
            System.out.print("Option: ");
            while (!sm.getSc().hasNextInt()) {
                System.out.println("The option only can be a Integer\n");
                System.out.print("Option: ");
                sm.getSc().nextLine();
            }
            int opc = sm.getSc().nextInt();
            switch(opc)
            {
                case 1: {
                    sm.addNewValue(sm);
                    break;
                }
                case 2: {
                    sm.editValue(sm);
                    break;
                }
                case 3: {
                    sm.deleteValue(sm);
                    break;
                }
                case 4: {
                    sm.showGames(sm.getGames());
                    break;
                }
                case 5: {
                    System.exit(0);
                }
            }
        }

    }

    public void addNewValue(ShopMenu sm)
    {
        System.out.println("--------------------- Add Element ---------------------");
        System.out.println("Name: ");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.print("Option: ");
            sc.nextLine();
        }
        String gameName = sc.next();
        if (gameName == null) {
            System.out.println("NULL is not a valid option.");
        }
        else
        {
            sc.nextLine();
            if (!sm.getGames().containsKey(gameName)) {
                System.out.println("Price: ");
                while (!sc.hasNextInt()) {
                    System.out.println("The option only can be a Integer\n");
                    System.out.print("Option: ");
                    sc.nextInt();
                }
                int gamePrice = sc.nextInt();
                if (gamePrice < 0) {
                    System.out.println("The product price cannot be less than 0€.");
                }
                else
                {
                    sm.getGames().put(gameName, gamePrice);
                }
            }
            else
            {
                System.out.println("The product already exists.");
            }
        }
    }

    public void editValue(ShopMenu sm)
    {
        System.out.println("--------------------- Edit Element ---------------------");
        System.out.println("Name: ");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.print("Option: ");
            sc.nextLine();
        }
        String gameName = sc.next();
        sc.nextLine();
        if (gameName == null) {
            System.out.println("NULL is not a valid option.");
        }
        else if (!sm.getGames().containsKey(gameName)) {
            System.out.println("The game " + gameName + " does not exists.");
        }
        else {
            System.out.println("\n1) Name: " + gameName + "\n2)Price:" + sm.getGames().get(gameName));
            System.out.println("Option: ");
            while (!sc.hasNext()) {
                System.out.println("The option only can be a Integer\n");
                System.out.print("Option: ");
                sc.nextLine();
            }
            int option = sc.nextInt();
            sc.nextLine();
            if (gameName == null) {
                System.out.println("NULL is not a valid option.");
            }
            else {
                switch (option){
                    case 1: {
                        System.out.println("####################### Change Name #######################");
                        if(!sm.getGames().containsKey(gameName)) {
                            System.out.println("The game " + gameName + " does not exists.");
                        }
                        else {
                            System.out.println("New Name: ");
                            while (!sc.hasNext()) {
                                System.out.println("The option only can be a String\n");
                                System.out.print("New Name: ");
                                sc.nextLine();
                            }
                            String newName = sc.next();
                            if (newName == null) {
                                System.out.println("NULL is not a valid option.");
                            }
                            else {
                                int price = sm.getGames().get(gameName);
                                sm.getGames().put(newName, price);
                                System.out.println("Changes has been changed.");
                                break;
                            }
                        }
                    }
                    case 2: {
                        System.out.println("####################### Change Price #######################");
                        System.out.println("Game to change: ");
                        while (!sc.hasNext()) {
                            System.out.println("The option only can be a String\n");
                            System.out.print("Option: ");
                            sc.nextLine();
                        }
                        gameName = sc.next();
                        if (gameName == null) {
                            System.out.println("NULL is not a valid option.");
                        }
                        else if(!sm.getGames().containsKey(gameName)) {
                            System.out.println("The game " + gameName + " does not exists.");
                        }
                        else {
                            System.out.println("New Price: ");
                            while (!sc.hasNextInt()) {
                                System.out.println("The option only can be a Integer\n");
                                System.out.print("New Price: ");
                                sc.nextLine();
                            }
                            int newPrice = sc.nextInt();
                            sm.getGames().put(gameName, newPrice);
                            System.out.println("Changes has been changed.");
                            break;
                        }
                    }
                }
            }
        }
    }

    public void deleteValue(ShopMenu sm) {
        System.out.println("--------------------- Delete Element ---------------------");
        System.out.println("Name: ");
        while (!sc.hasNext()) {
            System.out.println("The option only can be a String\n");
            System.out.print("Option: ");
            sc.nextLine();
        }
        String gameName = sc.next();
        sc.nextLine();
        if (gameName == null) {
            System.out.println("NULL is not a valid option.");
        }
        else if (!sm.getGames().containsKey(gameName)) {
            System.out.println("The game " + gameName + " does not exists.");
        }
        else {
            System.out.println("Are you sure you want to delete the product " + gameName + " (S / N)");
            String validator =  sc.nextLine();;
            if (validator.equalsIgnoreCase("S")) {
                sm.getGames().remove(gameName);
                System.out.println("Delete product sucsesfuly.");
            }else {
                System.out.println("Delete product canceled.");
            }
        }
    }

    public static void showGames(Map m){
        System.out.println("Games: ");
        for(Iterator i=m.keySet().iterator(); i.hasNext();){
            String k=(String)i.next();
            int v=(int)m.get(k);
            System.out.println("Name: " + k+ " Price: " +v);
        }
    }

    public NavigableMap<String, Integer> getGames() {
        return games;
    }

    public void setGames(NavigableMap<String, Integer> games) {
        this.games = games;
    }

    public String[] getMenuItems() {
        return menuItems;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

}
