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
    /** Путь к файлу, с которым проводятся текущие действия */
    private String fileName;
    /** Путь к файлу, в который записываются победители */
    private String fileWinner = "src\\storage\\dbWinner.txt";
    /** Путь к файлу, в котором список невыданных призовых игрушек */
    private String fileDstrb = "src\\storage\\listToyDistribution.txt";
       
    /**
     * Интерфейс работы с БД
     * @param fileName - имя (путь) файла БД с разыгрываемыми Игрушками
     */
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
        saveStringToFile(toyString, this.fileName, true);               
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
     * @return String
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
     * @return Toy1
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
     * @return String
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
        String allLines = "";
        for (Toy1 toy1 : allToys) {
            allLines = allLines + toyToString(toy1) + "\n";
        }
        saveStringToFile(allLines, this.fileName, false);
    }

    @Override
    public void deleteToy(int idRemovedToy) throws FileNotFoundException, IOException {
        List<Toy1> allToys = getAllToys();
        allToys.remove(idRemovedToy-1);
        /** перезаписываем индексы игрушек после удаления и сохраняем базу */
        int id = 1;
        for (Toy1 toy : allToys) {
            toy.setIdToy(id);
            id++;
        }
        saveAllToys(allToys);        
    }

    @Override
    public void addWinner(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException {
        String addWinnerString = java.time.ZonedDateTime.now().toString() + ";" + 
                            prizeToy.getNameToy() + ";" +
                            nameWinner + "\n";
        saveStringToFile(addWinnerString, this.fileWinner, true);
    }

    @Override
    public void addToyForDelivery(String nameToy, String nameWinner) throws FileNotFoundException, IOException {
        String stringData = nameWinner + ";" + 
                            nameToy + ";" + 
                            java.time.ZonedDateTime.now().toString() + "\n";
        saveStringToFile(stringData, this.fileDstrb, true);               
        //throw new UnsupportedOperationException("Unimplemented method 'addToyForDelivery'");
    }

    @Override
    public void saveStringToFile(String stringAnyData, String fileName, boolean appendMode) throws FileNotFoundException, IOException {
        FileWriter wrtr = new FileWriter(fileName, appendMode);
        BufferedWriter buffWrtr = new BufferedWriter(wrtr);
        buffWrtr.write(stringAnyData);
        buffWrtr.flush();
        buffWrtr.close(); 
        //throw new UnsupportedOperationException("Unimplemented method 'saveStringToFile'");
    }

    @Override
    public String getAllPrizeToys() throws FileNotFoundException, IOException {
        String allPrizeToys = "";
        List<String> getAllLines = new ArrayList<>();
        getAllLines = Files.readAllLines(Paths.get(this.fileDstrb));
        int counter = 1;
        for (String line : getAllLines) {
            if (line != null) {   
                String[] fromLine = line.split(";");
                allPrizeToys += "\n" + Integer.toString(counter) + ". " +
                                "Name Winner: " + fromLine[0] + "   " +
                                "Name Toy: " + fromLine[1] + "   " +
                                "Draw Date: " + fromLine[2];            
                counter++;
            }   
        }
        return allPrizeToys;        
        //throw new UnsupportedOperationException("Unimplemented method 'getAllPrizeToys'");
    }

    @Override
    public String[] getPrizeToys(int numberForDlvr) throws IOException {
        /** выгружаем все данные из файла с игрушками на выдачу в строку */
        List<String> allPrizeToys = Files.readAllLines(Paths.get(this.fileDstrb));
        /** считываем указанную строку для выдачи */
        String linePresentToy = Files.readAllLines(Paths.get(this.fileDstrb)).get(numberForDlvr-1); 
        /** удаляем подстроку в строке - вымарываем выданную игрушку из списка-строки игрушек на выдачу */
        allPrizeToys.remove(numberForDlvr-1);
        /** превращаем список в строку для записи в файл */
        String allPrizeToysWithoutPresent ="";
        for (String line : allPrizeToys) {
            allPrizeToysWithoutPresent += line + "\n";
        }
        /** перезаписывает строку игрушек на выдачу в файл со списком игрушек */
        saveStringToFile(allPrizeToysWithoutPresent, this.fileDstrb, false);
        /** разбиваем строку с выдаваемой игрушкой в массив */
        String[] arrPresentToy = linePresentToy.split(";");
        /** возвращаем массив с данными выдаваемой игрушки */
        return arrPresentToy;
        // throw new UnsupportedOperationException("Unimplemented method 'getAllPrizeToys'");
        
    }     
     
}
