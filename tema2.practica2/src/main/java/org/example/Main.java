package org.example;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement()
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}