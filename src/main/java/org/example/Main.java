package org.example;

import org.example.services.FileManager;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gerenciador de Arquivos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        FileManager fileManager = new FileManager(frame);

        String[] imageFilters = {"csv", "xlsx"};
        List<File> images = fileManager.selectFiles(false, imageFilters, "Relatorio");
        fileManager.showSelectedFiles(images);

        System.exit(0);
    }
}