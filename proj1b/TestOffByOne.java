import org.junit.Test;

import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('&', '%'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('A', '@'));
        assertFalse(offByOne.equalChars('a', 'a'));
/*        Test Failed!
                No valid tests caught the bug in Buggy OffByOne 4.
        at AGTestTestOffByOne.testBuggy:76 (AGTestTestOffByOne.java)
                at AGTestTestOffByOne.testBuggy4:40 (AGTestTestOffByOne.java)
                Hint: characters can be upper or lower case.*/
        assertFalse(offByOne.equalChars('B', 'c'));
        assertFalse(offByOne.equalChars('y', 'Z'));
    }

}
