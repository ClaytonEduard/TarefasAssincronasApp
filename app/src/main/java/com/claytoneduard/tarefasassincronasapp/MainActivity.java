package com.claytoneduard.tarefasassincronasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

    }

    public void iniciarAsyncTask(View view) {

        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);

    }

    //criando uma class interna para testes

    /**
     * 1-> Parametro a ser passado para a classe / Void
     * 2-> Tipo de valor que será utilizado para o progresso da tarefa
     * 3-> Retorno após tarefa finalizada
     */

    class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        // A propria class chama os medoos em ordem de execução  1,2,3,4

        // 1°
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        // 2°
        @Override
        protected String doInBackground(Integer... integers) {
            int numero = integers[0];
            for (int i = 0; i < numero; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return "Finalizado";
        }

        // 3°
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        // 4°
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }


    }

}