import java.util.*;

class AsciiCharSequence implements CharSequence {

    private final byte[] byteArray;
    // implementation
    public AsciiCharSequence(byte[] a) {
        this.byteArray = a;
    }

    public int length() {
        return this.byteArray.length;
    }

    public char charAt(int index) {
        return (char) this.byteArray[index];
    }

    public CharSequence subSequence(int from, int to) {
        // Check exceptions
        // Return empty sequence if the indexes are the same
        if (from == to) {
            return new AsciiCharSequence(new byte[0]);
        }

        int length = to - from;

        byte[] returnByteArray = Arrays.copyOfRange(this.byteArray, from, to);

        return new AsciiCharSequence(returnByteArray);
    }

    public String toString() {
        return new String(this.byteArray);
    }
}