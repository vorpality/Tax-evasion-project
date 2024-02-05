package tax_evasion_project;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

class RandomizedBST implements TaxEvasionInterface {
  private class TreeNode {
    LargeDepositor item;
    TreeNode left;
    TreeNode right;
    TreeNode parent; 
    int N; 

    public TreeNode(LargeDepositor item){
      this.item = item;
      this.left = null;
      this.right = null;
      this.parent= null;
      this.N = 1;
    }
  };

  private TreeNode root; 

  RandomizedBST(){};


@Override
  public void insert(LargeDepositor item){
    item.print_item();
    this.root = insert_element(this.root,item);
  }

@Override
  public void load(String file_name){
    String line = "";
    try (Scanner scanner = new Scanner(new File(file_name))) {
      while (scanner.hasNextLine()) {
          line = scanner.nextLine();
          String[] parts = line.split("\\s+");
          if (parts.length == 5) {
              int AFM = Integer.parseInt(parts[0]);
              String first_name = parts[1];
              String last_name = parts[2];
              double savings = Double.parseDouble(parts[3]);
              double taxed_income = Double.parseDouble(parts[4]);
              insert(new LargeDepositor(AFM, first_name, last_name, savings, taxed_income));
          }
        }
        System.out.println("The file has been loaded successfully.");
      } catch (FileNotFoundException e) {
          System.out.println("File " + file_name + " not found.");
      } catch (NumberFormatException e) {
          System.out.println("Error parsing number at line " + line) ;
      }
    return;
  }

@Override
  public void updateSavings(int AFM, double savings){
    TreeNode in_question = find_node_r(this.root, AFM);
    if (in_question == null) {
      System.out.println("Could not find a person with that AFM");
      return;
    }
    else {
      in_question.item.set_savings(savings);
      return;
    }
  }

@Override
  public LargeDepositor searchByAFM(int AFM){
    TreeNode in_question = find_node_r(this.root, AFM);
    if (in_question == null) {
      System.out.println("Could not find a person with that AFM");
      return null;
    }
    else {
      in_question.item.print_item();
      return in_question.item;
    }
  }

@Override
  public List searchByLastName(String last_name){
    List results = new List();
    tree_brush(results, this.root, last_name);
    if(results.size() < 6){
      results.print_items();
    }
    return results;
  }

@Override
  public void remove(int AFM){
    TreeNode to_remove = find_node_r(this.root, AFM);
    if (to_remove == null){
      System.out.println("There was no one with that AFM.");
      return;
    }
    if(to_remove == this.root){
      this.root = joinLR(this.root.left, this.root.right);
      this.root.parent=null;
    }
    else {
      if (to_remove.parent.left == to_remove){
        to_remove.parent.left = joinLR(to_remove.left, to_remove.right);
      }
      else {
        to_remove.parent.right = joinLR(to_remove.left, to_remove.right);
      }
    }
  }

@Override
  public double getMeanSavings(){
    if (this.root == null) return 0.0f;
    int sum = sum(0,this.root);
    int N = this.root.N;
    return (sum/N);
  }

@Override
  public void printΤopLargeDepositors(int k){
    if (this.root == null) return;
    TopKTree k_tree = new TopKTree();
    add_to_new_tree(this.root, k_tree);
    k_tree.printTopK(k);
    return;
  }

@Override
  public void printByAFM(){
    if (this.root == null) return;
    printInOrder(this.root);
    return;
  }

//Helper methods

  //Rotates right and returns subtree at node n
  private TreeNode rotate_r(TreeNode n){

    TreeNode x = n.left;
    n.left = x.right;
    if (x.right != null){
      x.right.parent=n;
    }
    
    x.parent = n.parent;
    
    if(n.parent == null){
      this.root = x;
    }
    else if(n==n.parent.right){
      n.parent.right=x;
    }
    else {
      n.parent.left = x;
    }
    x.right = n;
    n.parent = x;
    return x;

  }

