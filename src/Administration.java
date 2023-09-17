import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Vector;

public class Administration implements Runnable {
    private Thread thr;
    private static final String INPUT_FILE_NAME = "E:\\JAVA_FINAL\\Project2\\restaurant.txt";
    private static final String INPUT_FILE_NAME1 = "E:\\JAVA_FINAL\\Project2\\menu.txt";
    Vector<Restaurant> restaurants;

    Administration(Vector<Restaurant> restaurants) {
        this.restaurants = restaurants;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int ch = 1;
        while (ch>0) {
            System.out.println("select option");
            System.out.println("3.Add Restaurant");
            System.out.println("4.Add Food item to menu");
            System.out.println("5.Close Administration and server");
            if (ch == 3) { // add Restaurant work done
                String name, zipCode;
                double score;
                System.out.println("Enter Restaurant name:");
                scanner.nextLine();
                name = scanner.nextLine();
                System.out.println("Enter Score:");
                score = scanner.nextDouble();
                System.out.println("Enter Restaurant id:");
                int id = scanner.nextInt();
                System.out.println("Enter Restaurant zipCode:");
                scanner.nextLine();
                zipCode = scanner.nextLine();
                System.out.println("How many category do you want?(Between 1 to 3)");
                int temp = scanner.nextInt();
                while (temp < 1 || temp > 3) {
                    System.out.println("Invalid choice.Please enter a number from 1 to 3.");
                    temp = scanner.nextInt();
                }
                String catName[] = new String[temp];
                System.out.println("Enter " + temp + " category names:");
                scanner.nextLine();
                for (int i = 0; i < temp; i++) {
                    catName[i] = scanner.nextLine();
                }

                restaurants.add(new Restaurant(id, name, score, name, zipCode, catName));
            } else if (ch == 4) { // add Food item work done
                String resName;
                System.out.println("Enter Restaurant name :");
                scanner.nextLine();
                resName = scanner.nextLine();
                System.out.println("Category Name:");
                // scanner.nextLine();
                String catName = scanner.nextLine();
                System.out.println("Enter food name:");
                String FoodName = scanner.nextLine();
                System.out.println("Enter food price");
                double FoodPrice = scanner.nextInt();
                for (Restaurant x : restaurants) {
                    if (x.getName().equalsIgnoreCase(resName)) {
                        x.addFood(catName, FoodName, FoodPrice);
                    }
                }
            } else if (ch == 5) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME));
                    for (int i = 0; i < restaurants.size(); i++) {
                        Restaurant temp = restaurants.get(i);
                        bw.write(temp.getId() + "," + temp.getName() + "," + temp.getScore() + "," + temp.getPrice()
                                + ","
                                + temp.getZip_code() + ",");
                        // System.out.println(temp.getCategory().length);
                        for (int j = 0; j < temp.getCatCounter(); j++) {
                            if (j == temp.getCatCounter() - 1) {
                                bw.write(temp.getCategory()[j]);
                                break;
                            }
                            bw.write(temp.getCategory()[j] + ",");
                        }
                        bw.write(System.lineSeparator());
                    }
                    bw.close();
                    bw = new BufferedWriter(new FileWriter(INPUT_FILE_NAME1));
                    for (Restaurant x : restaurants) {
                        for (Food f : x.getAllFoods()) {
                            bw.write(f.getRestaurantId() + "," + f.getCatagory() + "," + f.getName() + ","
                                    + f.getPrice());
                            bw.write(System.lineSeparator());
                        }
                    }
                    bw.close();
                    break;
                } catch (Exception e) {
                    // TODO: handle exception
                }
                // File fout=new File(OUTPUT_FILE_NAME);
                // boolean success=fout.renameTo(new File(INPUT_FILE_NAME));
                // System.out.println(success);
                // fin.delete();
            }
        }
        scanner.close();
    }
}
