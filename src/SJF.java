
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SJF {
    double sum=0;
    int size;
    String line = "";
    int[] arr = new int[size];
    int[] all = new int[size];
    int[]ArrivalTime;
    int[]BurstTime;
    int j, i, end = 0, t = 0, min = Integer.MAX_VALUE;
    int theShort = 0, endTime,realsize=0;
    boolean check = false;
    void ShortestJobFirst(String FileName) {
        //-----------------------------------------------
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            line = br.readLine();
            int m = 0;
            size = Integer.parseInt(line);
            arr = new int[size];
            all = new int[size];
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


        //----------------------------------------------
        for (i = 0; i < size; i++)
            arr[i] = BurstTime[i];
        for(i=0;i<size;i++)
        {
            if(arr[i]!=0)
                realsize++;
        }
        while (end != realsize) {
            for (j = 0; j < size; j++) {
                if ((ArrivalTime[j] <= t) && (arr[j] < min) && (arr[j] > 0)) {
                    min = arr[j];
                    theShort = j;
                    check = true;
                }
            }
            if (check == false) {
                t++;
                continue;
            }
            arr[theShort]--;
            min = arr[theShort];
            if (min == 0) {
                min = Integer.MAX_VALUE;
            }
            if (arr[theShort] == 0) {
                end++;
                check = false;
                endTime = t + 1;
                all[theShort] = endTime - BurstTime[theShort] - ArrivalTime[theShort];
                if (all[theShort] < 0)
                    all[theShort] = 0;
            }
            t++;
        }
        for(i=0;i<size;i++)
        {
            sum+=BurstTime[i]+all[i];
        }
        sum/=size;
        System.out.println("SJF: mean turnaround =" + sum);
    }
    public static void main(String[] args) {
        System.out.println("input1:");
        SJF obj = new SJF();
        obj.ShortestJobFirst("input1.txt");
        System.out.println("input2:");
        SJF obj1 = new SJF();
        obj1.ShortestJobFirst("input2.txt");
        System.out.println("input3:");
        SJF obj2 = new SJF();
        obj2.ShortestJobFirst("input3.txt");
        System.out.println("input4:");
        SJF obj3 = new SJF();
        obj3.ShortestJobFirst("input4.txt");
        System.out.println("input5:");
        SJF obj4 = new SJF();
        obj4.ShortestJobFirst("input5.txt");

    }
}