import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopWatch implements ActionListener{

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JLabel timelabel = new JLabel();

    int elapsed_time = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsed_time = elapsed_time + 1000;
            seconds = (elapsed_time/1000) % 60;
            minutes = (elapsed_time/60000) % 60;
            hours = (elapsed_time/3600000);

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timelabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });

    StopWatch(){
        timelabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timelabel.setBounds(100,100,200,100);
        timelabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timelabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(100, 200, 100, 50);
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(200,200,100,50);
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(timelabel);
        frame.add(startButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            if(!started){
                startButton.setText("STOP");
                started = true;
                start();
            }
            else{
                started = false;
                stop();
                startButton.setText("START");
            }
        }
        if(e.getSource() == resetButton){
            reset();
            started=false;
            startButton.setText("START");
        }
    }

    public void start(){
        timer.start();
    }
    public void stop(){
        timer.stop();
    }
    public void reset(){
        timer.stop();
        elapsed_time = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timelabel.setText(hours+":"+minutes+":"+seconds);
    }
}
