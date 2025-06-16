import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Leetcode 515. Find Largest Value in Each Tree Row
 * Link: https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
 */
//------------------------------------ Solution 1 -----------------------------------
public class LargestInTreeRow {
    /**
     * BFS solution - process nodes level by level using queue and find maximum at each level and add it to the result
     *
     * TC: O(n) SC: O(n)
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);

        while(!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode curr = bfsQueue.poll();
                max = Math.max(max, curr.val);

                if (curr.left != null) {
                    bfsQueue.add(curr.left);
                }
                if (curr.right != null) {
                    bfsQueue.add(curr.right);
                }
            }
            result.add(max);
        }
        return result;
    }
}

//------------------------------------ Solution 2 -----------------------------------
class LargestInTreeRow2 {
    /**
     * DFS solution - void based and level passed as value to the recursive function.
     * For each level add the current node value to result in preorder manner if result
     * does't have a value for that level, otherwise for any other node processing at the same
     * level check the maximum between current value inside result and value of current node and update
     * result
     *
     * TC: O(n) Auxiliary SC: O(1)
     * Recursive stack SC: O(h) h -> height of tree , worst case n and for complete binary tree logn
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    private void dfs(TreeNode curr, List<Integer> result, int level) {
        //base
        if (curr == null) {
            return;
        }

        //logic
        if (result.size() == level) {
            result.add(curr.val);
        } else {
            int max = Math.max(result.get(level), curr.val);
            result.set(level, max);
        }
        dfs(curr.left, result, level + 1);
        dfs(curr.right, result, level + 1);
    }
}