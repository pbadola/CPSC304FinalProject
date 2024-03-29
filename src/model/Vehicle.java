package model;

public class Vehicle {
  private final String vlicense;
  private final String make;
  private final String model;
  private final int year;
  private final String color;
  private final int odometer;
  private final String status;
  private final String vtname;
  private final String location;
  private final String city;

  public Vehicle(
      String vlicense,
      String make,
      String model,
      int year,
      String color,
      int odometer,
      String status,
      String vtname,
      String location,
      String city) {
    this.vlicense = vlicense;
    this.make = make;
    this.model = model;
    this.year = year;
    this.color = color;
    this.odometer = odometer;
    this.status = status;
    this.vtname = vtname;
    this.location = location;
    this.city = city;
  }

  public String getVlicense() {
    return vlicense;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public String getColor() {
    return color;
  }

  public int getOdometer() {
    return odometer;
  }

  public String getStatus() {
    return status;
  }

  public String getVtname() {
    return vtname;
  }

  public String getLocation() {
    return location;
  }

  public String getCity() {
    return city;
  }

  @Override
  public String toString() {
    return String.format(
        "Vehicle: \n"
            + "* vlicense = %s\n"
            + "* make = %s\n"
            + "* model = %s\n"
            + "* year = %d\n"
            + "* color = %s\n"
            + "* odometer = %d\n"
            + "* status = %s\n"
            + "* vtname = %s\n"
            + "* location = %s\n"
            + "* city = %s",
        vlicense, make, model, year, color, odometer, status, vtname, location, city);
  }
}
