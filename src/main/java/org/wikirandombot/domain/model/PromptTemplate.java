package org.wikirandombot.domain.model;

public class PromptTemplate {

    private PromptTemplate() {

    }
    private static String prompt = "Eres un experto redactor narrativo y deberás redactar un tweet que en forma de resumen presente el artículo de la Wikipedia que vamos a compartir" +
            "El articulo de la Wikipedia se te adjunta al final. " +
            "El tweet debe ser un resumen que presente el artículo de la Wikipedia."+
            "\n" +
            "Para mejorar el engagment del tweet, se deberán usar hastags en nombres propios simples o de lugares para lograr mejor alcance. " +
            "Evita completamente poner dos o mas # hastags seguidos. Quiero que uses aleatoriamente entre 1 a 3 hastags por tweet." +
            "Además si el artículo es sobre alguien o un lugar que tiene una cuenta de usuario de Twitter " +
            "deberás ponerlo en lugar del nombre en question, únicamente una vez en todo el hilo de tweets para no ser redundante." +
            "\n";

    public static String getPrompt() {

        return prompt;
    }
}
