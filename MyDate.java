import java.util.*;

/** En immutable klass som representerar datum.
    OBS: Det finns buggar i denna kod. */
public class MyDate {

    private int y;
    private int m;
    private int d;

    /** Skapar ett nytt MyDate objekt för dagen: year, month, day.
        Till exempel: datumet 2029-07-15 skapas med new MyDate(2029,7,15).
        @throws Kastar ett exception ifall datumet inte är ett giltigt datum (t.ex. 2003-02-29).*/
    public MyDate(int year, int month, int day) {
        // Se till att all datum ligger inom rimliga intervall
        // year must be positive
        // month must in the intervall [1, 12]
        // days must check what month it is to check if it falls in the intervall [1, daysInMonth(month, year)]
        if(year >= 1 && (month >= 1 && month <= 12) && (day >= 1 && day <= daysInMonth(month, year))) {
            y = year;
            m = month;
            d = day;
        }
        else {
            throw new IllegalArgumentException("Invalid date parameters");
        }
          
    }

    /**
     * Returnerar detta datums år.
     * @return det år detta datum infaller på
    */
    public int getYear() { return y; }

    /**
     * Returnerar detta datums månad.
     * @return den månad detta datum infaller under
    */
    public int getMonth() { return m; }

    /**
     * Returnerar detta datums dag.
     * @return vilken dag i månaden detta datum infaller på
    */
    public int getDay() { return d; }

    /**
     * Konverterar datum till sträng.
     * @return en strängrepresentation av detta datum */
    public String toString() {
        String res = y + "-";
        if (m < 10) { res = res + "0"; };
        res = res + m + "-";
        if (d < 10) { res = res + "0"; };
        res = res + d;
        return res;
    }

    /** Likhetsjämförelse för datum
     *  @param other objekt att jämföra med
     *  @return true ifall detta objekt representerar samma datum */
    public boolean equals(Object other) {
        if (other == null) { return false; }
        if (this.getClass() != other.getClass()) { return false; }
        if(super.equals(other)) { return true; }
        
        MyDate x = (MyDate)other;
        return this.compareTo(x) == 0 ? true : false;
    }

    /** Jämför två datum.
        @param other datum att jämföra med
        @return -1 ifall this är ett tidigare datum än other, 1 ifall other är ett tidigare datum än this, och 0 ifall this och other representerar samma datum. */
    public int compareTo(MyDate other) {
        if(this.y < other.y) {
            return -1;
        }
        else if(this.y == other.y) {
            if(this.m < other.m) {
                return -1;
            }
            else if(this.m == other.m) {
                if(this.d < other.d) {
                    return -1;
                }
                else if(this.d == other.d) {
                    return 0;
                }
                else {
                    return 1;
                }
            }
            else {
                return 1;
            }
        }
        else {
            return 1;
        }
    }

    /**
     * Beräknar om ett tal är ett skottår.
     * @param year heltal som representerar ett år
     * @return true om year är ett skottår
     */
    private static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Returnerar hur många dagar det finns i en given månad.
     * @param m heltal som representerar en månad.
     * @param y heltal som representerar vilket år månaden infaller under.
     * @return antalet dagar i månad m om den infaller under år y.
     */
    private static int daysInMonth(int m, int y) {
        switch (m) {
          case 4: // apr
          case 6: // jun
          case 9: // sep
          case 11: // nov
              return 30;
          case 2: // feb
              if (isLeapYear(y)) {
                  return 29;
              } else {
                  return 28;
              }
          default: // resten
              return 31;
        }
    }

    /**
     * Beräknar nästa datum
     * @return ett datum som representerar dagen efter detta datum
     */
    public MyDate next() {
        if ((m == 12) && (d == 31)) {
            return new MyDate(y+1,1,1);
        }
        if (d == daysInMonth(m,y)) {
            return new MyDate(y,m+1,1);
        }
        return new MyDate(y,m,d+1);
    }

    /** Skapar en iterator som innehåller upprepningarna av detta datum med
        en veckas mellanrum. Det första datumet är samma som this; följande är
        7 dagar senare; och därefter 14 dagar senare, osv. Antalet datum i
        iterator bör vara samma som det givna repetitionCount.
        @param repetitionCount antal datum
        @return en iterator som beter sig som ovan
        @throws IllegalArgumentException om repetitionCount < 0
     */
    public Iterator<MyDate> repeatWeekly(int repetitionCount) {
        if(repetitionCount < 0)
            throw new IllegalArgumentException("repetitionCount must be positive value!");
        
        LinkedList<MyDate> reps = new LinkedList<>();
        MyDate date = new MyDate(this.y, this.m, this.d);
        for(int i = 1; i <= repetitionCount; ++i) {
            reps.add(date);
            for(int j = 1; j <= 7; ++j) {
                date = date.next();
            }
        }

        return reps.iterator();
    }
}
