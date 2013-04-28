package org.jlib.test.junit.matchers.text;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.jlib.core.Wrapper;

import static org.jlib.core.text.CharSequenceTraverser.iterable;

public class HasFixedWidthColumnValue
extends TypeSafeMatcher<String> {

    private static class Column {

        private final int beginIndex;

        private final int endIndex;

        Column(int beginIndex, int endIndex) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        int getBeginIndex() {
            return beginIndex;
        }

        int getEndIndex() {
            return endIndex;
        }
    }

    private static class Index
    extends Wrapper<Integer> {

        private Index(int value) {
            super(value);
        }
    }

    private static class ColumnWidth
    extends Wrapper<Integer> {

        private ColumnWidth(int value) {
            super(value);
        }
    }

    @Factory
    public static HasFixedWidthColumnValue hasValue(Object expectedColumnValue, Column column) {
        return new HasFixedWidthColumnValue(expectedColumnValue, column.getBeginIndex(), column.getEndIndex());
    }

    @Factory
    public static Column inColumn(int columnBeginIndex, int columnEndIndex) {
        return new Column(columnBeginIndex, columnEndIndex);
    }

    @Factory
    public static Column inColumn(Index columnBeginIndex, Index columnEndIndex) {
        return new Column(columnBeginIndex.get(), columnEndIndex.get());
    }

    @Factory
    public static Column inColumn(Index columnBeginIndex, ColumnWidth columnWidth) {
        return new Column(columnBeginIndex.get(), columnBeginIndex.get() + columnWidth.get() - 1);
    }

    @Factory
    public static Index beginningAtIndex(int columnBeginIndex) {
        return new Index(columnBeginIndex);
    }

    @Factory
    public static Index endingAtIndex(int columnEndIndex) {
        return new Index(columnEndIndex);
    }

    @Factory
    public static Index fromIndex(int columnBeginIndex) {
        return new Index(columnBeginIndex);
    }

    @Factory
    public static Index toIndex(int columnEndIndex) {
        return new Index(columnEndIndex);
    }

    @Factory
    public static ColumnWidth withWidth(int columnWidth) {
        return new ColumnWidth(columnWidth);
    }

    private static boolean isBlank(String string) {
        for (char character : iterable(string))
            // only the real space character is relevant, tabs and newline characters are not
            if (character != ' ')
                return false;
        return true;
    }

    private final Object expectedValue;

    private final int columnBeginIndex;

    private final int columnEndIndex;

    private boolean columnStartsWithValue;

    private boolean columnBlankAfterValue;

    private String actualValue;

    private String actualStringAfterValue;

    public HasFixedWidthColumnValue(Object expectedValue, int columnBeginIndex, int columnEndIndex) {
        this.expectedValue = expectedValue;
        this.columnBeginIndex = columnBeginIndex;
        this.columnEndIndex = columnEndIndex;
    }

    @Override
    public boolean matchesSafely(String containingString) {
        String expectedColumnValueString = expectedValue != null ? expectedValue.toString() : "";

        actualValue =
            containingString.substring(columnBeginIndex, columnBeginIndex + expectedColumnValueString.length());

        columnStartsWithValue = actualValue.equals(expectedColumnValueString);
        actualStringAfterValue =
            containingString.substring(columnBeginIndex + expectedColumnValueString.length(), columnEndIndex + 1);
        columnBlankAfterValue = isBlank(actualStringAfterValue);

        return columnStartsWithValue && columnBlankAfterValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("string containing ");
        description.appendValue(expectedValue);
        description.appendText(" in column [" + columnBeginIndex + ", " + columnEndIndex + "]");

        // TODO: using hamcrest 1.2, move the following lines to the new method describeMismatchSafely

        // if column doesn't begin with the expected value
        if (!columnStartsWithValue) {
            description.appendText(", column does not begin with the expected value but with ");
            description.appendValue(actualValue);
        }

        // if column DOES begin with the expected value but is not followed by blanks
        else if (!columnBlankAfterValue) {
            description.appendText(", value is correct but is not followed by blanks");
            description.appendValue(actualStringAfterValue);
        }
    }
}