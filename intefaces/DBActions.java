package intefaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            //System.out.println(String.format("Получили ласт %s", numberOfNotes));
            }
        return numberOfNotes;
    }

    @Override
    public void saveNewToyToStorage(Toy1 toy) throws FileNotFoundException, IOException {
        String toyString = convertToyToString(toy);
        FileWriter wrtr = new FileWriter(this.fileName, true);
        BufferedWriter buffWrtr = new BufferedWriter(wrtr);
        //System.out.println(this.fileName);
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
    
}
