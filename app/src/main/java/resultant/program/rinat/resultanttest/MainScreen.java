package resultant.program.rinat.resultanttest;

/**
 * Created by r1n0 on 11.02.18.
 */

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainScreen extends AppCompatActivity {
    public static String LOG_TAG = "my_log"; //Отладка

    Handler myHandler = new Handler(  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        //---------------Начало-обработки------------------------
        final Runnable upQuery = new Runnable() {
            @Override
            public void run() {

                //---------------------Основная программа---------------------------------------------------------
                String output = ""; //Вывод из background потока
                JSONObject dataJsonObj = null; //Json данные
                String InName = ""; //Имя из Json(Stock)
                //---------------------Запрос и Вывод данных-------------------------------------------------------

                int BOOKSHELF_COLUMNS = 3;
                TableLayout tableLayout = (TableLayout)findViewById( R.id.tableLayout );
                tableLayout.setBackgroundColor( Color.GREEN );
                String[] JsonLine = new String[3];

                try {
                    output = new ParseTask().execute().get();
                    Log.d(LOG_TAG, "output: " + output);
                    try {
                        dataJsonObj = new JSONObject(output);
                        JSONArray myStock = dataJsonObj.getJSONArray("stock");

                        //Перебираем и выводим данные котировок myStock
                        for (int i = 0; i < myStock.length(); i++) {
                            TableRow tableRow = new TableRow(MainScreen.this);
                            tableRow.setLayoutParams(new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            JSONObject tmpStock = myStock.getJSONObject(i);
                            JSONObject SecTmpStock = myStock.getJSONObject(i);
                            InName = SecTmpStock.getString("name");
                            JSONObject price = tmpStock.getJSONObject("price");
                            String currency = price.getString("currency");
                            String amount = price.getString("amount");

                            Log.d(LOG_TAG, "name: " + InName);
                            Log.d(LOG_TAG, "currency: " + currency);
                            Log.d(LOG_TAG, "amount: " + amount);
                            JsonLine[0] = InName;
                            JsonLine[1] = currency;
                            JsonLine[2] = amount;

                            for (int j = 0; j < BOOKSHELF_COLUMNS; j++) {
                                TextView tabView = new TextView(MainScreen.this);
                                tabView.setText(JsonLine[j]);
                                tableRow.addView(tabView, j);
                            }
                            tableLayout.addView(tableRow, i);
                        }
                        //----------------------------------------------------------------------------------------

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //--------------------------Конец-основной-программы-----------------------------------------------------

                Log.d( LOG_TAG, "UpDate Yes!" );
                myHandler.postDelayed( this, 15000 );
            }
        };
        myHandler.postDelayed( upQuery, 1000 );
        //---------------Конец-обработки-------------------------
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        TextView infoTextView = (TextView) findViewById(R.id.CopyText);

        // Операции для выбранного пункта меню
        switch (id) {
            case R.id.reload1:
                infoTextView.setText("Вы выбрали кота!");
                //---------------------Основная программа---------------------------------------------------------
                String output = ""; //Вывод из background потока
                JSONObject dataJsonObj = null; //Json данные
                String InName = ""; //Имя из Json(Stock)
                //---------------------Запрос и Вывод данных-------------------------------------------------------

                int BOOKSHELF_COLUMNS = 3;
                TableLayout tableLayout = (TableLayout)findViewById( R.id.tableLayout );
                tableLayout.setBackgroundColor( Color.YELLOW );
                String[] JsonLine = new String[3];

                try {
                    output = new ParseTask().execute().get();
                    Log.d(LOG_TAG, "output: " + output);
                    try {
                        dataJsonObj = new JSONObject(output);
                        JSONArray myStock = dataJsonObj.getJSONArray("stock");

                        //Перебираем и выводим данные котировок myStock
                        for (int i = 0; i < myStock.length(); i++) {
                            TableRow tableRow = new TableRow(MainScreen.this);
                            tableRow.setLayoutParams(new TableLayout.LayoutParams( TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT));

                            JSONObject tmpStock = myStock.getJSONObject(i);
                            JSONObject SecTmpStock = myStock.getJSONObject(i);
                            InName = SecTmpStock.getString("name");
                            JSONObject price = tmpStock.getJSONObject("price");
                            String currency = price.getString("currency");
                            String amount = price.getString("amount");

                            JsonLine[0] = InName;
                            JsonLine[1] = currency;
                            JsonLine[2] = amount;

                            for (int j = 0; j < BOOKSHELF_COLUMNS; j++) {
                                TextView tabView = new TextView(MainScreen.this);
                                tabView.setText(JsonLine[j]);
                                tableRow.addView(tabView, j);
                            }
                            tableLayout.addView(tableRow, i);
                        }
                        //----------------------------------------------------------------------------------------

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                //--------------------------Конец-основной-программы-----------------------------------------------------


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //-------------------------Background--------------------------
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("http://phisix-api3.appspot.com/stocks.json");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            Log.d(LOG_TAG, strJson);// выводим целиком полученную json-строку(для себя)
        }
    }
    //-------------------------Background-End----------------------


}
