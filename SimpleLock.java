public class SimpleLock implements Lock
{
    Gui lockGui;
    String entered;
    String code;
    int ticks;
    boolean isLocked;
    boolean ticking;
    //Called to connect this lock to its display
    public void connect(Gui g) {
        this.lockGui = g;
    }

    //Called to initialsie the lock
    //(Functions like inserting a new battery)
    public void initialise() {
        this.lockGui.setDisplay("Open");
        this.isLocked = false;
        this.lockGui.setLocked(isLocked);
        this.code = "";
        this.entered = "";
        //Ticking lets the timer know if it should be counting ticks.
        //Used when entering a code to lock/unlock.
        this.ticking = false;
        this.ticks = 0;
    }

    //Called whenever the user presses the star (*) key
    public void starPressed() {
        if (this.isLocked == false) {
            this.lockGui.setDisplay("Open");
        }
        
        else {
            this.lockGui.setDisplay("Locked");
        }

        //Stop ticking while not entering a code.
        this.ticking = false;
        //Reset the display.
        this.entered = "";
    }

    //Called whenever the user presses the hatch (#) key
    public void hatchPressed() {
        //Code must be four digits long.
        if (this.entered.length() == 4) {
            //If the current state is Open, set the state to Locked.
            //Update the code.
            if (this.isLocked == false) {
                this.lockGui.setDisplay("Locked");
                this.isLocked = true;
                this.lockGui.setLocked(isLocked);
                this.code = this.entered;
            }

            else {
                //If the entered code is correct, set the state to Open.
                if (this.entered.equals(this.code)) {
                    this.lockGui.setDisplay("Open");
                    this.isLocked = false;
                    this.lockGui.setLocked(isLocked);
                }

                else {
                    this.lockGui.setDisplay("Locked");
                }
            }

            this.ticking = false;
            this.entered = "";
        }
    }

     //Called whenever the user presses a digit key
    public void digitPressed(int n) {
        if (this.entered.length() < 4) {
            this.entered += Integer.toString(n);
            //If it is the first digit entered, start ticking.
            if (this.entered.length() == 1) {
                this.ticks = 0;
                this.ticking = true;
            }

            else if (this.entered.length() == 4) {
                this.ticking = false;
            }
        }

        //Update the display.
        this.lockGui.setDisplay(this.entered);
    }

    //Called regularly, every 1/10 second
    public void tick() {
        if (this.ticking == true) {
    	   this.ticks++;
            //Every 100 ticks is 10 seconds.
            //Reset the display.
            if (this.ticks == 100) {
                if (this.isLocked == false) {
                    this.lockGui.setDisplay("Open");
                }

                else {
                    this.lockGui.setDisplay("Locked");
                }

                this.ticking = false;
                this.entered = "";
            }
        }
    }
}