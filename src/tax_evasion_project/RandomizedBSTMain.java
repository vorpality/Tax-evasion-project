package tax_evasion_project;
import java.util.Scanner;

public class RandomizedBSTMain{

  public static void main(String[] args){
    RandomizedBST bst = new RandomizedBST();
    Scanner scanner = new Scanner(System.in);
    while(true){
      System.out.println(
        "Menu\n" +
        "Type the coresponding number, depending on the option you want to execute.\n" +
        "1. Load from file.\n" +
        "2. Update Savings.\n" +
        "3. Search.\n" +
        "4. Remove.\n" +
        "5. Get mean savings.\n" +
        "6. Print the top depositors.\n" +
        "7. Print by AFM.\n" +
        "8. Insert new Large depositor\n" +
        "0. Exit"
      );
      int option = scanner.nextInt();
      switch (option) {
        case 1:
          System.out.println("Enter path to file to be loaded:\n");
          scanner.nextLine();
          String file_name = scanner.nextLine();
          bst.load(file_name);
          break;
        case 2:
          System.out.println("Type the AFM of the person you want to update:");
          int to_change = scanner.nextInt();
          System.out.println("Type the amount you want to change it to:");
          double new_savings = scanner.nextDouble();
          bst.updateSavings(to_change, new_savings);
          System.out.println("The data has been changed successfully.");
          break;
        case 3:
          System.out.println(
            "Pick the search method you want by typing the coresponding number:\n" +
            "1. Search by AFM.\n" +
            "2. Search by Last name\n"
          );
          int search_option = scanner.nextInt();
          switch (search_option){
            case 1:
              System.out.println("Enter the AFM of the entry you want to see:\n");
              int to_check = scanner.nextInt();
              bst.searchByAFM(to_check);
              break;
            case 2:
              System.out.println("Enter the Last name of the entry you want to see:\n");
              scanner.nextLine();
              String name_to_check = scanner.nextLine();
              bst.searchByLastName(name_to_check);
              break;
          }
          break;
        case 4:
          System.out.println("Enter the AFM of the entry you want to remove:\n");
          int to_remove = scanner.nextInt();
          bst.remove(to_remove);
          System.out.println("The data has been removed successfully.");
          break;
        case 5:
            double m_s = bst.getMeanSavings();
            System.out.println("Mean savings are : " + m_s);
          break;
        case 6:
          System.out.println("Type how many of the top Depositors would you like to see?\n");
          bst.printΤopLargeDepositors(scanner.nextInt());
          break;
        case 7:
          bst.printByAFM();
          break;
        case 8:
          System.out.println("Type the AFM:\n");
          int AFM = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Type first name:\n");
          String first_name = scanner.nextLine();
          System.out.println("Type last name:\n");
          String last_name = scanner.nextLine();
          System.out.println("Type savings:\n");
          double savings = scanner.nextDouble();
          System.out.println("Type taxed income:\n");
          double taxed_income = scanner.nextDouble();
          bst.insert(new LargeDepositor(AFM, first_name, last_name, savings, taxed_income));          
          break;
        case 0:
          System.out.println("Exiting program.");
          scanner.close();
          return;
        default:
          System.out.println("Invalid choice. Please enter a number between 1 and 8.");
      }
    }
  }
}