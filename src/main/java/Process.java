/**
 * Represents a process in the system.
 */
public class Process {
  private int pid;
  private int arrivalTime;
  private int burstTime;
  private int priority;
  private int remainingTime;

  public Process(int pid, int arrivalTime, int burstTime) {
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.remainingTime = burstTime;
  }

  public Process(int pid, int arrivalTime, int burstTime, int priority) {
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.priority = priority;
    this.remainingTime = burstTime;
  }

  /**
   * Get the process ID.
   *
   * @return the process ID
   */
  public int getPid() {
    return pid;
  }

  /**
   * Get the arrival time of the process.
   *
   * @return the arrival time of the process
   */
  public int getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Get the burst time of the process.
   *
   * @return the burst time of the process
   */
  public int getBurstTime() {
    return burstTime;
  }

  /**
   * Get the priority of the process.
   *
   * @return the priority of the process
   */
  public int getPriority() {
    return priority;
  }

  /**
   * Get the remaining time of the process.
   *
   * @return the remaining time of the process
   */
  public int getRemainingTime() {
    return remainingTime;
  }

  /**
   * Set the remaining time of the process.
   *
   * @param remainingTime the remaining time of the process
   */
  public void setRemainingTime(int remainingTime) {
    this.remainingTime = remainingTime;
  }

  /**
   * Set the priority of the process.
   *
   * @param priority the priority of the process
   */
  public void setPriority(int priority) {
    this.priority = priority;
  }

  /**
   * Set the burst time of the process.
   *
   * @param burstTime the burst time of the process
   */
  public void setBurstTime(int burstTime) {
    this.burstTime = burstTime;
  }

  /**
   * Set the arrival time of the process.
   *
   * @param arrivalTime the arrival time of the process
   */
  public void setArrivalTime(int arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  /**
   * Set the process ID.
   *
   * @param pid the process ID
   */
  public void setPid(int pid) {
    this.pid = pid;
  }
}
