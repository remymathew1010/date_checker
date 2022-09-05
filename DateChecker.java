import java.io.*;
import java.util.Scanner;

public class DateChecker {

    static int MAX_VALID_YR = 3000;              // Max Valid Year
    static int MIN_VALID_YR = 1753;              // Min Valid Year

    static boolean isLeap(int year)              // Function to check for valid leap year
    {
        // Return true if year is
        // a multiple of 4 and not
        // multiple of 100.
        // OR year is multiple of 400.
        return (((year % 4 == 0) &&
                (year % 100 != 0)) ||
                (year % 400 == 0));
    }

    static boolean isValidDate(int d,
                               int m,
                               int y)
    {
        // If year, month and day
        // are not in given range
        if (y > MAX_VALID_YR ||
                y < MIN_VALID_YR){
            System.out.println("Year Out Of Range");
            return false;
        }
        if (m < 1 || m > 12) {
            System.out.println("Month Out Of Range");
            return false;
        }
        if (d < 1 || d > 31) {
            System.out.println("Days out of range");
            return false;
        }
        // Handle February month
        // with leap year
        if (m == 2)
        {
            if (isLeap(y))
                if (d <= 29){
                    return true;
                } else {
                    System.out.println("February Month Days are out of range for a Leap Year.");
                    return false;
                }
            else
                if (d <= 28){
                    return true;
                } else {
                    System.out.println("February Month Days are out of range for a Leap Year.");
                    return false;
                }
        }

        // Months of April, June,
        // Sept and Nov must have
        // number of days less than
        // or equal to 30.
        if (m == 4 || m == 6 ||
                m == 9 || m == 11)
            if (d <= 30){
                return true;
            } else {
                System.out.println("Days are out of range for the given month");
                return false;
            }

        return true;
    }

    // Driver code
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean monthWrongFormat = true, daysWrongFormat = true, yearWrongFormat = true;       // Boolean values to keep track of valid format
        String Line = scanner.nextLine();
        String[] data = new String[3];
        if (Line.contains("-") && !(Line.contains("/")||Line.contains(" "))){                  // If date consists of only "-" we'll separate it by "-"

            if(Line.split("-").length!=3)
            {
                System.out.println("INVALID: There must be three (3) arguments in Date (dd-mm-yy) but you provided "+Line.split("-").length+" arguments");
                System.exit(0);
            }
            data = Line.split("-");
        }else if (Line.contains("/") && !(Line.contains("-")||Line.contains(" "))){               // If date consists of only "/" we'll separate it by "/"
            if(Line.split("/").length!=3)
            {
                System.out.println("INVALID: There must be three (3) arguments in Date (dd/mm/yy) but you provided "+Line.split("/").length+" arguments");
                System.exit(0);
            }
            data = Line.split("/");
        }else if (Line.contains(" ") && !(Line.contains("/")||Line.contains("-"))){             // If date consists of only " " we'll separate it by " "

            if(Line.trim().split(" ").length!=3)
            {
                System.out.println("INVALID: There must be three (3) arguments in Date (dd mm yy) but you provided "+Line.split(" ").length+" arguments");
                System.exit(0);
            }
            data = Line.split(" ");
        }

        else {                                     // Any Other Format is Invalid



            if(Line.contains("-") && Line.split("-").length!=3)
            {
                System.out.println("Invalid Format: "+Line+"\nThere must be exactly two dashs (-) and no other separator");

            }
            else  if(Line.contains("/") && Line.split("/").length!=3)
            {
                System.out.println("Invalid Format: "+Line+"\nThere must be exactly two Slashes (/) and no other separator");

            }
            else  if(Line.contains(" ") && Line.split(" ").length!=3)
            {
                System.out.println("Invalid Format: "+Line+"\nThere must be a space after day and a space after month and no other separator");

            }

            else
            System.out.println("INVALID FORMAT");



            if(Line.contains(" ") && (Line.contains("-") || Line.contains("/")))
            {

                System.out.println("There must be only one separator from (dash,slash or space)");
            }
            else if(Line.contains("-") && (Line.contains(" ") || Line.contains("/")))
            {

                System.out.println("There must be only one separator from (dash,slash or space)");
            }
            else
            if(Line.contains("/") && (Line.contains("-") || Line.contains(" ")))
            {

                System.out.println("There must be only one separator from (dash,slash or space)");
            }



            System.exit(0);
        }
        String day = data[0];
        String month = data[1];
        String year = data[2];
        int days = 0;
        try {
            days = Integer.parseInt(day);              // Check if days can be converted into integer
        }catch (IllegalArgumentException e){
            daysWrongFormat = false;                  // If not Days format are wrong
        }
        int mon = 0;
        int years = 0;
        try {
            mon = Integer.parseInt(month);        // Checking if Months can be converted to Integer value
        }catch (IllegalArgumentException e){
            switch (month){                        // If not we'll then convert the given month into specific integer month
                case "jan", "Jan", "JAN" -> mon = 1;
                case "feb", "Feb", "FEB" -> mon = 2;
                case "mar", "Mar", "MAR" -> mon = 3;
                case "apr", "Apr", "APR" -> mon = 4;
                case "may", "May", "MAY" -> mon = 5;
                case "jun", "Jun", "JUN" -> mon = 6;
                case "jul", "Jul", "JUL" -> mon = 7;
                case "aug", "Aug", "AUG" -> mon = 8;
                case "sep", "Sep", "SEP" -> mon = 9;
                case "oct", "Oct", "OCT" -> mon = 10;
                case "nov", "Nov", "NOV" -> mon = 11;
                case "dec", "Dec", "DEC" -> mon = 12;
                default -> monthWrongFormat = false;              // If neither format applies then Month Format is invalid

            }
        }
        try {                                              // Checking if Year can be converted to Integer value
            if (year.length() == 4) {                     // If year length is 4
                years = Integer.parseInt(year);
            } else if (year.length() == 2) {              // If year length is 2 we'll check if it lies from 1950 to 1999 range or 2000 to 2049 range
                int checkYear = Integer.parseInt(year);
                if (checkYear <= 49 && checkYear >= 0) {
                    years = 2000 + checkYear;
                } else {
                    years = 1900 + checkYear;
                }
            }else {
                yearWrongFormat = false;                     // If length is neither 2 nor 4 then format is invalid
            }
        }catch (IllegalArgumentException e){
            yearWrongFormat = false;
        }
        if (daysWrongFormat && monthWrongFormat && yearWrongFormat) {        // If all formats are valid
            if (isValidDate(days, mon, years)){
                String strMon=getMonth(mon);
                if(strMon!=null)
                System.out.println(days+" "+strMon+" "+years);
                else
                {
                    System.out.println("INVALID value for Month "+mon+"\n");

                }
            }
        }else {                                                              // If any of the format is invalid
            System.out.println("INVALID: Date Format is not valid\n");
        }
    }

    public static String getMonth(int month)
    {
        switch (month)
        {
            case 1:
                return "Jan";
            case 2:
                return "Feb";

            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "July";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return null;
        }
    }
}