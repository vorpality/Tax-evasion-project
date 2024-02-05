package tax_evasion_project;

public class List{

  private class Node{
    LargeDepositor item;
    Node next;

    public Node(LargeDepositor item){
      this.item = item;
      this.next = null;
    }
  }
    Node head;
    int size;
  public List() {
    this.head = null;
    this.size = 0;
  } 

  public int size(){ return this.size;}

  public void insert(LargeDepositor item){
    Node n = new Node(item);
    if (this.head == null) {
      this.head=n;
      this.head.next = null;
      this.size++;
    }
    else {
      n.next = this.head;
      this.head = n;
      this.size++;;
    }
    return;
  }

  public void print_items(){
    Node curr = this.head;
    while (curr != null){
      curr.item.print_item();
      curr=curr.next;
    }
    return;
  }
}


