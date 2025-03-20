import java.util.Scanner;

public class Main {
  /**
   * Main method which should handle input and output.
   * Should output the following:
   * Average Waiting time
   * Average TurnAround Time:
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice, n;
    CPUScheduling cpuScheduling = new CPUScheduling();

    System.out.println("CPU Scheduling Algorithms");
    System.out.println("1. First Come First Serve (FCFS)");
    System.out.println("2. Preemptive Priority Scheduling");
    System.out.print("Enter your choice (1/2): ");
    choice = scanner.nextInt();

    System.out.print("Enter the number of processes: ");
    n = scanner.nextInt();

    if (choice == 1) {
      Process[] processes = new Process[n];

      System.out.println("\nEnter process details for FCFS:");
      for (int i = 0; i < n; i++) {
        System.out.println("\nProcess " + (i + 1) + ":");
        System.out.print("Process ID: ");
        int pid = scanner.nextInt();
        System.out.print("Arrival Time: ");
        int arrivalTime = scanner.nextInt();
        System.out.print("Burst Time: ");
        int burstTime = scanner.nextInt();

        processes[i] = new Process(pid, arrivalTime, burstTime);
      }

      cpuScheduling.fcfs(processes);
    } else if (choice == 2) {
      Process[] processes = new Process[n];

      System.out.println("\nEnter process details for Preemptive Priority Scheduling:");
      for (int i = 0; i < n; i++) {
        System.out.println("\nProcess " + (i + 1) + ":");
        System.out.print("Process ID: ");
        int pid = scanner.nextInt();
        System.out.print("Arrival Time: ");
        int arrivalTime = scanner.nextInt();
        System.out.print("Burst Time: ");
        int burstTime = scanner.nextInt();
        System.out.print("Priority (lower number means higher priority): ");
        int priority = scanner.nextInt();

        processes[i] = new Process(pid, arrivalTime, burstTime, priority);
      }

      cpuScheduling.priority(processes);
    } else {
      System.out.println("Invalid choice!");
    }

    scanner.close();
  }
}
