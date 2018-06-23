import java.lang.reflect.Constructor;
import java.util.Timer;
import java.util.TimerTask;

public class Driver
{
    public static void main(String[] args)
      throws Exception
    {
	if( args.length!=1 ){
	    System.err.println("Usage: Driver LOCKNAME");
	    System.exit(1);
	    return;
	}
	String lockName= args[0];

	Class cl= Class.forName(lockName,true,Thread.currentThread().getContextClassLoader());
	Constructor c[]= cl.getConstructors();
	if( c.length==0 ){
	    System.err.println("There is NO constructor in your lock class");
	    System.exit(1);
	    return;
	}

	if( c.length>1 ){
	    System.err.println( "There is more than one constructor in your class");
	    System.exit(1);
	}
	
	//Construct the components
	Lock lock=(Lock)c[0].newInstance(new Object[0]);
	Gui gui= new LockGui();
       
	//Connect them to each other
	gui.connect(lock);
	lock.connect(gui);

	//Reset the components()
	gui.initialise();
	lock.initialise();

	Timer t= new Timer();
	Ticker ticker= new Ticker(lock);

	t.scheduleAtFixedRate(ticker,0,100);
    }


    private static class Ticker extends TimerTask
    {
	Lock lock;

	public Ticker(Lock lock)
	{
	    this.lock= lock;
	}


	public void run()
	{
	    lock.tick();
	}
    }
}
