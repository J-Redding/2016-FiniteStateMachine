import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LockGui extends JFrame implements Gui
{
    Lock l;
    static boolean RIGHT_TO_LEFT = false;
    JTextField display;
    public LockGui() {
        //Create the gui frame and set conditions on it.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        //Create the display.
        this.display = new JTextField("");
        this.display.setEditable(false);
        c.weightx = 0.5;
        c.gridwidth = 3;
        //Set grid position of display.
        c.gridx = 0;
        c.gridy = 0;
        //Add display to the container.
        pane.add(this.display, c);

        //Create the "1" button.
        button = new JButton("1");
        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);
        //Add the digit ActionListener to the button.
        button.addActionListener(new CustomActionListener(1));

        button = new JButton("2");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(2));

        button = new JButton("3");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(3));

        button = new JButton("4");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(4));

        button = new JButton("5");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(5));

        button = new JButton("6");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(6));

        button = new JButton("7");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(7));

        button = new JButton("8");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(8));

        button = new JButton("9");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(9));

        button = new JButton("*");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(button, c);
        //Add ActionListener to the button.
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                l.starPressed();
            }
        } );

        button = new JButton("0");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 4;
        pane.add(button, c);
        button.addActionListener(new CustomActionListener(0));

        button = new JButton("#");
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 4;
        pane.add(button, c);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                l.hatchPressed();
            }
        } );

        //Draw the GUI.
        this.pack();
        this.setVisible(true);
    }

	//Called once to connect this display to its lock
    public void connect(Lock lock) {
        this.l = lock;
    }

    //Called to initialise the display
    public void initialise() {

    }

    //Called to change the message on the display
    public void setDisplay(String s) {
        Container pane = this.getContentPane();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        this.display = new JTextField(s);
        this.display.setEditable(false);
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(this.display, c);
        pane.revalidate();
    }

    //Called to lock/unlock the safe door
    public void setLocked(boolean locked) {
        if (locked == true) {
            this.setDisplay("Locked");
        }

        else {
            this.setDisplay("Open");
        }
    }

    //Custom ActionListener for the digit buttons
    //Allows for one ActionListener to be used for all digit buttons
    class CustomActionListener implements ActionListener {
        int digit;
        public CustomActionListener(int n) {
            digit = n;
        }

        //Calls digitPressed in the lock for the respective button.
        public void actionPerformed(ActionEvent e) {
            l.digitPressed(digit);
        }
    }
}