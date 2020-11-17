package chapter1.section4.exercises;

public class Ex06 {


    public void method1(int N) {
        int sum = 0;
        //O(logN)
        for (int n = N; n > 0; n /= 2) {
            //N + N/2 + N/4 + N/8 + ... + 1 = 2N - 1 ~O(N)
            for (int i = 0; i < n; i++) {
                sum++;
            }
        }
    }

    public void method2(int N) {
        int sum = 0;
        //O(logN)
        for (int i = 1; i < N; i *= 2) {
            //1 + 2 + 4 + 8 + ... + N = 2N - 1 ~O(N)
            for (int j = 0; j < i; j++) {
                sum++;
            }
        }
    }

    public void method3(int N) {
        int sum = 0;
        //O(logN)
        for (int i = 1; i < N; i *= 2) {
            //O(logN) * O(N) = O(N*logN)
            for (int j = 0; j < N; j++) {
                sum++;
            }
        }
    }
}
