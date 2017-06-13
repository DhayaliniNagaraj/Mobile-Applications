package example.dhayalini.androidservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.dhayalini.androidservices.services.CounterService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtCounter;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtCounter = (EditText) findViewById(R.id.edtCounter);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtons();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnStart){
            Intent counterServiceIntent = new Intent(this,CounterService.class);
            int initialCounterValue = 0;
            try {
                initialCounterValue = Integer.parseInt(edtCounter.getText().toString());
            }catch (NumberFormatException e){
                //Ignore this exception, since initial counter value is already intialised to 0
                Toast.makeText(MainActivity.this, "Invalid integer value. Starting the counter from 1..", Toast.LENGTH_SHORT).show();
                edtCounter.setText(""+1);
            }
            counterServiceIntent.putExtra("initial_value_counter",initialCounterValue);
            startService(counterServiceIntent);
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

        }else if(id == R.id.btnStop){
            Intent counterServiceIntent = new Intent(this,CounterService.class);
            stopService(counterServiceIntent);
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }


    /**
     * Updates the buttons UI state
     */
    private void updateButtons(){
        if(CounterService.isServiceRunning){
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
        }else{
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
        }
    }
}
