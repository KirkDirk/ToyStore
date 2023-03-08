package intefaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import models.Toy1;

public class DBActions implements IDBActions {
    /** Путь к файлу, с котороым проводятся текущие действия */
    private String fileName;
    
    
    public DBActions(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int getLastIDToy() throws IOException {
        File file = new File(this.fileName);
        String lastLine = ReadLastLine(file);
        int numberOfNotes = 0;
        if (lastLine != null) {
            String[] numbers = lastLine.split(";");            
            numberOfNotes = Integer.parseInt(numbers[0]);
            }
        return numberOfNotes;
    }

    @Override
    public void saveNewToyToStorage(Toy1 toy) throws FileNotFoundException, IOException {
        String toyString = convertToyToString(toy);
        FileWriter wrtr = new FileWriter(this.fileName, true);
        BufferedWriter buffWrtr = new BufferedWriter(wrtr);
        buffWrtr.write(toyString);
        buffWrtr.flush();
        buffWrtr.close();        
    }

    /**
     * Преобразовываем класс Игрушки в строку
     * @param toy передаваемый для преобразования экземпляр Игрушки
     * @return String в формате "ID;Name;Количество;Частота выпадения"
     */
    private String convertToyToString(Toy1 toy){
        String toyToString = toy.getIdToy() + ";"
        + toy.getNameToy() + ";"
        + toy.getCountToy() + ";"
        + toy.getFrqDlvrToy() + "\n";
        return toyToString;
    }

    /**
     * Метод получения последней строки из базы розыгрыша
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static String ReadLastLine(File file) throws FileNotFoundException, IOException {
        String result = null;
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long startIdx = file.length();
            while (startIdx >= 0 && (result == null || result.length() == 0)) {
                raf.seek(startIdx);
                if (startIdx > 0) 
                    raf.readLine();
                result = raf.readLine();
                startIdx--;
            }
        }
        return result;
    }

    @Override
    public List<Toy1> getAllToys() throws FileNotFoundException, IOException {
        List<Toy1> allToys = new ArrayList<>();
        List<String> getAllLines = new ArrayList<>();
        getAllLines = Files.readAllLines(Paths.get(this.fileName));
        for (String line : getAllLines) {
            if (line != null) {   
                allToys.add(lineToToy1(line));            
            }   
        }
        return allToys;        
    }

    /**
     * Преобразовываем считанную из базы розыгрыша строку в класс Игрушки
     * @param line
     * @return
     */
    private Toy1 lineToToy1(String line) {
        String[] lines = line.split(";");
        return new Toy1(Integer.parseInt(lines[0]), 
                        lines[1], 
                        Integer.parseInt(lines[2]),
                        Integer.parseInt(lines[3]));
    }

    /**
     * Преобразовываем экземпляр класса Игрушки в строку для записи в базу
     * @param toy
     * @return
     */
    private String toyToString(Toy1 toy){
        String line = toy.getIdToy() + ";" +
                        toy.getNameToy() + ";" +
                        toy.getCountToy() + ";" +
                        toy.getFrqDlvrToy();
        return line;
    }

    @Override
    public void saveAllToys(List<Toy1> allToys) throws FileNotFoundException, IOException{
        FileWriter wrtr = new FileWriter(this.fileName, false);
        BufferedWriter buffWrtr = new BufferedWriter(wrtr);
        String allLines = "";
        for (Toy1 toy1 : allToys) {
            allLines = allLines + toyToString(toy1) + "\n";
        }
        buffWrtr.write(allLines);
        buffWrtr.flush();
        buffWrtr.close();        

    }

       
}
