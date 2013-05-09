
package tests;


public class FormatNumberTest {

 
    public static void main(String[] args) {
        String s = "0000000000000000000000000000000000010000000100000001000000010000";
//         String s = "10000000100000001000000010000";
            Long r=     Long.parseLong(s, 2);
      
        System.out.println(r);
        String rr = Long.toBinaryString(r);
          System.out.println(rr);
        
        
    }
}
