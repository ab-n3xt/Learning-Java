class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code here
        StringBuilder sb = new StringBuilder();

        for (char i = 'A'; i <= 'Z'; i++) {
            sb.append(i);
            if (i != 'Z') {
                sb.append(' ');
            }
        }

        return sb;
    }
}