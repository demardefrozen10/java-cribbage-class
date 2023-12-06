import java.lang.Math; // import math for power
public class PowerSet<T> { //Power set class of generic type t
    Set<T>[] set; // array set of type T
    public PowerSet(T[] elements) {
        String X = "";
        double powerSet = Math.pow(2, elements.length); //there are 2^n power sets, or in particular length of elements
        set = new Set[(int)powerSet]; // create new set to store the subsets
        for (int i = 0; i < powerSet; i++) { // loop through 2^n
            X = Integer.toBinaryString(i); // convert to binary
            while (X.length() != elements.length) { // if length is not same as element length
                X = "0" + X; // pad the front with zeros
            }
            Set<T> sSet = new Set<T>(); // create new set
            for (int z = 0; z < X.length(); z++) { // loop through binary string
                if (X.charAt(z) == '1') { // see if the characters are equal to one
                  sSet.add(elements[z]); // if yes add that element
                }
            }
            set[i] = sSet; // add subset to set
        }
        }
        public int getLength() {
            return set.length; // return length of set
        }

    public Set<T> getSet(int i) {
        return set[i]; //return set at index I
    }
}