import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Represents two CPU scheduling algorithms:
 * 1. First-Come, First-Served (FCFS)
 * 2. Pre-emptive Priority scheduling algorithm
 */
public class CPUScheduling {
  /**
   * First-Come, First-Served (FCFS) scheduling algorithm with the following input:
   * 1. Process ID
   * 2. Arrival Time
   * 3. Burst Time
   */
  public void fcfs(Process[] processes) {
    int n = processes.length;

    // Sort processes based on arrival time
    Arrays.sort(processes, Comparator.comparingInt(Process::getArrivalTime));

    // Calculate completion time, turnaround time, and waiting time
    int currentTime = 0;
    double totalWaitingTime = 0;
    double totalTurnaroundTime = 0;

    System.out.println("\nProcess Execution Order:");
    printTableHeader("Process ID", "Arrival", "Burst", "Completion", "Turnaround", "Waiting");

    for (int i = 0; i < n; i++) {
      // Ensure the current time is at least the arrival time of the process
      if (currentTime < processes[i].getArrivalTime()) {
        currentTime = processes[i].getArrivalTime();
      }

      // Calculate completion time
      int completionTime = currentTime + processes[i].getBurstTime();
      currentTime = completionTime;

      // Calculate turnaround time = completion time - arrival time
      int turnaroundTime = completionTime - processes[i].getArrivalTime();

      // Calculate waiting time = turnaround time - burst time
      int waitingTime = turnaroundTime - processes[i].getBurstTime();

      // Add to total times
      totalWaitingTime += waitingTime;
      totalTurnaroundTime += turnaroundTime;

      // Print process details
      printTableRow(
          processes[i].getPid(),
          processes[i].getArrivalTime(),
          processes[i].getBurstTime(),
          completionTime,
          turnaroundTime,
          waitingTime
      );
    }

    printTableFooter(6);

    // Calculate and print average waiting time and average turnaround time
    double avgWaitingTime = totalWaitingTime / n;
    double avgTurnaroundTime = totalTurnaroundTime / n;

    System.out.printf("\nAverage Waiting Time: %.2f\n", avgWaitingTime);
    System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
  }


  /**
   * Pre-emptive Priority scheduling algorithm with the following input:
   * 1. Process ID
   * 2. Arrival Time
   * 3. Burst Time
   * 4. Priority
   */
  public void priority(Process[] processes) {
    int n = processes.length;

    // Create a copy of the processes array for calculations
    Process[] processesInfo = new Process[n];
    for (int i = 0; i < n; i++) {
      processesInfo[i] = new Process(
          processes[i].getPid(),
          processes[i].getArrivalTime(),
          processes[i].getBurstTime(),
          processes[i].getPriority()
      );
    }

    // Sort processes by arrival time to identify the first process
    Arrays.sort(processesInfo, Comparator.comparingInt(Process::getArrivalTime));

    int completed = 0;       // Number of completed processes
    int currentTime = 0;     // Current time
    int[] completionTime = new int[n];
    boolean[] isCompleted = new boolean[n];

    System.out.println("\nProcess Execution Timeline:");
    System.out.println("Time\tProcess ID");

    // Continue until all processes are completed
    while (completed != n) {
      int highestPriorityIndex = -1;
      int highestPriority = Integer.MAX_VALUE;

      // Find the process with the highest priority (lowest priority number)
      // that has arrived and is not completed
      for (int i = 0; i < n; i++) {
        if (processesInfo[i].getArrivalTime() <= currentTime && !isCompleted[i]) {
          if (processesInfo[i].getPriority() < highestPriority) {
            highestPriority = processesInfo[i].getPriority();
            highestPriorityIndex = i;
          }
        }
      }

      // If no process is available, increment time
      if (highestPriorityIndex == -1) {
        currentTime++;
        continue;
      }

      // Execute the process for 1 time unit
      int remainingTime = processesInfo[highestPriorityIndex].getRemainingTime();
      processesInfo[highestPriorityIndex].setRemainingTime(remainingTime - 1);
      System.out.println(currentTime + "\t" + processesInfo[highestPriorityIndex].getPid());
      currentTime++;

      // If the process is completed
      if (processesInfo[highestPriorityIndex].getRemainingTime() == 0) {
        completed++;
        isCompleted[highestPriorityIndex] = true;
        completionTime[highestPriorityIndex] = currentTime;
      }
    }

    // Calculate turnaround time and waiting time
    double totalWaitingTime = 0;
    double totalTurnaroundTime = 0;

    System.out.println("\nProcess Details:");
    printTableHeader("Process ID", "Arrival", "Burst", "Priority", "Completion", "Turnaround",
        "Waiting");

    for (int i = 0; i < n; i++) {
      //processes[i].completionTime = completionTime[i];
      int turnaroundTime = completionTime[i] - processes[i].getArrivalTime();
      int waitingTime = turnaroundTime - processes[i].getBurstTime();

      totalTurnaroundTime += turnaroundTime;
      totalWaitingTime += waitingTime;

      printTableRow(
          processes[i].getPid(),
          processes[i].getArrivalTime(),
          processes[i].getBurstTime(),
          processes[i].getPriority(),
          completionTime[i],
          turnaroundTime,
          waitingTime
      );
    }

    printTableFooter(7);

    // Calculate and print average waiting time and average turnaround time
    double avgWaitingTime = totalWaitingTime / n;
    double avgTurnaroundTime = totalTurnaroundTime / n;

    System.out.printf("\nAverage Waiting Time: %.2f\n", avgWaitingTime);
    System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
  }

  // Helper method to print formatted table headers and rows
  private void printTableHeader(String... headers) {
    StringBuilder divider = new StringBuilder("+");
    StringBuilder headerLine = new StringBuilder("|");

    for (String header : headers) {
      divider.append("-".repeat(header.length() + 2)).append("+");
      headerLine.append(" ").append(header).append(" |");
    }

    System.out.println(divider);
    System.out.println(headerLine);
    System.out.println(divider);
  }

  private void printTableRow(Object... values) {
    StringBuilder row = new StringBuilder("|");

    for (Object value : values) {
      String strValue = value.toString();
      // Center align the value
      int padding = Math.max(10 - strValue.length(), 0);
      int leftPad = padding / 2;
      int rightPad = padding - leftPad;

      row.append(" ".repeat(leftPad)).append(strValue).append(" ".repeat(rightPad)).append(" |");
    }

    System.out.println(row);
  }

  private void printTableFooter(int columnCount) {
    StringBuilder divider = new StringBuilder("+");
    for (int i = 0; i < columnCount; i++) {
      divider.append("-".repeat(12)).append("+");
    }
    System.out.println(divider);
  }

}
