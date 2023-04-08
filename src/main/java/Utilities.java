import model.ExchangeRatesSeries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

class Utilities {

    private static final DateTimeFormatter BASE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    private Scanner scanner = new Scanner(System.in);

    char getCharSign(Character ...args) {
        boolean flag = true;
        List<Character> characters = new ArrayList<>(Arrays.asList(args));
        char sign;
        do {
            System.out.println();
            System.out.println("Enter your choice: " + characters + " ?");
            sign = scanner.next().charAt(0);
            if (characters.contains(sign)){
                flag = false;
            } else {
                System.out.println("Bad choice - try again.");
            }
        } while (flag);
        return sign;
    }

    ExchangeRatesSeries getExchangeRatesSeriesByXml(String requestURL_A) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRatesSeries.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            try {
                URL url_A = new URL(requestURL_A);
//                ExchangeRatesSeries exchangeRatesSeries_A = (ExchangeRatesSeries) unmarshaller.unmarshal(url_A);
//                return exchangeRatesSeries_A;
                return (ExchangeRatesSeries) unmarshaller.unmarshal(url_A);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    LocalDate getDate(){

        LocalDate localDate = null;
        do {
            try {
                System.out.println("Wprowadź datę (format: dd-MM-yyyy):");
                String line = scanner.next();
                localDate = LocalDate.parse(line, BASE_DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.err.println("Zły format (dd-MM-yyyy)");
            }
        } while (localDate == null);
        return localDate;
    }

    String getCode(){
        System.out.println("Wybierz walutę:");
        List<String> list = new ArrayList<>(Arrays.asList("USD", "GBP", "EUR", "CHF"));
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            characters.add((char) (i + 97));
        }
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(characters.get(i) + ") " + list.get(i) + ", ");
        }
//        System.out.print(" ");
//        for (int i = 0; i < characters.size(); i++) {
//            System.out.print(characters.get(i) + "    ");
//        }
//        System.out.println();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.print(list.get(i) + "  ");
//        }
        System.out.println();

        Character[] tab = new Character[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            tab[i] = characters.get(i);
        }
        char sign = getCharSign(tab);

        Map<Character, String> characterStringMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            characterStringMap.put(characters.get(i), list.get(i));
        }
        System.out.println(characterStringMap.get(sign));

        return characterStringMap.get(sign);
    }
}
