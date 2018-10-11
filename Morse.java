package CS211;
/**
 * @author Sean Lawless
 * @date 4/4/2018
 *
 */

import javax.sound.sampled.*;
import java.util.*;

public class Morse {
    public static void main (String args[]) throws LineUnavailableException, InterruptedException {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your message to convert to morse code.");
        String message = scan.nextLine();
        scan.close();


        //Mapping for english alphabet to morse code.
        HashMap<Character,String> morseAlphabet = new HashMap<Character, String>();
            morseAlphabet.put('a',".-");
            morseAlphabet.put('b',"-...");
            morseAlphabet.put('c',"-.-.");
            morseAlphabet.put('d',"-..");
            morseAlphabet.put('e',".");
            morseAlphabet.put('f',"..-.");
            morseAlphabet.put('g',"--.");
            morseAlphabet.put('h',"....");
            morseAlphabet.put('i',"..");
            morseAlphabet.put('j',".---");
            morseAlphabet.put('k',"-.-");
            morseAlphabet.put('l',".-..");
            morseAlphabet.put('m',"--");
            morseAlphabet.put('n',"-.");
            morseAlphabet.put('o',"---");
            morseAlphabet.put('p',".--.");
            morseAlphabet.put('q',"--.-");
            morseAlphabet.put('r',".-.");
            morseAlphabet.put('s',"...");
            morseAlphabet.put('t',"-");
            morseAlphabet.put('u',"..-");
            morseAlphabet.put('v',"...-");
            morseAlphabet.put('w',".--");
            morseAlphabet.put('x',"-..-");
            morseAlphabet.put('y',"-.--");
            morseAlphabet.put('z',"--..");
            morseAlphabet.put(' '," ");

            morseAlphabet.put('1',".----");
            morseAlphabet.put('2',"..---");
            morseAlphabet.put('3',"...--");
            morseAlphabet.put('4',"....-");
            morseAlphabet.put('5',".....");
            morseAlphabet.put('6',"-....");
            morseAlphabet.put('7',"--...");
            morseAlphabet.put('8',"---..");
            morseAlphabet.put('9',"----.");
            morseAlphabet.put('0',"-----");

        int dashLength = 3, dotLength = 1, letterSpace = 3, wordSpace = 7; //Spacing for all the units in morse code.

        String morseArray[] = new String[message.length()];
        message = message.toLowerCase(); //convert string to lower case for ease of use.
        //System.out.println(message);
        char[] array = message.toCharArray(); // convert to a char array which will allow the program to single out a letter.
        for(int i = 0; i < array.length; i ++){
            if(morseAlphabet.containsKey(array[i])){
                System.out.print(morseAlphabet.get(array[i]) + "   "); //Prints morse code for the specified character
                morseArray[i] = morseAlphabet.get(array[i]); // Fills morseArray with the morse code for the message
            }
        }

        for(int i = 0; i < morseArray.length; i++){ //Runs through the morse array.
            for(int j = 0; j < morseArray[i].length(); j++){ //
                if(morseArray[i].charAt(j) == '.'){ //if . plays tone.
                    tone(600, 80 * dotLength , 0.5);
                }
                else if(morseArray[i].charAt(j) == '-'){ // if - plays tone.
                    tone(600, 80 * dashLength ,0.5);
                }else if(morseArray[i].charAt(j) == ' '){
                    Thread.sleep(80 * wordSpace);
                }
                Thread.sleep(80 * letterSpace);
            }
        }

    }



    public static void tone(int hz, int msecs, double vol) throws LineUnavailableException{

        float SAMPLE_RATE = 8000f;
        byte[] buf = new byte[1];
        AudioFormat af = new AudioFormat(SAMPLE_RATE,8,1,true,false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for(int i = 0; i < msecs*8; i++){
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }
}
