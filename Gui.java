public interface Gui
{
    //Called once to connect this display to its lock
    public void connect(Lock lock);

    //Called to initialise the display
    public void initialise();

    //Called to change the message on the display
    public void setDisplay(String s);

    //Called to lock/unlock the safe door
    public void setLocked(boolean locked);
}
