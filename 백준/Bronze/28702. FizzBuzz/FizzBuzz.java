import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();
        int result = 0;

        if (isNumber(s1)) { // 첫번째 단어가 숫자라면
            result = Integer.parseInt(s1) + 3;
        } else {
            if (isNumber(s2)) { // 두번째 단어가 숫자라면
                result = Integer.parseInt(s2) + 2;
            } else {
                if (isNumber(s3)) { // 세번째 단어가 숫자라면
                    result = Integer.parseInt(s3) + 1;
                }
            }
        }

        if (result % 3 == 0) { // 정답이 3의 배수라면
            sb.append("Fizz");
        }

        if (result % 5 == 0) { // 정답이 5의 배수라면
            sb.append("Buzz");
        }

        if (sb.length() == 0) { // 정답이 3과 5의 배수가 아니라면
            sb.append(result);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 문자열인지 숫자인지 판별하는 함수.
    public static boolean isNumber(String str) {
        return str.matches("[+-]?\\d*(\\.\\d+)?");
    }
}