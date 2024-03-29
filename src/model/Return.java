package model;

import java.sql.Timestamp;

public class Return {
  private final int rid;
  private final Timestamp dateTime;
  private final int odometer;
  private final int fulltank;
  private double value;

  public Return(int rid, Timestamp dateTime, int odometer, int fulltank, double value) {
    this.rid = rid;
    this.dateTime = dateTime;
    this.odometer = odometer;
    this.fulltank = fulltank;
    this.value = value;
  }

  public int getRid() {
    return rid;
  }

  public Timestamp getDateTime() {
    return dateTime;
  }

  public int getOdometer() {
    return odometer;
  }

  public int isFulltank() {
    return fulltank;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format(
        "Return: \n"
            + "* rid = %d\n"
            + "* dateTime = %s\n"
            + "* odometer = %d\n"
            + "* fulltank = %d\n"
            + "* rvalue = %.2f\n",
        rid, dateTime, odometer, fulltank, value);
  }
}
