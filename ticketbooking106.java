import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


class frame106 extends JFrame{
    Container c106;
    JLabel driver106 = new JLabel(" Driver");   
    JButton[][] seats106 = new JButton[8][3]; 
    JButton bt106 = new JButton("Confirm");
    String[][] seatStatus106 = new String[8][3];
    Font fn106 = new Font("Arial", Font.PLAIN, 16);
    File file106 = new File("seat106.txt");

    public frame106(){
        c106 = this.getContentPane();
        c106.setBackground(Color.WHITE);
        c106.setLayout(null);
        driver106.setBounds(200, 20, 60, 30);
        driver106.setFont(fn106);
         driver106.setOpaque(true);
        driver106.setBackground(Color.lightGray);
        driver106.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        c106.add(driver106);
        loadSeatsFromFile106();

        char rowLetter106 = 'A';
        for (int row106 = 0; row106 < 8; row106++) {
            for (int col106 = 0; col106 < 3; col106++) {
                String seatName106 = "" + rowLetter106 + (col106 + 1);
                seats106[row106][col106] = new JButton(seatName106);
                seats106[row106][col106].setBounds(50 + col106 * 80, 60 + row106 * 50, 70, 30);
                seats106[row106][col106].setFont(fn106);
                int x;
                if (col106 == 0) {
                    x = 50;  
                } else if (col106 == 1) {
                    x = 180;  
                } else {
                    x = 260; 
                }

                seats106[row106][col106].setBounds(x, 60 + row106 * 50, 70, 30);

                if (seatStatus106[row106][col106].equals("Unsold")) {
                    seats106[row106][col106].setBackground(Color.WHITE);
                } else if (seatStatus106[row106][col106].equals("Sold")) {
                    seats106[row106][col106].setBackground(Color.GRAY);
                    seats106[row106][col106].setEnabled(false);
                }
                 final int r106 = row106, cIndex106 = col106;
                seats106[row106][col106].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e106) {
                        if (seatStatus106[r106][cIndex106].equals("Unsold")) {
                            if (seats106[r106][cIndex106].getBackground() == Color.WHITE) {
                                seats106[r106][cIndex106].setBackground(Color.GREEN);
                                seatStatus106[r106][cIndex106] = "Booked";
                            } else {
                                seats106[r106][cIndex106].setBackground(Color.WHITE);
                                seatStatus106[r106][cIndex106] = "Unsold";
                            }
                        }
                    }
                });

                c106.add(seats106[row106][col106]);
            }
            rowLetter106++;
        }
 
        bt106.setBounds(50, 480, 80, 30);
        bt106.setFont(fn106);
        bt106.setBackground(Color.LIGHT_GRAY);
        bt106.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e106) {
                confirmBookings106();
            }
        });
        c106.add(bt106);
    }
    private void loadSeatsFromFile106() {
        if (!file106.exists()) {
            try (PrintWriter pw106 = new PrintWriter(new FileWriter(file106))) {
                for (int i106 = 0; i106 < 8; i106++) {
                    for (int j106 = 0; j106 < 3; j106++) {
                        pw106.println("Unsold");
                        seatStatus106[i106][j106] = "Unsold";
                    }
                }
            } catch (IOException ex106) {
                ex106.printStackTrace();
            }
        } else {
            try (BufferedReader br106 = new BufferedReader(new FileReader(file106))) {
                for (int i106 = 0; i106 < 8; i106++) {
                    for (int j106 = 0; j106 < 3; j106++) {
                        seatStatus106[i106][j106] = br106.readLine();
                    }
                }
            } catch (IOException ex106) {
                ex106.printStackTrace();
            }
        }
    }
     private void saveSeatsToFile106() {
        try (PrintWriter pw106 = new PrintWriter(new FileWriter(file106))) {
            for (int i106 = 0; i106 < 8; i106++) {
                for (int j106 = 0; j106 < 3; j106++) {
                    pw106.println(seatStatus106[i106][j106]);
                }
            }
        } catch (IOException ex106) {
            ex106.printStackTrace();
        }
    }

    private void confirmBookings106() {
        for (int i106 = 0; i106 < 8; i106++) {
            for (int j106 = 0; j106 < 3; j106++) {
                if (seatStatus106[i106][j106].equals("Booked")) {
                    seatStatus106[i106][j106] = "Sold";
                    seats106[i106][j106].setBackground(Color.GRAY);
                    seats106[i106][j106].setEnabled(false);
                }
            }
             }
        saveSeatsToFile106();
    }
}

public class ticketbooking106 {
    public static void main(String[] args) {
        frame106 f106 = new frame106();
        f106.setVisible(true);
        f106.setBounds(200,100,400,600);
        f106.setResizable(false);
        f106.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
