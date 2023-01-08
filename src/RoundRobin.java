
import java.io.BufferedReader;
import java.io.FileReader;

public class RoundRobin {
    double sum = 0;
    int size;
    int[] arr;
    int[] waitArr;
    int[] ArrivalTime;
    int i, time = 0;
    String line = "";

    void RR(String FileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            line = br.readLine();
            int j = 0;
            size = Integer.parseInt(line);//puts the number of process in line 1 in size;
            arr = new int[size];
            waitArr = new int[size];
            ArrivalTime = new int[size];
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");//read from file integer with backspace between and puts in string array
                ArrivalTime[j] = Integer.parseInt(values[0]);//puts the numbet from a file to the array
                arr[j] = Integer.parseInt(values[1]);
                j++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //-----------------------------------------
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
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //enhanced bubblesort
                    swapped = true;
                }
            }
            if (swapped == false) {
                break;
            }
        }
        //------------------------------------------------------
        for (i = 0; i < size; i++) {
            if (arr[i] != 0) {
                time = ArrivalTime[i];
                break;
            }
        }
        while (true) {
            boolean finish = true;
            boolean in = false;
            for (i = 0; i < size; i++) {
                if ((arr[i] > 0) && (ArrivalTime[i] <= time)) {
                    in = true;
                    finish = false;
                    if (arr[i] > 2) {
                        time += 2;
                        arr[i] -= 2;
                    } else {
                        time += arr[i];
                        waitArr[i] = time - arr[i] - ArrivalTime[i] + 1;
                        arr[i] = 0;
                    }
                }
            }
            if (in == false) {
                for (i = 0; i < size; i++) {
                    if (arr[i] != 0) {
                        finish = false;
                        time = ArrivalTime[i];
                        break;
                    }
                }
            }
            if (finish == true)
                break;
        }
        for (i = 0; i < size; i++) {
            sum += arr[i] + waitArr[i];
        }
        sum /= size;
        System.out.println("RR: mean turnaround =" + sum);
    }
    public static void main(String[] args) {
        System.out.println("input1:");
        RoundRobin obj = new RoundRobin();
        obj.RR("input1.txt");
        System.out.println("input2:");
        RoundRobin obj1 = new RoundRobin();
        obj1.RR("input2.txt");
        System.out.println("input3:");
        RoundRobin obj2 = new RoundRobin();
        obj2.RR("input3.txt");
        System.out.println("input4:");
        RoundRobin obj3 = new RoundRobin();
        obj3.RR("input4.txt");
        System.out.println("input5:");
        RoundRobin obj4 = new RoundRobin();
        obj4.RR("input5.txt");
    }


}
