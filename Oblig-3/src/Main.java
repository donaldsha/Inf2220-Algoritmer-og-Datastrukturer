import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    private static double[] runTimes;//brukes for å lagre runtiden for VenstreRadixen min
    
    public static void main(String[] args) {
        
        
        for (int i = 1;i<=7 ;i++ ) {
        double radixTime = 0, arraysTime = 0, tidDiferanse = 0;

                runTimes = new double[5];//siden vi skal ha bare 5 kjoringer. 
            for (int x = 0;x<runTimes.length ;x++) {//for aa kjore 5 ganger og faa medianen.
                
                VenstreRadixSort vRadix = new VenstreRadixSort();
                //10^i siden i gaar opp til 7 sa kommer vi til aa faa 10 millioner tall 
                int arrStr = (int) Math.pow(10, i);
                //usorterte arrayer som inneholder de samme random tallene som skal sorteres
                int[] usortA = new int[arrStr];
                int[] usortB = new int[arrStr];

                Random random = new Random();
                //fyller opp arrayene med random tall(samme random tall)
                for (int j = 0;j<usortA.length;j++ ) {
                    usortA[j] = random.nextInt(arrStr);
                    usortB[j] = usortA[j];//usortB == usortA slik at algoritmene sorterer samme tall.
                }

                radixTime = vRadix.VRadixMulti(usortA);//får tiden det tar for venstre radix å sortere tallene

                long start = System.nanoTime();
                Arrays.sort(usortB);
                arraysTime = (System.nanoTime() - start)/1000000.0;

                tidDiferanse = arraysTime / radixTime;//
                

                runTimes[x] = radixTime;

            }
            
            //System.out.println("Radix time" + radixTime);
            double medianTidRadix = runTimes[runTimes.length/2];//tar medianen til Radix tiden 

            System.out.println("Tid for arrayen med lengde 10^" + i + " random tall");
            System.out.println("\tVenstre Radix:\t|\tKvikkSort:");
            System.out.println("\t" + medianTidRadix + "\t|\t" + arraysTime);
            System.out.println("\nDifferanse mellom de to tidene er: " + tidDiferanse + "\n");
        }
    }
}
