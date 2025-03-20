import java.util.*;

public class FCFS {

  public static void execute(List<Process> processes) {
    // Sort processes by arrival time
    processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

    int currentTime = 0;

    // Process each job
    for (Process p : processes) {
      // If the process arrives after the current time, we have to wait
      if (p.arrivalTime > currentTime) {
        currentTime = p.arrivalTime;
      }

      // Waiting time is the current time minus the arrival time
      p.waitingTime = currentTime - p.arrivalTime;
      // Turnaround time is waiting time + burst time
      p.turnaroundTime = p.waitingTime + p.burstTime;

      // Update current time after the process finishes
      currentTime += p.burstTime;
    }

    // Output results
    for (Process p : processes) {
      System.out.println("P" + p.id + " - Waiting Time: " + p.waitingTime + ", Turnaround Time: " + p.turnaroundTime);
    }

    calculateAverageTimes(processes);
  }

  // Calculate and print average waiting time and turnaround time
  public static void calculateAverageTimes(List<Process> processes) {
    int totalWaitingTime = 0;
    int totalTurnaroundTime = 0;

    for (Process p : processes) {
      totalWaitingTime += p.waitingTime;
      totalTurnaroundTime += p.turnaroundTime;
    }

    double avgWaitingTime = (double) totalWaitingTime / processes.size();
    double avgTurnaroundTime = (double) totalTurnaroundTime / processes.size();

    System.out.println("\nAverage Waiting Time: " + avgWaitingTime);
    System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
  }
}
