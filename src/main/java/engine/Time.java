package engine;

/**
 * Created by JgamerXD on 21.08.2014.
 */
public class Time {

    public static final long SECOND = 1000000000l;

    float secTimer = 0;
    int frameCounter = 0;
    long previousTime = 0;
    long currentTime = 0;


    boolean printFPS = false;

    public Time(boolean printFPS)
    {
        this.printFPS = printFPS;
        previousTime = System.nanoTime();
    }

    public float getDelta()
    {
        currentTime = System.nanoTime();
        float delta = (float)((currentTime - previousTime) / 1000000000.0);

        previousTime = currentTime;

        if(printFPS)
            add(delta);

        return delta;
    }

    public void add(float delta)
    {
        secTimer += delta;
        frameCounter++;
        if(secTimer >= 1.0f) {
            System.out.println(frameCounter);
            frameCounter = 0;
            secTimer = 0;
        }
    }

    public void sleep(long time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
