import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        String str = null;
        while (!StdIn.isEmpty()){
            str = StdIn.readString();
            randomizedQueue.enqueue(str);
        }

        int i = 0;
        for(String s: randomizedQueue){
            if (i < k){
                System.out.println(s);
                ++i;
            }else{
                break;
            }
        }

    }


}
