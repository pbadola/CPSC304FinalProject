package ui;

import controller.CustomersController;
import controller.ReservationsController;
import controller.VehiclesController;
import model.Customer;
import model.Reservation;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ReservationUI extends JFrame implements ActionListener  {
    private static final int TEXT_FIELD_WIDTH = 30;
    private MainWindow mw;
    private Reservation res;
    private Customer customer;

    private JButton submitButton;
    private JButton submitCustButton;
    private JButton submitInfoButton;
    private JButton reserveButton;
    private JButton cancelButton;

    private JTextField carTypeField;
    private JTextField locationField;
    private JTextField fromDateTimeField;
    private JTextField toDateTimeField;
    private JTextField cityField;
    private JTextField dlicenseField;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField phoneNumField;


    private String carType;
    private String location;
    private String fromDateTime;
    private String toDateTime;
    private String city;
    private String dlicense;
    private String name;
    private String address;
    private String phoneNum;
    private int confNo;
    private Timestamp toTimestamp;
    private Timestamp fromTimestamp;


    private JFrame frame;
    private JPanel contentPane;
    private GridBagLayout gb;
    private  GridBagConstraints c;

    private ArrayList<Vehicle> availableVehicles = new ArrayList<>();

    public ReservationUI(JFrame frame){
        super("Available Vehicle Menu");
        this.frame = frame;
    }

    public void showMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        //TODO: Enter the format
        JLabel carTypeLabel = new JLabel("Car type: ");
        JLabel locationLabel = new JLabel("Location: ");
        JLabel cityLabel = new JLabel("City: ");
        JLabel fromDateTimeLabel = new JLabel("Pickup Date and Time (required): ");
        JLabel toDateTimeLabel = new JLabel("Drop-off Date and Time (required): ");

        carTypeField = new JTextField(TEXT_FIELD_WIDTH);
        locationField = new JTextField(TEXT_FIELD_WIDTH);
        cityField = new JTextField(TEXT_FIELD_WIDTH);
        fromDateTimeField = new JTextField(TEXT_FIELD_WIDTH);
        toDateTimeField = new JTextField(TEXT_FIELD_WIDTH);
        submitButton = new JButton("SUBMIT");

        contentPane = new JPanel();
        frame.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        gb = new GridBagLayout();
        c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //car type
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        gb.setConstraints(carTypeLabel, c);
        contentPane.add(carTypeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(carTypeField, c);
        contentPane.add(carTypeField);

        // location
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(locationLabel, c);
        contentPane.add(locationLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(locationField, c);
        contentPane.add(locationField);

        // place the city label
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cityLabel, c);
        contentPane.add(cityLabel);

        // place the text field for the location
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cityField, c);
        contentPane.add(cityField);

        // from date time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromDateTimeLabel, c);
        contentPane.add(fromDateTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(fromDateTimeField, c);
        contentPane.add(fromDateTimeField);

        // to date time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toDateTimeLabel, c);
        contentPane.add(toDateTimeLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(toDateTimeField, c);
        contentPane.add(toDateTimeField);


        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitButton, c);
        contentPane.add(submitButton);

        submitButton.addActionListener(this);

        // anonymous inner class for closing the window
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        frame.pack();

        // center the frame
        Dimension d = frame.getToolkit().getScreenSize();
        Rectangle r = frame.getBounds();
        frame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        frame.setVisible(true);

        // place the cursor in the text field for the username
        carTypeField.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO: Handle errors appropriately
        //if getVehicles gives null then
            //error handling
        //else
        if(e.getSource() == submitButton) {
            carType = carTypeField.getText();
            location = locationField.getText();
            fromDateTime = fromDateTimeField.getText();
            toDateTime = toDateTimeField.getText();
            city = cityField.getText();



            if(fromDateTime.isBlank() || toDateTime.isBlank()){
                missingRequiredInfo();
            } else {
                showAvailableVehicles();
            }
        } else if (e.getSource() == reserveButton){
            reservationMenu();
        } else if(e.getSource() == cancelButton){
            mw = new MainWindow();
            mw.showFrame();
        } else if(e.getSource() == submitCustButton){
            name = nameField.getText();
            dlicense = dlicenseField.getText();
            if(dlicense.isBlank() || name.isBlank()){
                missingRequiredInfo();
            }else{
                makeReservation();
            }
        } else if(e.getSource() == submitInfoButton){
            phoneNum = phoneNumField.getText();
            address = addressField.getText();
            if (address.isBlank() || phoneNum.isBlank()){
                missingRequiredInfo();
            } else {
                customer = handleCustomerInfo();
                completeReservation();
            }
        }
    }
    private void missingRequiredInfo(){
        JLabel requiredLabel = new JLabel("Please ensure that you have filled in " +
                "the required fields");

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(requiredLabel, c);
        contentPane.add(requiredLabel);
        frame.pack();
    }
    public void showAvailableVehicles(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel availabilityLabel;
        reserveButton = new JButton("RESERVE");
        cancelButton = new JButton("CANCEL");

        System.out.println(carType);
        if (carType.isBlank()){
            carType = null;
        }
        if (location.isBlank()){
            location = null;
        }
        if (city.isBlank()){
            city = null;
        }
        // example: Timestamp.valueOf("2019-01-18 13:30:00");
        fromTimestamp = Timestamp.valueOf(fromDateTime);
        toTimestamp = Timestamp.valueOf(toDateTime);
        availableVehicles = VehiclesController.getAvailableVehicles(carType, location, city,
                fromTimestamp, toTimestamp);
        boolean setReserve = false;

        if (availableVehicles == null || availableVehicles.isEmpty()){
            availabilityLabel = new JLabel("**** Sorry, there are no vehicles available at this time****");
        } else {
            availabilityLabel = new JLabel("Good news, we have the perfect vehicle for you to rent!");
            setReserve = true;
        }

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(availabilityLabel, c);
        contentPane.add(availabilityLabel);

        if (setReserve){

            // reserve button
            c.gridwidth = GridBagConstraints.REMAINDER;
            c.insets = new Insets(5, 10, 10, 10);
            c.anchor = GridBagConstraints.CENTER;
            gb.setConstraints(reserveButton, c);
            contentPane.add(reserveButton);
        }

        //cancel button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cancelButton, c);
        contentPane.add(cancelButton);

        reserveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        frame.pack();


    }

    private void makeReservation(){
        customer = CustomersController.getCustomer(dlicense);
        if (customer == null) {
            getCustomerInfo();
        }

    }

    private void completeReservation(){
        System.out.println("completer callerd");
        carType = availableVehicles.get(0).getVtname();

        //TODO: Remember Reservation has city and location
        res = new Reservation(1, carType, dlicense, fromTimestamp, toTimestamp, location, city);
        //location and city
        confNo = ReservationsController.makeReservation(res);
        printDetails();
    }

    private void printDetails(){
        frame.getContentPane().removeAll();
        frame.repaint();

        String conf = valueOf(confNo);
        String s = "SUCCESS! Your confirmation number is " + conf +
                "\nName: "+ name +
                "\nDropoff Date and Time: "+ fromDateTime +
                "\nPickup Date and Time: "+ toDateTime +
                "\nCar Type: "+ carType;

        JTextArea textArea = new JTextArea(s);
        cancelButton = new JButton("OK");

        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(textArea, c);
        contentPane.add(textArea);

        //cancel button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(cancelButton, c);
        contentPane.add(cancelButton);

        cancelButton.addActionListener(this);
        frame.setSize(100,100);
        frame.pack();
        frame.setVisible(true);
    }

    private Customer handleCustomerInfo(){
        System.out.println("Handle customer is called");
        int phone = Integer.parseInt(phoneNum);
        Customer c = new Customer(phone, name,address,dlicense);
        CustomersController.addCustomer(c);
        return c;
    }
    private void reservationMenu(){
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel dlicenseLabel = new JLabel("Driver's License (required):  ");
        JLabel nameLabel = new JLabel("Name (required): ");
        dlicenseField = new JTextField(TEXT_FIELD_WIDTH);
        nameField = new JTextField(TEXT_FIELD_WIDTH);

        submitCustButton = new JButton("SUBMIT");

        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(nameLabel, c);
        contentPane.add(nameLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(nameField, c);
        contentPane.add(nameField);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(dlicenseLabel, c);
        contentPane.add(dlicenseLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(dlicenseField, c);
        contentPane.add(dlicenseField);


        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitCustButton, c);
        contentPane.add(submitCustButton);

        submitCustButton.addActionListener(this);

        frame.pack();
        nameField.requestFocus();
        frame.setVisible(true);
    }

    private void getCustomerInfo(){
        System.out.println("get customer info called");
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel addressLabel = new JLabel("Address (required):  ");
        JLabel phoneLabel = new JLabel("Phone Number (required): ");
        addressField = new JTextField(TEXT_FIELD_WIDTH);
        phoneNumField = new JTextField(TEXT_FIELD_WIDTH);

        submitInfoButton = new JButton("SUBMIT");

        // from time
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(addressLabel, c);
        contentPane.add(addressLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(addressField, c);
        contentPane.add(addressField);

        // to date
        c.gridwidth = GridBagConstraints.RELATIVE;
        c.insets = new Insets(10, 10, 5, 0);
        gb.setConstraints(phoneLabel, c);
        contentPane.add(phoneLabel);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(10, 0, 5, 10);
        gb.setConstraints(phoneNumField, c);
        contentPane.add(phoneNumField);


        // submit button
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(submitInfoButton, c);
        contentPane.add(submitInfoButton);

        submitInfoButton.addActionListener(this);

        frame.pack();
        nameField.requestFocus();
        frame.setVisible(true);

    }
}

