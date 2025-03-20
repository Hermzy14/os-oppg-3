/**
 * Class for storing user input.
 */
git public class Process {

  int id;
  int arrivalTime;
  int burstTime;
  int priority;
  int waitingTime;

  int turnaroundTime;

  /**
   * Constructor for process.
   */

  public Process (int id, int arrivalTime, int burstTime, int priority) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.priority = priority;
    this.waitingTime = 0;   // Initialized to 0, calculated later
    this.turnaroundTime = 0; // Initialized to 0, calculated later
  }

  /**
   * Constructor for process without priority (used for FCFS).
   */
  public Process(int id, int arrivalTime, int burstTime) {
    this(id, arrivalTime, burstTime, -1); // For FCFS, we set priority to -1
  }


  @Override
    public String toString() {
    return "P" + id + " (Arrival: " + arrivalTime + ", Burst: " + burstTime + ", Priority: " + priority + ")";
    }
}
