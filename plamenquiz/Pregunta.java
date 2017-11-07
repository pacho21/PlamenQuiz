package com.example.pacho.plamenquiz;

/**
 * Created by Pacho on 31/10/2017.
 */

public class Pregunta {
    //declaracion de atributos
    protected String pregunta;
    protected String respuesta_string;
    protected int respuesta_int;
    protected boolean respuesta_boolean,can_answer;
    protected String type;
    protected String color=null;
    protected String placeHolder=null;


    public Pregunta(String pregunta, String res) {
        this.pregunta = pregunta;
        this.respuesta_string = res;
        this.type="String";
        this.can_answer=true;
    }

    public Pregunta (String pregunta, int res){
        this.pregunta= pregunta;
        this.respuesta_int = res;
        this.type="int";
        this.can_answer=true;
    }
    public Pregunta(String pregunta, boolean res){
        this.pregunta = pregunta;
        this.respuesta_boolean=res;
        this.type="boolean";
        this.can_answer=true;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta_string() {
        return respuesta_string;
    }

    public void setRespuesta_string(String respuesta_string) {
        this.respuesta_string = respuesta_string;
    }

    public int getRespuesta_int() {
        return respuesta_int;
    }

    public void setRespuesta_int(int respuesta_int) {
        this.respuesta_int = respuesta_int;
    }

    public boolean isRespuesta_boolean() {
        return respuesta_boolean;
    }

    public void setRespuesta_boolean(boolean respuesta_boolean) {
        this.respuesta_boolean = respuesta_boolean;
    }

    public String getType() {
        return this.type;
    }

    public boolean isCan_answer() {
        return can_answer;
    }

    public void setCan_answer(boolean can_answer) {
        this.can_answer = can_answer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaceHolder(){return this.placeHolder;}
    public void setPlaceHolder(String place){this.placeHolder=place;}


}
