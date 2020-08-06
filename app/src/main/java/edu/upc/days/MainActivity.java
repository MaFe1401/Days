package edu.upc.days;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.MessageQueue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

import static java.lang.Integer.valueOf;

public class MainActivity extends AppCompatActivity {
    List<Mes> meses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mes enero = new Mes("enero", 6, 1);
        Mes febrero = new Mes("febrero", 2, 2);
        Mes marzo = new Mes("marzo", 2, 3);
        Mes abril = new Mes("abril", 5, 4);
        Mes mayo = new Mes("mayo", 0, 5);
        Mes junio = new Mes("junio", 3, 6);
        Mes julio = new Mes("julio", 5, 7);
        Mes agosto = new Mes("agosto", 1, 8);
        Mes septiembre = new Mes("septiembre", 4, 9);
        Mes octubre = new Mes("octubre", 6, 10);
        Mes noviembre = new Mes("noviembre", 2, 11);
        Mes diciembre = new Mes("diciembre", 4, 12);
        meses.add(enero);
        meses.add(febrero);
        meses.add(marzo);
        meses.add(abril);
        meses.add(mayo);
        meses.add(junio);
        meses.add(julio);
        meses.add(agosto);
        meses.add(septiembre);
        meses.add(octubre);
        meses.add(noviembre);
        meses.add(diciembre);
    }

    //Button discoverBtn=(Button)findViewById(R.id.discoverBtn);
    public void dayDiscover(View view) {
        int valor;
        EditText day = (EditText) findViewById(R.id.dayText);
        EditText month = (EditText) findViewById(R.id.monthText);
        EditText year = (EditText) findViewById(R.id.yearText);
        TextView answer = (TextView) findViewById(R.id.answerText);
        if((day.getText().toString().isEmpty())||(month.getText().toString().isEmpty())||(year.getText().toString().isEmpty())){
            answer.setText("Empty fields");
        }
        else{
            int dia = valueOf(day.getText().toString());
            int mes = valueOf(month.getText().toString());
            int valorMes = getMonthValue(mes);
            if (valorMes == 20) {
                answer.setText("Error. Does that month exist?");
            } else {
                int valorAño = getYearCode(valueOf(year.getText().toString()), mes);
                if(valorAño==4000){
                    answer.setText("Year not valid");
                }
                else{
                    boolean valido=isItValid(dia, mes, valueOf(year.getText().toString()));
                    if (valido==false){
                        answer.setText("Error. That day does not exist");
                    }
                    else{
                        valor = dia + valorMes + valorAño;
                        String diaDeLaSemana=setDayofTheWeek(valor);
                        answer.setText(diaDeLaSemana);
                    }

                }

            }
        }

    }
    public void randomButton(View view){
        TextView date=findViewById(R.id.date);
        date.setText(generateRandomDate());
    }
    public int getMonthValue(int orden) {
        int i = 0;
        while (i < 12) {
            if (orden == meses.get(i).orden) {
                return meses.get(i).valor;
            } else i++;
        }
        return 20;
    }

    public int getYearCode(int year, int mes) {
        int i = 2000;
        int code = -1;
        boolean encontrado = false;
        if ((year<1600)||(year>2399)){
            return 4000;
        }
        while ((encontrado == false) && (i < 2100)) {
            if (i % 4 == 0) {
                code++;
            }
            if (code >= 7) {
                code = 0;
            }
            if (i - 400 == year) {
                encontrado = true;
            } else if (i - 300 == year) {
                code = code + 5;
                encontrado = true;
            } else if (i - 200 == year) {
                code = code + 3;
                encontrado = true;
            } else if (i - 100 == year) {
                code = code + 1;
                encontrado = true;
            } else if (i + 100 == year) {
                code = code + 5;
                encontrado = true;
            } else if (i + 200 == year) {
                code = code + 3;
                encontrado = true;
            } else if (i + 300 == year) {
                code = code + 1;
                encontrado = true;
            } else if (i == year) {
                encontrado = true;
            }
            else {
                i++;
                code++;
                if (code >= 7) {
                    code = 0;
                }
            }

        }
        if (mes == 1 || mes == 2) {
            if (year % 4 == 0){
                code = code - 1;
                if(year%100==0){
                    code=code+1;
                    if(year%400==0){
                        code=code-1;
                    }
                }
            }
        }
        return code;
    }

    public String setDayofTheWeek(int valor) {
        while (valor >= 7) {
            valor = valor - 7;
        }
        if (valor == 0)
            return "Domingo (Sunday)";
        if (valor == 1)
            return "Lunes (Monday)";
        if (valor == 2)
            return "martes (Tuesday)";
        if (valor == 3)
            return "Miercoles (Wednesday)";
        if (valor == 4)
            return "Jueves (Thursday)";
        if (valor == 5)
            return "Viernes (Friday)";
        else{
            return "Sabado (Saturday)";
        }
    }
    public boolean isItValid(int day, int month, int year){
        boolean bisiesto=false;
        if((month>12)||(month<1)){
            return false;
        }
        if(year%4==0){
            if(year%100!=0){
                bisiesto=true;
            }
            else if(year%400==0){
                bisiesto=true;
            }
            else bisiesto = false;
        }
        if((month==1)&&((day<1)||(day>31))){
            return false;
        }
        if((month==2)&&(bisiesto==false)){
            if((day<1)||(day>28))
                return false;
        }
        if((month==2)&&(bisiesto==true)){
            if((day<1)||(day>29))
                return false;
        }
        if((month==3)&&((day<1)||(day>31))){
            return false;
        }
        if((month==4)&&((day<1)||(day>30))){
            return false;
        }
        if((month==5)&&((day<1)||(day>31))){
            return false;
        }
        if((month==6)&&((day<1)||(day>30))){
            return false;
        }
        if((month==7)&&((day<1)||(day>31))){
            return false;
        }
        if((month==8)&&((day<1)||(day>31))){
            return false;
        }
        if((month==9)&&((day<1)||(day>30))){
            return false;
        }
        if((month==10)&&((day<1)||(day>31))){
            return false;
        }
        if((month==11)&&((day<1)||(day>30))){
            return false;
        }
        if((month==12)&&((day<1)||(day>31))){
            return false;
        }
        return true;

    }
    public String generateRandomDate(){
       boolean valid= false;
       int day=0; int month=0; int year=0;
       while (valid==false){
            day=(int)(Math.random() * ((31 - 1) + 1)) + 1;
            month=(int)(Math.random() * ((12 - 1) + 1)) + 1;
            year=(int)(Math.random() * ((2399 - 1600) + 1)) + 1600;
           valid=isItValid(day, month, year);
       }
        String date= day+"/"+month+"/"+year;
       return date;
    }
}
