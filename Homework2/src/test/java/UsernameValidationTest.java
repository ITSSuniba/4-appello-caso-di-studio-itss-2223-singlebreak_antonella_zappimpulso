import net.jqwik.api.*;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.UsernameValidation;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class UsernameValidationTest {

    //username con i primi tre elementi alfanumerici un punto e tre cifre numeriche finali
    private static final Pattern USERNAME_PATTERN = Pattern.compile(
            "^[A-Za-z0-9]{3}\\.[0-9]{3}$"
    );

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void passTestUsername(@ForAll("validUsernames") String username) {
        boolean isValid = UsernameValidation.validateUsername(username);
        assertTrue(isValid);
        String prefix = username.substring(0, 3);
        boolean startsWithLetter = Character.isLetter(prefix.charAt(0));
        boolean startsWithNumber = Character.isDigit(prefix.charAt(0));
        boolean hasDotAtFourthChar = username.charAt(3) == '.';
        boolean onlyAlphanumeric = prefix.matches("[A-Za-z0-9]+");
        if (startsWithLetter) {
            Statistics.collect("StartsWithLetter");
        }
        if (startsWithNumber) {
            Statistics.collect("StartsWithNumber");
        }
        if (hasDotAtFourthChar) {
            Statistics.collect("HasDotAtFourthChar");
        }
        if (onlyAlphanumeric) {
            Statistics.collect("OnlyAlphanumeric");
        }
    }
    @Provide
    Arbitrary<String> validUsernames() {
        return Arbitraries.strings().alpha().numeric().ofLength(3)
                .flatMap(prefix -> {
                    char dot = '.';
                    String suffix = generateNumericSuffix();
                    return Arbitraries.just(prefix + dot + suffix);
                });
    }


    //Generare un suffisso che abbia 4 cifre numeriche
    private String generateNumericSuffix() {
        return String.valueOf(Arbitraries.integers().between(1000, 9999).sample());
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void failedTestUsername(@ForAll("invalidUsernames") String username) {
        boolean isValid = UsernameValidation.validateUsername(username);
        assertFalse(isValid);
    }

    @Provide
    Arbitrary<String> invalidUsernames() {
        return Arbitraries.strings().withCharRange(' ', 'Z').ofLength(8)
                .filter(username -> username.charAt(3) != '.');
    }

    @Property
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void invalidFormatTestUsername(@ForAll("invalidFormatUsernames") String username) {
        assertThrows(IllegalArgumentException.class, () -> UsernameValidation.validateUsername(username));
    }

    @Provide
    Arbitrary<String> invalidFormatUsernames() {
        return Arbitraries.strings().ofMaxLength(0);
    }
}
