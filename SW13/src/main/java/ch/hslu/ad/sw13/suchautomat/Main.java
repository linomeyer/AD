package ch.hslu.ad.sw13.suchautomat;

public class Main {
    public static int stateSearch(String input) {
        int i = 0;
        String state = "notFound";

        do {
            switch (state) {
                case "notFound":
                    if (input.charAt(i) == 'A') {
                        state = "A";
                    }
                    break;
                case "A":
                    if (input.charAt(i) == 'N') {
                        state = "AN";
                    } else if (input.charAt(i) != 'A') {
                        state = "notFound";
                    }
                    break;
                case "AN":
                    if (input.charAt(i) == 'A') {
                        state = "ANA";
                    } else {
                        state = "notFound";
                    }
                    break;
                case "ANA":
                    if (input.charAt(i) == 'N') {
                        state = "ANAN";
                    } else if (input.charAt(i) == 'A') {
                        state = "A";
                    } else {
                        state = "notFound";
                    }
                    break;
                case "ANAN":
                    if (input.charAt(i) == 'A') {
                        state = "ANANA";
                    } else {
                        state = "notFound";
                    }
                    break;
                case "ANANA":
                    if (input.charAt(i) == 'S') {
                        state = "ANANAS";
                    } else if (input.charAt(i) == 'A') {
                        state = "A";
                    } else if (input.charAt(i) == 'N') {
                        state = "ANAN";
                    } else {
                        state = "notFound";
                    }
                    break;
            }
            i++;
        } while (!state.equals("ANANAS") && i < input.length());

        if (state.equals("ANANAS")) {
            return i - "ANANAS".length();
        }

        return -1;

    }
}
