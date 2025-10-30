package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Client implements Subscriber {

    /**
     * Metodo main per testare la lettura di un file e l'iterazione inversa.
     */
    public static void main(String[] args) {
        MyList<String> fileLines = new MyList<>();
        String fileName = "input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            System.out.println("...Leggendo il file " + fileName + "...");

            while ((line = reader.readLine()) != null) {
                fileLines.addElement(line);
            }

            System.out.println("File letto. Trovate " + fileLines.length() + " righe.");

        } catch (IOException e) {
            System.err.println("Errore durante la lettura del file '" + fileName + "': " + e.getMessage());
            return;
        }

        System.out.println("\n--- Stampo il file in ordine inverso ---");

        MyIterator<String> backwardIterator = fileLines.getBackwardIterator();

        while (backwardIterator.hasMoreElements()) {
            String fileLine = backwardIterator.nextElement();
            System.out.println(fileLine);
        }

        System.out.println("--- Fine ---");
    }

    @Override
    public void update() {
        System.out.println("Notify arrived!");
    }
}