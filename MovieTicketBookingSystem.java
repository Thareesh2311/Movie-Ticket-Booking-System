import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieTicketBookingSystem extends JFrame
        implements ActionListener {

    JButton[][] seats =
            new JButton[5][5];

    JButton generateTicketButton;

    JTextArea resultArea;

    String selectedSeat = "";

    int ticketPrice = 200;

    public MovieTicketBookingSystem() {

        setTitle("Movie Ticket Booking System");

        setSize(800, 700);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(
                new Color(240, 240, 255));
        JLabel titleLabel =
                new JLabel(
                        "MOVIE TICKET BOOKING SYSTEM");

        titleLabel.setFont(
                new Font("Arial",
                        Font.BOLD, 28));

        titleLabel.setBounds(
                170, 20, 500, 40);

        add(titleLabel);
        JPanel seatPanel = new JPanel();

        seatPanel.setLayout(
                new GridLayout(5, 5, 10, 10));

        seatPanel.setBounds(
                150, 100, 450, 300);
        for(int i = 0; i < 5; i++) {

            for(int j = 0; j < 5; j++) {

                seats[i][j] =
                        new JButton(
                                "S" + (i+1) + (j+1));

                seats[i][j].setFont(
                        new Font("Arial",
                                Font.BOLD, 16));

                seats[i][j].addActionListener(this);

                seatPanel.add(seats[i][j]);
            }
        }

        add(seatPanel);
        generateTicketButton =
                new JButton("Generate Ticket");

        generateTicketButton.setBounds(
                280, 450, 220, 50);

        generateTicketButton.setFont(
                new Font("Arial",
                        Font.BOLD, 18));

        generateTicketButton.addActionListener(this);

        add(generateTicketButton);
        resultArea = new JTextArea();

        resultArea.setBounds(
                150, 530, 450, 90);

        resultArea.setFont(
                new Font("Monospaced",
                        Font.BOLD, 16));

        resultArea.setEditable(false);

        add(resultArea);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < 5; i++) {

            for(int j = 0; j < 5; j++) {

                if(e.getSource() == seats[i][j]) {
                    if(seats[i][j]
                            .getText()
                            .contains("BOOKED")) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Seat Already Booked!");

                        return;
                    }
                    selectedSeat =
                            "Seat " +
                            (i+1) + "-" +
                            (j+1);
                    seats[i][j].setText("BOOKED");

                    seats[i][j].setEnabled(false);

                    resultArea.setText(
                            selectedSeat +
                            " Selected Successfully!");
                }
            }
        }
        if(e.getSource()
                == generateTicketButton) {

            if(selectedSeat.equals("")) {

                resultArea.setText(
                        "Please Select A Seat!");

            } else {
                String payment =
                        JOptionPane.showInputDialog(
                                this,
                                "Enter Payment Amount ₹"
                                        + ticketPrice);

                try {

                    int amount =
                            Integer.parseInt(payment);

                    if(amount >= ticketPrice) {

                        int change =
                                amount - ticketPrice;

                        resultArea.setText(

                                "===== MOVIE TICKET =====\n\n"

                                + "Seat Number : "
                                + selectedSeat +

                                "\nTicket Price : ₹"
                                + ticketPrice +

                                "\nPayment Status : SUCCESS"

                                + "\nRemaining Balance : ₹"
                                + change);
                    }

                    else {

                        resultArea.setText(
                                "Insufficient Payment!");
                    }

                } catch(Exception ex) {

                    resultArea.setText(
                            "Invalid Payment!");
                }
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        new MovieTicketBookingSystem();
    }
}