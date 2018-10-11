import java.util.*;
import java.math.*;

public class Cryptography {
    public static void main (String args[]){

        Scanner scan = new Scanner(System.in);
        String alice = scan.nextLine();
        String bob = scan.nextLine();
        String[] aliceSplit = alice.split(" ");
        String[] bobSplit = bob.split(" ");
        long p = Long.parseLong(aliceSplit[0]), g = Long.parseLong(aliceSplit[1]), gXModP = Long.parseLong(aliceSplit[2]);
        long gY = Long.parseLong(bobSplit[0]), mGXY = Long.parseLong(bobSplit[1]), x = 0L;

        for(long i =0;;i++){
            if(modPow(g,i,p)==gXModP){
                x = i;
                break;
            }
        }

        long p1X = p - 1 -x;

        long divisor = modPow(gY,p1X,p);
        long answer = modMult(divisor,mGXY,p);
        System.out.println(answer);
        
        
       /* BigInteger gYBig = new BigInteger(Long.toString(gY));
        BigInteger mGXYBig = new BigInteger(Long.toString(mGXY));
        BigInteger pBig = new BigInteger(Long.toString(p));
        BigInteger gBig = new BigInteger(Long.toString(g));
        BigInteger gxModPBig = new BigInteger(Long.toString(gXModP));
        BigInteger xBig = new BigInteger(Long.toString(x));

        BigInteger power = pBig;
        power = power.subtract(new BigInteger("1"));
        System.out.println(power); // output 9858
        power = power.subtract(xBig);
        //System.out.println(power); // output 9774
        gYBig = gYBig.modPow(power,pBig);
        gYBig = gYBig.multiply(mGXYBig);
        gYBig = gYBig.mod(pBig);
        System.out.println(pBig);
        */



    }

    public static long modPow(long number, long power, long modulus){
//raises a number to a power with the given modulus
//when raising a number to a power, the number quickly becomes too large to handle
//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
//however you want the algorithm to work quickly - having a multiplication loop would result in an O(n) algorithm!
//the trick is to use recursion - keep breaking the problem down into smaller pieces and use the modMult method to join them back together
        if(power==0)
            return 1;
        else if (power%2==0) {
            long halfpower=modPow(number, power/2, modulus);
            return modMult(halfpower,halfpower,modulus);
        }else{
            long halfpower=modPow(number, power/2, modulus);
            long firstbit = modMult(halfpower,halfpower,modulus);
            return modMult(firstbit,number,modulus);
        }
    }

    public static long modMult(long first, long second, long modulus){
//multiplies the first number by the second number with the given modulus
//a long can have a maximum of 19 digits. Therefore, if you're multiplying two ten digits numbers the usual way, things will go wrong
//you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
//however you want the algorithm to work quickly - having an addition loop would result in an O(n) algorithm!
//the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually
        if(second==0)
            return 0;
        else if (second%2==0) {
            long half=modMult(first, second/2, modulus);
            return (half+half)%modulus;
        }else{
            long half=modMult(first, second/2, modulus);
            return (half+half+first)%modulus;
        }
    }
}