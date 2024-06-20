/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medcine;

/**
 *
 * @author haris
 */
public class M_BandType {
    public final String medicine_name;
    public  boolean morning_status;
    public  boolean afternoon_status;
    public  boolean evening_status;
    public boolean before,after,delete_chk;
    public String selected_combo ;
    public String tab,selected_index;
    
  
   public M_BandType(String medicine_name, boolean morning_status,boolean afternoon_status,boolean evening_status,boolean before,boolean after, String tab , String selected_combo,boolean delete_chk) {
    this.medicine_name = medicine_name;
    this.morning_status = morning_status;
    this.afternoon_status = afternoon_status;
    this.evening_status = evening_status;
    this.before = before;
    this.after = after;
    this.tab = tab;
    this.selected_combo = selected_combo;
   // this.selected_index = selected_index;
    this.delete_chk = delete_chk;
  }
  public void showDetails()
  {
       System.out.println(this.medicine_name);
       System.out.println(this.morning_status);
       System.out.println(this.afternoon_status);
       System.out.println(this.evening_status);
       System.out.println(this.before);
       System.out.println(this.after);
       System.out.println(this.tab);
       System.out.println(this.selected_combo);
  }
}
