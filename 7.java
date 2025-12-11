import java.util.*;

public class LeakyBucket {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int bucketCap = 4;   // bucket capacity
        int rate = 3;        // sending rate
        int bucket = 0;      // current bucket content

        System.out.print("Enter number of packets: ");
        int n = sc.nextInt();

        int packets[] = new int[n];

        System.out.println("Enter packet sizes:");
        for (int i = 0; i < n; i++) {
            packets[i] = sc.nextInt();
        }

        System.out.println("\nTime\tSize\tAccepted/Dropped\tSent\tLeft");

        for (int i = 0; i < n; i++) {

            int recv, sent;

            // check if packet fits in bucket
            if (packets[i] + bucket > bucketCap) {
                recv = -1; // drop
            } else {
                recv = packets[i];
                bucket += packets[i];
            }

            // send packets at fixed rate
            sent = Math.min(bucket, rate);
            bucket -= sent;

            // print result
            if (recv == -1)
                System.out.println((i+1) + "\t" + packets[i] + "\tDropped\t\t" + sent + "\t" + bucket);
            else
                System.out.println((i+1) + "\t" + packets[i] + "\t" + recv + "\t\t\t" + sent + "\t" + bucket);
        }
        sc.close();
    }
}
