import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int M;
    static int K;
    static int A;
    static int B;

    static int answer;

    static class Customer implements Comparable<Customer> {
        int id;
        int arriveTime;
        int receptionNum;

        public Customer(int id, int arriveTime) {
            this.id = id;
            this.arriveTime = arriveTime;
            this.receptionNum = 0;
        }

        @Override
        public int compareTo(Customer other) {
            if (this.arriveTime == other.arriveTime) {
                return this.receptionNum - other.receptionNum;
            }
            return this.arriveTime - other.arriveTime;
        }

        @Override
        public String toString() {
            return "(" + id + ", " + arriveTime + ", " + receptionNum + ')';
        }
    }

    static class Service {
        int takenTime;
        int endTime;

        public Service(int takenTime, int endTime) {
            this.takenTime = takenTime;
            this.endTime = endTime;
        }
    }

    static Service[] reception;
    static Service[] repair;
    static Queue<Customer> customer;
    static PriorityQueue<Customer> repairPQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            reception = new Service[N + 1];
            repair = new Service[M + 1];
            customer = new LinkedList<>();
            repairPQ = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                reception[i] = new Service(Integer.parseInt(st.nextToken()), 0);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M ; i++) {
                repair[i] = new Service(Integer.parseInt(st.nextToken()), 0);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                customer.add(new Customer(i, Integer.parseInt(st.nextToken())));
            }

            answer = 0;

            reception();
            repair();

            if (answer == 0) {
                answer = -1;
            }
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void reception() {
        while (!customer.isEmpty()) {

            Customer c = customer.poll(); // 가장 먼저 온 고객
            Service s;

            int emptyReception = -1;

            for (int i = 1; i <= N; i++) {
                if (c.arriveTime >= reception[i].endTime) {
                    emptyReception = i;
                    break;
                }
            }

            if (emptyReception == -1) {
                int min = Integer.MAX_VALUE;
                for (int i = 1; i <= N; i++) {
                    if (reception[i].endTime < min) {
                        min = reception[i].endTime;
                        emptyReception = i;
                    }
                }
                s = reception[emptyReception];
                s.endTime += s.takenTime;
            }
            else {
                s = reception[emptyReception];
                s.endTime = c.arriveTime + s.takenTime;
            }

            c.arriveTime = s.endTime;
            c.receptionNum = emptyReception;

            repairPQ.add(c);
        }
    }

    static void repair() {
        while (!repairPQ.isEmpty()) {

            Customer c = repairPQ.poll(); // 정비 대기열에 가장 먼저 온 고객
            Service s;

            int emptyRepair = -1;

            for (int i = 1; i <= M; i++) {
                if (c.arriveTime >= repair[i].endTime) {
                    emptyRepair = i;
                    break;
                }
            }

            if (emptyRepair == -1) {
                int min = Integer.MAX_VALUE;
                for (int i = 1; i <= M; i++) {
                    if (repair[i].endTime < min) {
                        min = repair[i].endTime;
                        emptyRepair = i;
                    }
                }
                s = repair[emptyRepair];
                s.endTime += s.takenTime;
            }
            else {
                s = repair[emptyRepair];
                s.endTime = c.arriveTime + s.takenTime;
            }

            if (c.receptionNum == A && emptyRepair == B) {
                answer += c.id;
            }
        }
    }
}