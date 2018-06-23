public interface Lock
{
    //Called to connect this lock to its display
    public void connect(Gui g);

    //Called to initialsie the lock
    //(Functions like inserting a new battery)
    public void initialise();
    
    //Called whenever the user presses the star (*) key
    public void starPressed();

    //Called whenever the user presses the hatch (#) key
    public void hatchPressed();

    //Called whenever the user presses a digit key
    public void digitPressed(int n);

    //Called regularly, every 1/10 second
    public void tick();
}
