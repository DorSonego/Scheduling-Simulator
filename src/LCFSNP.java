

import java.io.BufferedReader;
import java.io.FileReader;

public class LCFSNP {
    double sum = 0;
    int size;
    int[] arr;
    int[] ArrivalTime;
    int[] BurstTime;
    int i, j, wait = 0;
    int time = 0;
    String line = "";

    void LCFS(String FileName) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            line = br.readLine();
            int m = 0;
            size = Integer.parseInt(line);
            arr = new int[size];
            ArrivalTime = new int[size];
            BurstTime = new int[size];
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                ArrivalTime[m] = Integer.parseInt(values[0]);
                BurstTime[m] = Integer.parseInt(values[1]);
                m++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //--------------------
        boolean swapped;
        int temp;
        String stemp;
        for (int i = 0; i < size; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (ArrivalTime[j] > ArrivalTime[j + 1]) {
                    //swapping arrival time
                    temp = ArrivalTime[j];
                    ArrivalTime[j] = ArrivalTime[j + 1];
                    ArrivalTime[j + 1] = temp;

                    //swapping burst time
                    temp = BurstTime[j];
                    BurstTime[j] = BurstTime[j + 1];
                    BurstTime[j + 1] = temp;

                    //swapping process id

                    //enhanced bubblesort
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }


        //---------------------
        for (i = 0; i < size; i++) {
            arr[i] = 0;
        }
        for (j = 0; j < size; j++) {
            if (this.BurstTime[j] != 0) {
                arr[j] = this.BurstTime[j];
                wait += this.BurstTime[j] + this.ArrivalTime[j] - 1;
                break;
            }
        }
        for (i = size - 1; i > j; i--) {
            if (this.ArrivalTime[i] < wait) {
                arr[i] += wait + 1 - this.ArrivalTime[i] + this.BurstTime[i];
            } else {
                arr[i] += this.BurstTime[i];
            }
            wait += this.BurstTime[i];
        }
        for (i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        sum = sum / size;
        System.out.println("LCFS (NP): mean turnaround=" + sum);

    }

    public static void main(String[] args) {
        System.out.println("input1:");
        LCFSNP obj = new LCFSNP();
        obj.LCFS("input1.txt");
        System.out.println("input2:");
        LCFSNP obj1 = new LCFSNP();
        obj1.LCFS("input2.txt");
        System.out.println("input3:");
        LCFSNP obj2 = new LCFSNP();
        obj2.LCFS("input3.txt");
        System.out.println("input4:");
        LCFSNP obj3 = new LCFSNP();
        obj3.LCFS("input4.txt");
        System.out.println("input5:");
        LCFSNP obj4 = new LCFSNP();
        obj4.LCFS("input5.txt");

    }
}
