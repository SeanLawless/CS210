package CS211;

import java.util.*;
import java.text.*;
/**
 * @Author Sean Lawless
 */


/**
 * D =  distance between two points
   r is the radius of the sphere = 6371km
   Maynooth Uni = 53.3798527, -6.596786500000007
   Ryugyong Hotel, Pyongyang, North Korea = 39.0366364,125.73091870000007
 */

public class DistanceCalculator
{
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");
        double endResult = distance(53.3798527,-6.596786500000007,39.0366364,125.73091870000007);
        System.out.println("Distance = " + formatter.format(endResult)+"km");
    }
    public static double distance(double startLat, double startLong, double endLat, double endLong){

        double radStartLat = Math.toRadians(startLat);
        double radEndLat = Math.toRadians(endLat);

        double lat2lat1 = Math.toRadians((endLat-startLat)/2);
        double long2long1 = Math.toRadians((endLong-startLong)/2);

        double distance = 2*6371*Math.asin(Math.sqrt((Math.pow(Math.sin(lat2lat1),2)+Math.cos(radStartLat)*Math.cos(radEndLat)
        *Math.pow(Math.sin(long2long1),2))));


        return distance;
    }

}
