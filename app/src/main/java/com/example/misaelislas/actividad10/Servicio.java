import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import java.util.Random;

public class MyService extends Service
{
    private final IBinder lBinder = new LocalBinder();

    private final Random randomGenerator = new Random();

    public class LocalBinder extends Binder
    {
        MyService getService()
        {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return lBinder;
    }

    public int getRandomNumber()
    {
        return randomGenerator.nextInt(100);
    }
}