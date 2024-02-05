package tax_evasion_project;

class TopKTree{
  private class TreeNode {
    LargeDepositor item;
    TreeNode left;
    TreeNode right;


    public TreeNode(LargeDepositor item){
      this.item = item;
      this.left = null;
      this.right = null;
    }
    
  };
  int k;
  private TreeNode root;

  public void insert (LargeDepositor item){
    root = insertNew(this.root, item);
  }

  private TreeNode insertNew(TreeNode node, LargeDepositor item){
    if (node == null){
      node = new TreeNode(item);
      return node;
    }

    if (item.get_score() > node.item.get_score()){
      node.right = insertNew(node.right, item);
    }
    else{ 
      node.left = insertNew(node.left,item);
    }
    return node;
  }

  public void printTopK(int k){
    if (this.root == null || k == 0) return;
    this.k = k;
    printInOrder(this.root);
    return;
  }

  private void printInOrder(TreeNode node) {
    if (node == null) return;
    printInOrder(node.right);
    if (this.k > 0){
    node.item.print_item();
      this.k --;
    }
    printInOrder(node.left);
  }
  
}

