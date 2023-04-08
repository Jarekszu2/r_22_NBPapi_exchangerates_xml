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
                    LocalDate dateStart_A = utilities.getDate();
                    System.out.println(dateStart_A.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    System.out.println();
                    System.out.println("Podaj datę końca okresu:");
                    LocalDate dateEnd_A = utilities.getDate();
                    System.out.println(dateEnd_A.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    if (dateEnd_A.isBefore(dateStart_A)) {
                        System.out.println();
                        System.err.println("Zmiana kolejnosci dat!");
                        LocalDate date;
                        date = dateStart_A;
                        dateStart_A = dateEnd_A;
                        dateEnd_A = date;
                        System.err.println(dateStart_A.format(BASE_DATE_TIME_NBP_API_FORMATTER));
                        System.err.println(dateEnd_A.format(BASE_DATE_TIME_NBP_API_FORMATTER));
                        System.out.println();
                    }

                    String code_A = utilities.getCode();
                    // http://api.nbp.pl/api/exchangerates/rates/{table}/{code}/{startDate}/{endDate}/
//                    String requestURL_A = "http://api.nbp.pl/api/exchangerates/rates/" + "a" + "/" + code + "/" + dateStart_A.format(BASE_DATE_TIME_NBP_API_FORMATTER) + "/" + dateEnd_A.format(BASE_DATE_TIME_NBP_API_FORMATTER) + "/?format=" + "xml";
                    String requestURL_A = BASE_NBP_API_URL
                            .replace("{table}", "a")
                            .replace("{code}", code_A)
                            .replace("{startDate}", dateStart_A.format(BASE_DATE_TIME_NBP_API_FORMATTER))
                            .replace("{endDate}", dateEnd_A.format(BASE_DATE_TIME_NBP_API_FORMATTER))
                            + "/?format=" + "xml";
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

                    System.out.println();
                    System.out.println("Podaj datę początku okresu:");
                    LocalDate dateStart_B = utilities.getDate();
                    System.out.println(dateStart_B.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    System.out.println();
                    System.out.println("Podaj datę końca okresu:");
                    LocalDate dateEnd_B = utilities.getDate();
                    System.out.println(dateEnd_B.format(BASE_DATE_TIME_NBP_API_FORMATTER));

                    if (dateStart_B.isAfter(dateEnd_B)) {
                        System.out.println(" ");
                        System.err.println("Zmiana kolejnosci dat!");
                        LocalDate date;
                        date = dateStart_B;
                        dateStart_B = dateEnd_B;
                        dateEnd_B = date;
                        System.err.println(dateStart_B.format(BASE_DATE_TIME_NBP_API_FORMATTER) + "");
                        System.err.println(dateEnd_B.format(BASE_DATE_TIME_NBP_API_FORMATTER));
                        System.out.println(" ");
                    }

                    String code_B = utilities.getCode();
                    String requestURL_B = BASE_NBP_API_URL
                            .replace("{table}", "c")
                            .replace("{code}", code_B)
                            .replace("{startDate}", dateStart_B.format(BASE_DATE_TIME_NBP_API_FORMATTER))
                            .replace("{endDate}", dateEnd_B.format(BASE_DATE_TIME_NBP_API_FORMATTER))
                            + "/?format=" + "xml";
                    System.out.println(requestURL_B);

                    ExchangeRatesSeries exchangeRatesSeries_B = utilities.getExchangeRatesSeriesByXml(requestURL_B);
                    if (exchangeRatesSeries_B != null) {
                        List<Rate> rateList = exchangeRatesSeries_B.getRates();
                        rateList.forEach(System.out::println);
                    } else {
                        System.err.println("Brak danych.");
                    }

                    break;
                case 'q' :
                    flag_1 = false;
            }
        } while (flag_1);
    }
}
