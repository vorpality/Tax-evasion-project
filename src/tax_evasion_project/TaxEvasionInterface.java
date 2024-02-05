package tax_evasion_project;
import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a RandomizedBST Structure
 */

public interface TaxEvasionInterface {
  /**Inserts a LargeDepositor item in the bst
  *@param item
  */
  void insert(LargeDepositor item);

  /** Reads data from a txt file_name 
   * @param file_name
   */
  void load(String file_name);

  /** Changed this.savings entry of key AFM to savings 
   * @param AFM,savings
   */
  void updateSavings(int AFM, double savings);

  /** Finds a node with key AFM and returns the object
   * @param AFM 
   * @return The object found or null
   */
  LargeDepositor searchByAFM(int AFM);

  /** Finds all nodes with last name last_name and returns a list
   * @param last_name
   * @return A list of all those fitting the criteria.
   */
  List searchByLastName(String last_name);

  /** Removes an entry (if exists) with key AFM (joins subtrees randomly)
   * @param AFM
   */
  void remove(int AFM);

  /**
   * @return Total_savings / N
   */
  double getMeanSavings();

  /** Creates new tree with key tax_score instead of AFM and prints top k elements
  */
  void printΤopLargeDepositors(int k);

  /** Prints all elements in the tree
   */
  void printByAFM();
}
