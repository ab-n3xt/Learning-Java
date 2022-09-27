import java.util.function.UnaryOperator;

class PrefixSuffixOperator {

    public static final String PREFIX = "__pref__";
    public static final String SUFFIX = "__suff__";

    public static UnaryOperator<String> operator = (str) -> {
        while (str.startsWith(" ")) {
            str = str.substring(1);
        }

        while (str.endsWith(" ")) {
            str = str.substring(0, str.length()-1);
        }

        return PREFIX + str + SUFFIX;
    };
}