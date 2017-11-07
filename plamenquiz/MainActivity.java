package com.example.pacho.plamenquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>(); //array list que contiene la preguntas.

    protected boolean resp_boolean;

    protected EditText rspEscrita;

    protected int posicionArray=0,puntuacionTotal=0; //de las preguntas
    protected int respuestasCorrectas=0,respuestasIncorrectas=0;
    protected int resp_int;//variable para guardar la respuesta tipo int


    protected String[] preguntasString;
    protected String[] respuestasString;
    protected String resp_string;

    protected TextView txt_pregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preguntasString=getResources().getStringArray(R.array.preguntas); //leemos los datos del XML
        respuestasString=getResources().getStringArray(R.array.respuestas); //leemos datos de XML

    }

    //vuelve a la pregunta anterior
    public void anteriorPregunta(View v){

        posicionArray = posicionArray - 1;
        plantearPregunta(posicionArray);

    }

    //crea las preguntas recorriendo las dos arrays de pregunta y respuesta
    public void crearPreeguntas(){
        for (int i=0;i<preguntasString.length;i++){
            switch(tipoRespuesta(respuestasString[i])){
                case 1: preguntas.add(new Pregunta(preguntasString[i],respuestasString[i].equals("true")));
                    break;
                case 2: preguntas.add(new Pregunta(preguntasString[i],Integer.parseInt(respuestasString[i])));
                    break;
                case 3: preguntas.add(new Pregunta(preguntasString[i],respuestasString[i]));
                    break;
            }

        }
    }

    //corrige al hacer click en enviar ;)
    public void enviarRsp(View v ){
        rspEscrita = (EditText) findViewById(R.id.rsp_string);
        String respuesta_user=rspEscrita.getText().toString();
        try{
            int ru_in=Integer.parseInt(respuesta_user);
            if(ru_in==resp_int&&preguntas.get(posicionArray).isCan_answer()){
                preguntas.get(posicionArray).setColor("green");
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_correcto),Toast.LENGTH_SHORT).show();
                respuestasCorrectas++;
                preguntas.get(posicionArray).setPlaceHolder(respuesta_user);
            }else{
                if(preguntas.get(posicionArray).isCan_answer()&&respuesta_user.length()!=0){
                    preguntas.get(posicionArray).setColor("red");
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_incorrecto),Toast.LENGTH_SHORT).show();
                    respuestasIncorrectas++;
                    preguntas.get(posicionArray).setPlaceHolder(respuesta_user);
                }}

            }catch(Exception e){

            if(respuesta_user.toLowerCase().equals(resp_string.toLowerCase())&&preguntas.get(posicionArray).isCan_answer()){
                preguntas.get(posicionArray).setColor("green");
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_correcto),Toast.LENGTH_SHORT).show();
                respuestasCorrectas++;
                preguntas.get(posicionArray).setPlaceHolder(respuesta_user);
                preguntas.get(posicionArray).setCan_answer(false);
            }else{

                if(preguntas.get(posicionArray).isCan_answer()&&respuesta_user.length()!=0){
                    preguntas.get(posicionArray).setColor("red");
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_incorrecto),Toast.LENGTH_SHORT).show();
                    respuestasIncorrectas++;
                    preguntas.get(posicionArray).setPlaceHolder(respuesta_user);
                    preguntas.get(posicionArray).setCan_answer(false);
                }}
        }
        posicionArray=posicionArray+1;
        plantearPregunta(posicionArray);
    }
    //setea un Layout dependiendo del tipo de respuesta
    public void elegirLayout(String type){
        if(type.equals("String")){
            setContentView(R.layout.question_answer);
            rspEscrita=(EditText) findViewById(R.id.rsp_string);
            rspEscrita.setInputType(InputType.TYPE_CLASS_TEXT);

        }
        if(type.equals("int")){
            setContentView(R.layout.question_answer);
            rspEscrita=(EditText) findViewById(R.id.rsp_string);
            rspEscrita.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        if (type.equals("boolean")){
            setContentView((R.layout.question_boolean));
        }

    }


    //corrige al hacer click en falso
    public void falseMan(View v){
        boolean click= false;
        if(!click&&!resp_boolean&&preguntas.get(posicionArray).isCan_answer()){
            preguntas.get(posicionArray).setColor("green");
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_correcto),Toast.LENGTH_SHORT).show();
            respuestasCorrectas++;
        }else{
            if(preguntas.get(posicionArray).isCan_answer()){
                preguntas.get(posicionArray).setColor("red");
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_incorrecto),Toast.LENGTH_SHORT).show();
                respuestasIncorrectas++;
            }
        }
        preguntas.get(posicionArray).setCan_answer(false);
        posicionArray=posicionArray+1;
        plantearPregunta(posicionArray);
    }

    //el metodo plantea la pregunta y setea la respuesta en una variable "momentanea"
    public void plantearPregunta(int nmPregunta){
        if(nmPregunta<preguntas.size()){
            String type=preguntas.get(nmPregunta).getType();
            elegirLayout(type);
            if(nmPregunta==0){
                Button b = (Button) findViewById(R.id.btn_prev);
                b.setVisibility(View.INVISIBLE);}

            txt_pregunta=(TextView)findViewById(R.id.question);
            txt_pregunta.setText(preguntas.get(nmPregunta).getPregunta());

            if(preguntas.get(nmPregunta).getColor()!=null){
                txt_pregunta.setBackgroundColor(Color.parseColor(preguntas.get(nmPregunta).getColor()));
                txt_pregunta.setTextColor(Color.BLUE);
            }

            if(type.equals("boolean")){
                this.resp_boolean=preguntas.get(nmPregunta).isRespuesta_boolean();
            }

            if(type.equals("int")){
                this.resp_int=preguntas.get(nmPregunta).getRespuesta_int();
                if(preguntas.get(posicionArray).getPlaceHolder()!=null){
                    rspEscrita = (EditText) findViewById(R.id.rsp_string);
                    rspEscrita.setHint(preguntas.get(posicionArray).getPlaceHolder());
                }
            }

            if(type.equals("String")){
                this.resp_string=preguntas.get(nmPregunta).getRespuesta_string();
                if(preguntas.get(posicionArray).getPlaceHolder()!=null){
                    rspEscrita = (EditText) findViewById(R.id.rsp_string);
                    rspEscrita.setHint(preguntas.get(posicionArray).getPlaceHolder());
                }
            }

        }else{
            setContentView(R.layout.final_result);
            txt_pregunta=(TextView)findViewById(R.id.question);
            if(respuestasCorrectas+respuestasIncorrectas==preguntas.size()){
                puntuacionTotal=respuestasCorrectas-respuestasIncorrectas;
                Button restart = (Button) findViewById(R.id.restart);
                if (puntuacionTotal<5){
                    if(puntuacionTotal<0){puntuacionTotal=1;}
                    txt_pregunta.setText(R.string.aprendido);
                    txt_pregunta.setBackgroundColor(Color.parseColor("red"));
                    txt_pregunta.setTextColor(Color.parseColor("blue"));
                    TextView punt = (TextView) findViewById(R.id.punt);
                    punt.setText("Tu puntuacion es: "+puntuacionTotal);
                    restart.setVisibility(View.VISIBLE);
                    //ha suspendido
                }else{
                    //ha aprobao
                    txt_pregunta.setText(R.string.aprobado);
                    txt_pregunta.setBackgroundColor(Color.parseColor("green"));
                    txt_pregunta.setTextColor(Color.parseColor("blue"));
                    TextView punt = (TextView) findViewById(R.id.punt);
                    punt.setText("Tu puntuacion es: "+puntuacionTotal);
                    restart.setVisibility(View.VISIBLE);
                }
            }else{
                txt_pregunta.setText(R.string.te_quedan);
            }
        }
    }

    //devuelve un int dependiendo del tipo de String que tenemos.
    public int tipoRespuesta(String s){
        if(s.equals("true"))return 1;
        if(s.equals("false"))return 1;
        try{
            Integer.parseInt(s);
            return 2;
        }catch (Exception e){
            return 3;
        }


    }
    //corrige si hacemos click en True
    public void trueMan(View v){
        boolean click=true;
        if(click&&resp_boolean&&preguntas.get(posicionArray).isCan_answer()){
            preguntas.get(posicionArray).setColor("green");
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_correcto),Toast.LENGTH_SHORT).show();
            respuestasCorrectas++;
        }else{
            preguntas.get(posicionArray).setColor("red");
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.string_incorrecto),Toast.LENGTH_SHORT).show();
            respuestasIncorrectas++;
        }
        preguntas.get(posicionArray).setCan_answer(false);
        posicionArray=posicionArray+1;

        plantearPregunta(posicionArray);
    }

    //pasa a la siguiente pregunta
    public void siguientePregunta(View v){
        posicionArray = posicionArray+1;
        plantearPregunta(posicionArray);
    }
    //inicia el quizz y crea las preguntas y demas
    public void startQuiz(View view){
        this.preguntas.clear();
        crearPreeguntas();
        this.respuestasIncorrectas=0;
        this.respuestasCorrectas=0;
        this.puntuacionTotal=0;
        plantearPregunta(posicionArray);
    }
}
