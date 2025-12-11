import java.util.Scanner;

public class Labman {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input Data Stream
        System.out.print("Enter message bits: ");
        String message = sc.nextLine();

        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        int data[] = new int[message.length() + generator.length() - 1];
        int divisor[] = new int[generator.length()];

        // Copy message bits into data[]
        for (int i = 0; i < message.length(); i++)
            data[i] = message.charAt(i) - '0';

        // Append zeros for CRC (already 0 by default)

        // Copy generator into divisor[]
        for (int i = 0; i < generator.length(); i++)
            divisor[i] = generator.charAt(i) - '0';

        // CRC Calculation
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1) {
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
            }
        }

        // Create final codeword: original message + remainder
        int codeword[] = new int[message.length() + generator.length() - 1];

        // Copy original message
        for (int i = 0; i < message.length(); i++)
            codeword[i] = message.charAt(i) - '0';

        // Copy remainder from data[]
        for (int i = message.length(); i < codeword.length; i++)
            codeword[i] = data[i];

        // Print checksum/codeword
        System.out.print("The checksum code is: ");
        for (int i = 0; i < codeword.length; i++)
            System.out.print(codeword[i]);
        System.out.println();


        // ---------------- Receiver Side -----------------

        System.out.print("Enter checksum code: ");
        message = sc.nextLine();

        System.out.print("Enter generator: ");
        generator = sc.nextLine();

        data = new int[message.length() + generator.length() - 1];
        divisor = new int[generator.length()];

        // Copy received message to data[]
        for (int i = 0; i < message.length(); i++)
            data[i] = message.charAt(i) - '0';

        // Copy generator
        for (int i = 0; i < generator.length(); i++)
            divisor[i] = generator.charAt(i) - '0';

        // CRC Remainder Calculation
        for (int i = 0; i < message.length(); i++) {
            if (data[i] == 1)
                for (int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        // Check if remainder is all zeros
        boolean valid = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                valid = false;
                break;
            }
        }

        if (valid)
            System.out.println("Data stream is valid");
        else
            System.out.println("Data stream is invalid. CRC error occurred.");
    }
}
