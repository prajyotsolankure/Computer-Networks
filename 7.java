import java.util.Scanner;

public class LeakyBucket {

    public static void main(String[] args) {

        int i;
        int a[] = new int[20];
        int buck_rem = 0, buck_cap = 4, rate = 3, sent, recv;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of packets: ");
        int n = in.nextInt();

        System.out.println("Enter the packet sizes:");
        for (i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        System.out.println("\nClock\tPacketSize\tAccepted\tSent\tRemaining");

        for (i = 1; i <= n; i++) {

            // Receiving packets
            if (a[i] != 0) {
                if (buck_rem + a[i] > buck_cap) {
                    recv = -1;  // packet dropped
                } else {
                    recv = a[i];  // packet accepted
                    buck_rem += a[i];
                }
            } else {
                recv = 0; // no packet
            }

            // Sending packets at fixed rate
            if (buck_rem != 0) {
                if (buck_rem < rate) {
                    sent = buck_rem;
                    buck_rem = 0;
                } else {
                    sent = rate;
                    buck_rem -= rate;
                }
            } else {
                sent = 0;
            }

            // Output
            if (recv == -1) {
                System.out.println(i + "\t\t" + a[i] + "\tDropped\t\t" + sent + "\t" + buck_rem);
            } else {
                System.out.println(i + "\t\t" + a[i] + "\t\t" + recv + "\t\t" + sent + "\t" + buck_rem);
            }
        }

        in.close();
    }
}
