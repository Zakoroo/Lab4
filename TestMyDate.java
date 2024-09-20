import java.util.*;

public class TestMyDate {

    public static void main(String[] args) {

        MyDate d1, d2;
        boolean testOK = true;

        
        
        // test 1: försöker hitta ett fel i konstruktorn
        
        // skriv din lösning här
        // Lösning: Konstruktorm kollar inte om talen är mindre eller lika med noll (fel 1).
        try {
            d1 = new MyDate(2025, 0, -2);
            d2 = new MyDate(2024, 13, -1);
            testOK = false;
        } catch (IllegalArgumentException e) {
            testOK = true;
        }
        

        if (testOK) {
            System.out.println("Test 1 OK.");
        } else {
            System.out.println("Test 1 failed.");
        }

        // test 2: försöker hitta ett fel i compareTo

        // skriv din lösning här
        // Lösning: Om d1.y > d2.y och d1.d < d2.d då kommer vi få -1, dvs. d1 < d2, vilket är inte sant (fel 1).
        try {
            testOK = true;
            d1 = new MyDate(2025, 1, 1);
            d2 = new MyDate(2024, 1, 2);

            if(d1.compareTo(d2) == -1)
                throw new IllegalArgumentException("d1 is greater than d2");
        } catch (IllegalArgumentException e) {
            testOK = false;
        }

        if (testOK) {
            System.out.println("Test 2 OK.");
        } else {
            System.out.println("Test 2 failed.");
        }

        // test 3: försöker hitta ett fel i equals

        // skriv din lösning här
        // Om vi får två datum object med samma värden vi kommer få false, vilket inte kan vara sant (fel 1)
        try {
            testOK = true;
            d1 = new MyDate(2024, 9, 20);
            d2 = new MyDate(d1.getYear(), d1.getMonth(), d1.getDay());

            if(d1.equals(d2) == false)
                throw new IllegalArgumentException("d1 and d2 are equal");
        } catch (IllegalArgumentException e) {
            testOK = false;
        }
        
    
        if (testOK) {
            System.out.println("Test 3 OK.");
        } else {
            System.out.println("Test 3 failed.");
        }

    }

}
