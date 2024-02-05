package tax_evasion_project;

class LargeDepositor {
  private int AFM; 
  private String first_name; 
  private String last_name;
  private double savings; 
  private double taxed_income; 


  public LargeDepositor(int AFM, String first_name, String last_name, double savings, double taxed_income){
    this.AFM = AFM;
    this.first_name = first_name;
    this.last_name = last_name;
    this.savings = savings;
    this.taxed_income = taxed_income;
  }


  public void print_item(){
    System.out.println(
      "AFM : " + this.AFM +
      " Name : " + this.first_name + 
      " " + this.last_name + 
      " Savings : " + this.savings + 
      " Taxed Income : " + this.taxed_income
    );
  }

  public int key() {return AFM;} 
  public String get_last_name(){return this.last_name;}
  public double get_savings(){return this.savings;}

  public void set_savings(double savings){ this.savings = savings;}
  public double get_score(){
    if (this.taxed_income > 8000){
      return Double.MAX_VALUE;
    }
    else{
      return (this.savings - this.taxed_income);
    }
  }


}