  //Rotates left and returns subtree at node n
  private TreeNode rotate_l(TreeNode n){
    TreeNode x = n.right;
    n.right = x.left;
    if (x.left != null){
      x.left.parent=n;
    }
    
    x.parent = n.parent;
    
    if(n.parent == null){
      this.root = x;
    }
    else if(n==n.parent.left){
      n.parent.left=x;
    }
    else {
      n.parent.right = x;
    }
    x.left = n;
    n.parent = x;
    return x;
  }

  //Recursive function for inserting element.
  private TreeNode insert_element(TreeNode root, LargeDepositor item){
    if (root == null) return new TreeNode(item);

    if (Math.random()*(root.N +1) < 1.0)
      return insertAsRoot(root, item);

    if (root.item.key() > item.key()){
      root.right = insert_element(root.right, item);

      if (root.right != null) root.right.parent = root;
    }

    else if (root.item.key() < item.key()){
      root.left = insert_element(root.left,item);
      if (root.left != null) root.left.parent = root;
    }

    else {
      System.out.println("The AFM given already exists in the tree.\n Now exiting..");
      System.exit(0);
    }
    int left_N = root.left == null ? 0 : root.left.N;
    int right_N = root.right == null ? 0 : root.right.N;
    root.N= left_N + right_N + 1;
    return root;
  }

  //Inserts as root, rotates accordingly
  private TreeNode insertAsRoot(TreeNode node, LargeDepositor item){
    if (node == null) return new TreeNode(item);

    if (node.item.key() > item.key()){
      node.right = insertAsRoot(node.right, item);
      if (node.right != null) node.right.parent = root;
      node = rotate_l(node);
    }
    else if (node.item.key() < item.key()){
      node.left = insertAsRoot(node.left, item);
      if (node.left != null) node.left.parent = root;
      node = rotate_r(node);
    }
    else {
      System.out.println("The AFM given already exists in the tree.\n Now exiting..");
      System.exit(0);
    }
    return node;
  }
  
  //Iterates all elements in tree to find last name. Inserts in List given as parameter
  private void tree_brush(List results,TreeNode current, String key){
    if(current == null) return;

    if(current.item.get_last_name().equals(key)){
      results.insert(current.item);
    }

    tree_brush(results, current.left, key);
    tree_brush(results, current.right, key);
    return;
  }

  //Finds and returns node with key AFM
  private TreeNode find_node_r(TreeNode node, int AFM){
    if(node == null) return null;
    if(node.item.key() == AFM) return node;
    else if(node.item.key() > AFM) return find_node_r(node.right, AFM);
    else return find_node_r(node.left, AFM);
  }

  //Joins Right and Left subtrees, Randomly picks parent but is weighted by the amount of elements in each subtree
  private TreeNode joinLR(TreeNode a, TreeNode b){
    if (a == null) return b;
    if (b == null) return a;
    int N = a.N + b.N;
    if (Math.random()*N < 1.0*a.N) {
      a.right = joinLR(a.right, b);
      a.right.parent = a;
      return a;
    }
    else {
      b.left = joinLR(a, b.left);;
      b.left.parent = b;
      return b;
    }
  }

  //Returns the sum of all node savings in tree 
  private int sum(int current_sum, TreeNode node){
    if (node == null) return current_sum;
    current_sum += node.item.get_savings();
    current_sum = sum(current_sum, node.left);
    current_sum= sum(current_sum, node.right);
    return current_sum;
  }

  //Adds all elements to TopKTree tree with key tax_score instead of AFM for finding the top ones
  private void add_to_new_tree(TreeNode node, TopKTree tree){
    if (node == null) return;
    tree.insert(node.item);
    add_to_new_tree(node.left, tree);
    add_to_new_tree(node.right, tree);
    return;
  }

  //Prints all elements in ascending key order
  private void printInOrder(TreeNode node) {
    if (node != null){
      printInOrder(node.right);
      node.item.print_item();
      printInOrder(node.left);
    }
  }
}


