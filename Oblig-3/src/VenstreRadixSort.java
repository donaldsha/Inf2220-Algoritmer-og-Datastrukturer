public class VenstreRadixSort {

    final static int NUM_BIT =8; // eller: 6-13 er kanskje best.. finn selv ut hvilken verdi som er raskest, min funker best når den er 8
    final static int MIN_NUM = 31; // mellom 16 og 60, kvikksort bruker 47 //jeg tar 31 siden det er best for int og vi vet at int lengde er 2^32(32 bit langt)

    private int max;
    private int numBit; //= String.valueOf(max).length();
    int numbits;

    VenstreRadixSort(){}//create an empty constructor

    public int findMax(int[] a) {//finner max verdi, metode tatt av den gamle losningen min
        max = -1;
        for (int num : a) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }

    public int numbitValue(int num){//metode for å finne hvor mange bits langt er det største tallet.
        numbits = 1;
        while (num >= (1<< numbits)) numbits++;
        return numbits;
    }

    public double VRadixMulti(int [] a) {
        long tt = System.nanoTime();
        int [] b = new int [a.length];
        // a) finn ‘max’ verdi i a[]
        max = findMax(a);
        System.out.println("MAX verdien i Arrayen: " + max);
        // b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
        numBit = numbitValue(max);//returnerer bits lengde til det største tallet

        System.out.println("NumBit value i forhold til MAX: " + numBit);
        // int maskLen = Math.min(numBit, NUM_BIT);//får ut det tallet som er størst av de to.
        // c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer
        //VenstreRadix(a, b, 0, a.lenght, numBit, )

        if (numBit < NUM_BIT) {
        	VenstreRadix(a, b, 0, a.length, numBit, numBit);
        }else{
        	VenstreRadix(a, b, 0, a.length, numBit, NUM_BIT);
        }

        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
    } // end VRadixMulti

    /*
    * Her bruker jeg det samme algoritme jeg brukte paa den forste forsoket 
    * men har bare adaptert til bitshift og passer at den oppfyller alle de kravene 
    * nevnt paa obligen
    */
    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix (int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
        int mask = (1<<maskLen)-1;
        int[] count = new int [mask+1];
        int[] newArr = new int [mask+1];
        int plaser = left;//for a plasere tallene paa venstre
        int shift = 0;

        if (leftSortBit > maskLen) {
        	shift = leftSortBit - maskLen;//initialiserer shift som blir brukt etterpaa
        }

        // d) count[] =hvor mange det er med de ulike verdiene
        // av dette sifret i a [left..right]
        for (int i = left; i< right;i++) {
        	int curr = mask & (a[i]>>shift);//starter fra venstre og shifter til hoyre
        	count[curr]++;
        }

        // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal
        // flytte første element med verdien ‘i’ vi sorterer.
        for (int i = 0;i<count.length;i++) {
        	newArr[i] = plaser;//setter forste elem helt til venstre 
        	plaser += count[i];
        }
        // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for
        //alle de ulike verdiene for dette sifferet
        for (int i = left; i<right;i++) {
        	int curr = mask & (a[i]>>shift);
        	b[newArr[curr]++] = a[i];
        }

        // g) Kall enten innstikkSort eller rekursivt VenstreRadix
        // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
        // Vurder når vi. skal kopiere tilbake b[] til a[] ??
        leftSortBit -= maskLen;
        /*
        * etter at det stoerste delen av arrayen har blitt sortert
        * bruker jeg insertSort(innstikkSort) for aa sortere arrayen med smaa 
        * storrelse siden den var mest effektivt paa slike tilfeller ellers bruker jeg 
        * rekursiv VenstreRadix.
        */
        for (int i = 0;i<count.length;i++) {
        	if (count[i]>MIN_NUM && shift > 0) {
        		VenstreRadix(b, a, (newArr[i] - count[i]), newArr[i], leftSortBit, maskLen);
        	}else if (shift > 0) {
        		insertSort(b, (newArr[i] - count[i]), newArr[i]);
        	}
        }

        for (int i = left; i < right ;i++ ) {
        	a[i] = b[i];//kopierer over/tilbake fra b til a
        }


    }// end VenstreRadix

    public void insertSort(int[] a, int l, int r){//insertSort eller innstikkSort
    	int ikSort, cnt;
    	for (int i = l; i< r-1 ;i++ ) {
    		ikSort = a[i+1];
    		cnt = i+1;

    		while (cnt > l && ikSort < a[cnt-1]) {
    			a[cnt] = a[cnt-1];
    			cnt--;
    		}
    		a[cnt] = ikSort;
    	}
    }

    void testSort(int [] a){
        for (int i = 0; i< a.length-1;i++) {
            if (a[i] > a[i+1]){
                System.out.println("SorteringsFEIL på: "+
                        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
                return;
            } }
    }// end testSort
}
