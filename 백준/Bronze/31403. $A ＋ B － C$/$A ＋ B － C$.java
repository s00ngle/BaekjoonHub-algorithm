import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A, B, C;

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();

        System.out.println(A + B - C);
        String s = "";
        s += A;
        s += B;
        System.out.println(Integer.parseInt(s) - C);
    }
}