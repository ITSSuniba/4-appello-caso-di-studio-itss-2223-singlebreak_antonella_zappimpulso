package org.example;
public class UsernameValidation {

    public static boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Nessun valore inserito");
        }

        boolean hasAlphanumericPrefix = true;
        boolean hasDot = false;
        boolean hasNumericSuffix = true;

        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);

            if (i < 3) {
                if (!Character.isLetterOrDigit(c)) {
                    hasAlphanumericPrefix = false;
                    break;
                }
            } else if (i == 3) {
                if (c != '.') {
                    return false;
                }
                hasDot = true;
            } else {
                if (!Character.isDigit(c)) {
                    hasNumericSuffix = false;
                    break;
                }
            }
        }

        return hasAlphanumericPrefix && hasDot && hasNumericSuffix;
    }
}
