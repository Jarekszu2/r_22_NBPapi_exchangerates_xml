import model.ExchangeRatesSeries;
import model.Rate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {

    private static final String BASE_NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/";
    private static final DateTimeFormatter BASE_DATE_TIME_NBP_API_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static void main(String[] args) {

        Utilities utilities = new Utilities();

        System.out.println();
        boolean flag_1 = true;
        char sign;
        System.out.println("Aplikacja pobiera serię kursów pojedynczej waluty, dla określonego");
        System.out.println("typu tabeli oraz symbolu waluty.");
        System.out.println();
        do {
            System.out.println();
            System.out.println("Wybierz: \n" +
                    " a) Tabela A kursów średnich walut obcych\n" +
                    " b) Tabela C kursów kupna i sprzedaży walut obcych\n" +
                    " q) wyjście");

            sign = utilities.getCharSign('a', 'b', 'q');

            switch (sign) {
                case 'a' :
                    System.out.println("Tabela A kursów średnich walut obcych.");

                    System.out.println();
                    System.out.println("Podaj datę początku okresu:");
                    LocalDate dateStart = utilities.getDate();
                    System.out.println(dateStart.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    System.out.println();
                    System.out.println("Podaj datę końca okresu:");
                    LocalDate dateEnd = utilities.getDate();
                    System.out.println(dateEnd.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    if (dateEnd.isBefore(dateStart)) {
                        System.out.println();
                        System.err.println("Zmiana kolejnosci dat!");
                        LocalDate date;
                        date = dateStart;
                        dateStart = dateEnd;
                        dateEnd = date;
                        System.err.println(dateStart.format(BASE_DATE_TIME_NBP_API_FORMATTER));
                        System.err.println(dateEnd.format(BASE_DATE_TIME_NBP_API_FORMATTER));
                        System.out.println();
                    }

                    String code = utilities.getCode();
                    // http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
                    String requestURL_A = "http://api.nbp.pl/api/exchangerates/rates/" + "a" + "/" + code + "/" + dateStart.format(BASE_DATE_TIME_NBP_API_FORMATTER) + "/" + dateEnd.format(BASE_DATE_TIME_NBP_API_FORMATTER) + "/?format=" + "xml";
                    System.out.println(requestURL_A);

                    ExchangeRatesSeries exchangeRatesSeries_A = utilities.getExchangeRatesSeriesByXml(requestURL_A);
                    if (exchangeRatesSeries_A != null) {
                        List<Rate> rateList = exchangeRatesSeries_A.getRates();
                        rateList.forEach(System.out::println);
                    } else {
                        System.err.println("Brak danych.");
                    }
                    break;
                case 'b' :
                    System.out.println("B");
                    break;
                case 'q' :
                    flag_1 = false;
            }
        } while (flag_1);
    }
}
