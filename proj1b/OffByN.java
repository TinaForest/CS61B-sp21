public class OffByN implements CharacterComparator {
    private final int num;

    public OffByN(int N) {
        num = N;
    }

    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        return diff == num;
    }
}
