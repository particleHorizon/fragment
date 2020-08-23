
// https://www.jianshu.com/p/7bc461a53e0e
public class Hello {
    final static int POINT_NUM = 9;
    public static void main(String[] args) throws Exception {
        int[][] cost = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0,30, 0, 0,10, 0, 0, 0},
                { 0,30, 0,20, 0,15, 3, 0, 0},
                { 0, 0,20, 0,15, 0,16, 6, 0},
                { 0, 0, 0,15, 0, 0, 0, 3, 6},
                { 0,10,15, 0, 0, 0, 7, 0, 0},
                { 0, 0, 3,16, 0, 7, 0, 8, 0},
                { 0, 0, 0, 6, 3, 0, 8, 0,10},
                { 0, 0, 0, 0, 6, 0, 0,10, 0}
        };
        int k = 2;
        double[][] dis = new double[k + 1][POINT_NUM];
        dij2(cost, 1, dis, k);
        for (int i = 0; i < dis[0].length; i++) {
            System.out.println(i + ":" + dis[k][i]);
        }
    }

    static void dij2(int[][] cost, int source, double[][] dis, int k) {
        for (int use = 0; use <= k; use++) {
            for (int i = 0; i < dis[0].length; i++) {
                dis[use][i] = -1;
            }
        }
        boolean[][] flag = new boolean[k + 1][dis[0].length];
        for (int use = 0; use <= k; use++) {
            dis[use][source] = 0;
            flag[use][source] = true;
        }
        for (int i = 0; i < dis[0].length; i++) {
            if (cost[source][i] > 0) {
                for (int use = 0; use <= k; use++) {
                    if (use == 0) {
                        dis[use][i] = cost[source][i];
                    } else {
                        dis[use][i] = cost[source][i] / 2.0;
                    }
                }
            }
        }

        for (int cnt = 0; cnt < dis[0].length - 1; cnt++) {
            for (int use = 0; use <= k; use++) {
                double minDis = 10000;
                int minIndex = 0;
                for (int i = 0; i < dis[0].length; i++) {
                    if (!flag[use][i] && dis[use][i] > 0 && dis[use][i] < minDis) {
                        minDis = dis[use][i];
                        minIndex = i;
                    }
                }
                flag[use][minIndex] = true;
                for (int i = 0; i < dis[0].length; i++) {
                    if (flag[use][i] || cost[minIndex][i] <= 0) {
                        continue;
                    }
                    if (dis[use][i] < 0 || dis[use][minIndex] + cost[minIndex][i] < dis[use][i]) {
                        dis[use][i] = dis[use][minIndex] + cost[minIndex][i];
                    }
                }
                if (use < k) {
                    for (int i = 0; i < dis[0].length; i++) {
                        if (flag[use + 1][i] || cost[minIndex][i] <= 0) {
                            continue;
                        }
                        if (dis[use + 1][i] < 0 || dis[use][minIndex] + cost[minIndex][i] / 2.0 < dis[use + 1][i]) {
                            dis[use + 1][i] = dis[use][minIndex] + cost[minIndex][i] / 2.0;
                        }
                    }
                }
            }
        }
    }

    static void dij(int[][] cost, int source, double[] dis) {
        for (int i = 0; i < dis.length; i++) {
            dis[i] = -1;
        }
        boolean[] flag = new boolean[dis.length];
        dis[source] = 0;
        flag[source] = true;
        for (int i = 0; i < dis.length; i++) {
            if (cost[source][i] > 0) {
                dis[i] = cost[source][i];
            }
        }
        for (int cnt = 0; cnt < dis.length - 1; cnt++) {
            double minDis = 10000;
            int minIndex = 0;
            for (int i = 0; i < dis.length; i++) {
                if (!flag[i] && dis[i] > 0 && dis[i] < minDis) {
                    minDis = dis[i];
                    minIndex = i;
                }
            }
            flag[minIndex] = true;
            for (int i = 0; i < dis.length; i++) {
                if (flag[i] || cost[minIndex][i] <= 0) {
                    continue;
                }
                if (dis[i] < 0 || dis[minIndex] + cost[minIndex][i] < dis[i]) {
                    dis[i] = dis[minIndex] + cost[minIndex][i];
                }
            }
        }
    }
}

