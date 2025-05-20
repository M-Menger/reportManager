package org.example.services;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private JFrame parentFrame;
    private String defaultDirectory;

    public FileManager(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.defaultDirectory = System.getProperty("user.home") + File.separator + "Downloads";
    }

    public FileManager(JFrame parentFrame, String initialDirectory) {
        this.parentFrame = parentFrame;
        this.defaultDirectory = initialDirectory;
    }

    public List<File> selectFiles(boolean allowMultipleSelection, String[] fileFilters, String filterDescription) {
        List<File> selectedFiles = new ArrayList<>();

        JFileChooser fileChooser = createFileChooser(allowMultipleSelection, fileFilters, filterDescription);

        int returnValue = fileChooser.showOpenDialog(parentFrame);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            if (allowMultipleSelection) {
                File[] files = fileChooser.getSelectedFiles();
                for (File file : files) {
                    selectedFiles.add(file);
                }
            } else {
                selectedFiles.add(fileChooser.getSelectedFile());
            }
        }

        return selectedFiles;
    }

    private JFileChooser createFileChooser(boolean allowMultipleSelection, String[] fileFilters, String filterDescription) {
        JFileChooser fileChooser = new JFileChooser();

        // Configura o diretório inicial
        File initialDir = new File(defaultDirectory);
        if (initialDir.exists()) {
            fileChooser.setCurrentDirectory(initialDir);
        }

        // Configura seleção múltipla
        fileChooser.setMultiSelectionEnabled(allowMultipleSelection);

        // Adiciona filtros se especificado
        if (fileFilters != null && fileFilters.length > 0) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    filterDescription != null ? filterDescription : "Arquivos permitidos",
                    fileFilters
            );
            fileChooser.setFileFilter(filter);
        }

        // Configurações adicionais
        fileChooser.setDialogTitle("Selecione os arquivos");
        fileChooser.setApproveButtonText("Selecionar");

        return fileChooser;
    }

    /**
     * Método auxiliar para mostrar os arquivos selecionados em uma mensagem
     */
    public void showSelectedFiles(List<File> files) {
        if (files == null || files.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Nenhum arquivo foi selecionado.",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder message = new StringBuilder("Arquivos selecionados:\n");
        for (File file : files) {
            message.append("- ").append(file.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(parentFrame,
                message.toString(),
                "Seleção Confirmada", JOptionPane.INFORMATION_MESSAGE);
    }
}