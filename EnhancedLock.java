public class EnhancedLock implements Lock
{
    Gui lockGui;
    String entered;
    String code;
    int ticks;
    int enhancedTicks;
    boolean isLocked;
    boolean wait;
    boolean sleeping;
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
        this.ticking = false;
        this.ticks = 0;
        //enhancedTicks activate the enhancedLock sleep mode.
        this.enhancedTicks = 0;
        this.wait = false;
        this.code = "";
        this.entered = "";
    }

    //Called whenever the user presses the star (*) key
    public void starPressed() {
        if (this.wait == false) {
            if (this.isLocked == false) {
                this.lockGui.setDisplay("Open");
            }
        
            else {
                this.lockGui.setDisplay("Locked");
            }

            this.ticking = false;
            //Reset the display.
            this.entered = "";
            //If sleeping, wake up.
            if (this.sleeping == true) {
                this.sleeping = false;
            }
        }
    }

    //Called whenever the user presses the hatch (#) key
    public void hatchPressed() {
        if (this.sleeping == false) {
            if (this.wait == false) {
                if (this.entered.length() > 2) {
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

                        //If the code is incorrect, start waiting.
                        else {
                            this.lockGui.setDisplay("Wait!");
                            this.ticks = 0;
                            this.wait = true;
                        }
                    }

                    this.ticking = false;
                    this.entered= "";
                }
            }
        }
    }

     //Called whenever the user presses a digit key
    public void digitPressed(int n) {
        if (this.sleeping == false) {
            if (this.wait == false) {
                if (this.entered.length() < 6) {
                    this.entered += Integer.toString(n);
                    //If it is the first digit entered, start ticking.
                    if (this.entered.length() == 1) {
                        this.ticks = 0;
                        this.ticking = true;
                    }
                }
                
                //Pad code with dashes, if it is less than six digits long.
                String dashes = "";
                for (int i = 0; i < (6 - this.entered.length()); i++) {
                    dashes += "-";
                }

                String enhancedDisplay = dashes + entered;
                this.lockGui.setDisplay(enhancedDisplay);
            }
        }

        this.enhancedTicks = 0;
    }

    //Called regularly, every 1/10 second
    public void tick() {
        if (this.sleeping == false) {
            if (this.wait == true) {
                this.ticks++;
                if (this.ticks == 400) {
                    //Finish waiting
                    this.lockGui.setDisplay("Locked");
                    this.enhancedTicks = 0;
                    this.wait = false;
                }
            }

            else {
                if (this.ticking == true) {
                    this.ticks++;
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

                this.enhancedTicks++;
                if (this.enhancedTicks == 150) {
                    //Timeout. Start sleeping.
                    this.lockGui.setDisplay("");
                    this.enhancedTicks = 0;
                    this.sleeping = true;
                }
            }
        }
    }
}