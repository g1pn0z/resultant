package resultant.program.rinat.resultanttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);

        Thread launchTimer = new Thread(){
            public void run()
            {
                try
                {
                    int launchTimer = 0;
                    while (launchTimer<5000)
                    {
                        sleep(100);
                        launchTimer = launchTimer+100;
                    };
                    startActivity(new Intent("resultant.program.rinat.CLEARSCREEN"));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    finish();
                }
            }
        };
        launchTimer.start();
    }
}
