package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int stateFileChooser = fileChooser.showOpenDialog(view);
        if (stateFileChooser == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.read(fileReader, document, 0);
                view.resetUndo();
            } catch (FileNotFoundException e) {
                ExceptionHandler.log(e);
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void saveDocument(){
        view.selectHtmlTab();
        if (currentFile == null){
            saveDocumentAs();
        }
        else {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(fileWriter, document, 0, document.getLength());
                // Добавил закрытие потока
                fileWriter.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int stateFileChooser = fileChooser.showSaveDialog(view);
        if (stateFileChooser == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(fileWriter, document, 0, document.getLength());
                // Добавил закрытие потока
                fileWriter.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void resetDocument(){
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        document = (HTMLDocument) htmlEditorKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader reader = new StringReader(text);
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.read(reader, document, 0);
            // Добавил закрытие потока
            reader.close();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter writer = new StringWriter();
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.write(writer, document, 0, document.getLength());
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
        return writer.toString();
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }
}
