import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Зчитуємо назви файлів від користувача
        Scanner scanner = new Scanner(System.in);

        // words.txt
        System.out.print("\nВведіть шлях до файлу зі списком слів: ");
        String wordsFilePath = scanner.nextLine();

        // text.txt
        System.out.print("\nВведіть шлях до файлу з текстом: ");
        String textFilePath = scanner.nextLine();

        System.out.print("\nВведіть шлях до файлу де будуть збережені відсортовані слова з тексту: ");
        String outputTextFilePath = scanner.nextLine();
        scanner.close();

        // Зчитуємо список слів з файлу
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(wordsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Зчитуємо текст з файлу
        StringBuilder textBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(textFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = textBuilder.toString();

        // Підраховуємо кількість входжень слів у текст
        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String word : wordsList) {
            int count = 0;
            int index = text.indexOf(word);
            while (index != -1) {
                count++;
                index = text.indexOf(word, index + 1);
            }
            wordCountMap.put(word, count);
        }

        // Сортуємо список слів за кількістю входжень
        List<Map.Entry<String, Integer>> wordCountList = new ArrayList<>(wordCountMap.entrySet());
        wordCountList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Виводимо відсортований список слів з кількістю входжень в консоль
        System.out.println("Список слів з кількістю входжень:");
        for (Map.Entry<String, Integer> entry : wordCountList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Видаляємо дублікати з тексту
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(text.split("\\P{L}+")));

        // Виводимо відсортований список слів з тексту в файл
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputTextFilePath))) {
            List<String> sortedWords = new ArrayList<>(uniqueWords);
            Collections.sort(sortedWords);
            for (String word : sortedWords) {
                writer.println(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Список слів з тексту виведено у файл '" + outputTextFilePath + "'.");
    }
}
