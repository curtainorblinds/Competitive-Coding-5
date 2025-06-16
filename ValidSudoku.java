import java.util.HashSet;
import java.util.Set;

/**
 * Leetcode 36. Valid Sudoku
 * Link: https://leetcode.com/problems/valid-sudoku/description/
 */
public class ValidSudoku {
    /**
     * Brute force solution - traverse entire board row by row and use 3 types of Set to check if value at
     * current cell already present in that row, column or box, if present return false
     * otherwise add that value to corresponding row, column and box hashSets and repeat
     *
     * TC: O(mn) SC: O(mn)
     */
    public boolean isValidSudoku(char[][] board) {
        Set<Integer>[] rowSet = new HashSet[9]; //Space O(mn)
        Set<Integer>[] colSet = new HashSet[9]; //Space O(mn)
        Set<Integer>[] boxSet = new HashSet[9]; //Space O(mn)

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) { //Time O(mn)
                if (board[i][j] == '.') {
                    continue;
                }
                int val = board[i][j] - '0';
                int boxNo = getBoxNumber(i, j);

                if ((rowSet[i] != null && rowSet[i].contains(val)) || (colSet[j] != null && colSet[j].contains(val))
                        || (boxSet[boxNo] != null && boxSet[boxNo].contains(val))) {
                    return false;
                }

                if (rowSet[i] == null) {
                    rowSet[i] = new HashSet<>();
                }
                rowSet[i].add(val);

                if (colSet[j] == null) {
                    colSet[j] = new HashSet<>();
                }
                colSet[j].add(val);

                if (boxSet[boxNo] == null) {
                    boxSet[boxNo] = new HashSet<>();
                }
                boxSet[boxNo].add(val);
            }
        }
        return true;
    }

    private int getBoxNumber(int i, int j) {
        return (3 * (i / 3)) + (j / 3);
    }
}
