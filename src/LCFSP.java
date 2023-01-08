
import java.io.BufferedReader;
import java.io.FileReader;

public class LCFSP {
    int size;
    int[] arr;
    int i, j, wait = 0;
    double sum = 0;
    String line = "";
    int[] ArrivalTime;
    int[] BurstTime;
    void LCFSPreemptive(String FileName) {
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

        for (i = 0; i < size; i++) {
            arr[i] = 0;
        }
        arr[size-1] = BurstTime[size-1];
        wait += BurstTime[size-1];

        for(i=size-2;i>=0;i--) {
            if (BurstTime[i] != 0) {
                if ((BurstTime[i]+ArrivalTime[i]<ArrivalTime[i+1])) {
                    arr[i] = BurstTime[i];
                } else {
                    arr[i] +=BurstTime[i]+wait;
                    wait=arr[i];
                }
            }
        }
        for(i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        sum=sum/size;
        System.out.println("LCFS (P): mean turnaround="+sum);
    }
    public static void main(String[] args) {
        System.out.println("input1:");
        LCFSP obj = new LCFSP();
        obj.LCFSPreemptive("input1.txt");
        System.out.println("input2:");
        LCFSP obj1 = new LCFSP();
        obj1.LCFSPreemptive("input2.txt");
        System.out.println("input3:");
        LCFSP obj2 = new LCFSP();
        obj2.LCFSPreemptive("input3.txt");
        System.out.println("input4:");
        LCFSP obj3 = new LCFSP();
        obj3.LCFSPreemptive("input4.txt");
        System.out.println("input5:");
        LCFSP obj4 = new LCFSP();
        obj4.LCFSPreemptive("input5.txt");
    }

}
