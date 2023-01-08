
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

    public class FirstComeFirstServeCPUSchedulingAlgorithm{
        int burstTime[];
        int arrivalTime[];
        String[] processId;
        List<String[]> lst = new ArrayList<>();
        protected String line = "";
        int i = 0;
        int numberOfProcess;

        void FirstCome(String FileName) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(FileName));
                line = br.readLine();
                numberOfProcess = Integer.parseInt(line);
                burstTime = new int[numberOfProcess];
                arrivalTime = new int[numberOfProcess];
                processId = new String[numberOfProcess];
                String st = "P";
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    processId[i] = st.concat(Integer.toString(i));
                    burstTime[i] = Integer.parseInt(values[1]);
                    arrivalTime[i] = Integer.parseInt(values[0]);
                    i++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        void sortAccordingArrivalTime(int[] at, int[] bt, String[] pid) {
            boolean swapped;
            int temp;
            String stemp;
            for (int i = 0; i < numberOfProcess; i++) {
                swapped = false;
                for (int j = 0; j < numberOfProcess - i - 1; j++) {
                    if (at[j] > at[j + 1]) {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping process id
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                        //enhanced bubble sort
                        swapped = true;
                    }
                }
                if (swapped == false) {
                    break;
                }
            }
        }

        void firstComeFirstServeAlgorithm() {
            int finishTime[] = new int[numberOfProcess];
            int bt[] = burstTime.clone();
            int at[] = arrivalTime.clone();
            String pid[] = processId.clone();
            int waitingTime[] = new int[numberOfProcess];
            int turnAroundTime[] = new int[numberOfProcess];
            sortAccordingArrivalTime(at, bt, pid);

            //calculating waiting & turn-around time for each process
            finishTime[0] = at[0] + bt[0];
            turnAroundTime[0] = finishTime[0] - at[0];
            waitingTime[0] = turnAroundTime[0] - bt[0];
            for (int i = 1; i < numberOfProcess; i++) {
                finishTime[i] = bt[i] + finishTime[i - 1];
                turnAroundTime[i] = finishTime[i] - at[i];
                waitingTime[i] = turnAroundTime[i] - bt[i];
            }
            float sum = 0;
            for (int n : waitingTime) {
                sum += n;
            }
            float averageWaitingTime = sum / numberOfProcess;

            sum = 0;
            for (int n : turnAroundTime) {
                sum += n;
            }
            float averageTurnAroundTime = sum / numberOfProcess;

            //print on console the order of processes scheduled using FirstComeFirstServer Algorithm
            System.out.println("FCFS mean turnaround=" + averageTurnAroundTime);
        }


        public static void main(String[] args) {
            System.out.println("input1:");
            FirstComeFirstServeCPUSchedulingAlgorithm obj = new FirstComeFirstServeCPUSchedulingAlgorithm();
            obj.FirstCome("input1.txt");
            obj.firstComeFirstServeAlgorithm();
            System.out.println("input2:");
            FirstComeFirstServeCPUSchedulingAlgorithm obj2 = new FirstComeFirstServeCPUSchedulingAlgorithm();
            obj2.FirstCome("input2.txt");
            obj2.firstComeFirstServeAlgorithm();
            System.out.println("input3:");
            FirstComeFirstServeCPUSchedulingAlgorithm obj3 = new FirstComeFirstServeCPUSchedulingAlgorithm();
            obj3.FirstCome("input3.txt");
            obj3.firstComeFirstServeAlgorithm();
            System.out.println("input4:");
            FirstComeFirstServeCPUSchedulingAlgorithm obj4 = new FirstComeFirstServeCPUSchedulingAlgorithm();
            obj4.FirstCome("input4.txt");
            obj4.firstComeFirstServeAlgorithm();
            System.out.println("input5:");
            FirstComeFirstServeCPUSchedulingAlgorithm obj5 = new FirstComeFirstServeCPUSchedulingAlgorithm();
            obj5.FirstCome("input5.txt");
            obj5.firstComeFirstServeAlgorithm();


        }
    }

