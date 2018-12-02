package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * 
 * 55. Jump Game
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 跳字游戏
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月30日
 */
public class JumpGame {

	/**
	 * @Description:
	 * Index	0	1	2	3	4	5	6
	 * nums		9	4	2	1	0	2	0
	 * memo		U	G	B	B	B	G	G
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(canJumpByGreedy(new int[] { 9, 4, 2, 1, 0, 2, 0 }));
	}

	/**
	 * Greedy 继续优化
	 * Once we have our code in the bottom-up state, we can make one final, important observation. 
	 * From a given position, when we try to see if we can jump to a GOOD position, we only ever 
	 * use one - the first one (see the break statement). In other words, the left-most one. 
	 * If we keep track of this left-most GOOD position as a separate variable, we can avoid 
	 * searching for it in the array. Not only that, but we can stop using the array altogether.
	 * 
	 * 一旦我们的代码处于自底向上的状态，我们就可以进行最后一次重要的观察。从给定的位置，当我们试图查看是否可以
	 * 跳转到“好”位置时，我们只使用一个——第一个（参见break语句）。换句话说，最左边的一个。如果我们作为一个单独
	 * 的变量来跟踪这个最左边的GOOD位置，就可以避免在数组中搜索它。不仅如此，我们可以完全停止使用数组。
	 * 
	 * 时间复杂度 ：O(n)
	 * 空间复杂度 ：O(1)
	 * @param position
	 * @param nums
	 * @return
	 */
	public boolean canJumpByGreedy(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}

	/**
	 * 自下向上的动态规划（不通过递归的方式实现，减小方法栈的开销）
	 * Top-down to bottom-up conversion is done by eliminating recursion. In practice, this achieves better 
	 * performance as we no longer have the method stack overhead and might even benefit from some caching. 
	 * More importantly, this step opens up possibilities for future optimization. The recursion is usually 
	 * eliminated by trying to reverse the order of the steps from the top-down approach.
	 * 
	 * 自顶向下到自下而上转换是通过消除递归来完成的。在实践中，这实现了更好的性能，因为我们不再有方法堆栈开销，甚至可能从
	 * 某些缓存中受益。更重要的是，这一步为未来的优化开辟了可能。递归通常通过尝试颠倒自上而下的步骤顺序来消除。
	 * 
	 * 时间复杂度 ：O(n^2)
	 * 空间复杂度 ：O(n)
	 * @param position
	 * @param nums
	 * @return
	 */
	public boolean canJumpByDPBottomUp(int[] nums) {
		Index[] memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;

		for (int i = nums.length - 2; i >= 0; i--) {
			int furthestJump = Math.min(i + nums[i], nums.length - 1);
			for (int j = i + 1; j <= furthestJump; j++) {
				if (memo[j] == Index.GOOD) {
					memo[i] = Index.GOOD;
					break;
				}
			}
		}

		return memo[0] == Index.GOOD;
	}

	/**
	 * 自顶向下动态规划
	 * Top-down Dynamic Programming can be thought of as optimized backtracking. It relies on the observation 
	 * that once we determine that a certain index is good / bad, this result will never change. 
	 * This means that we can store the result and not need to recompute it every time.
	 * 
	 * 自顶向下的动态编程可以被认为是优化回溯。它依赖于这样的观察，即一旦我们确定某个指标是好的/坏的，这个结果就永远不会改变。
	 * 这意味着我们可以存储结果，而不需要每次重新计算它。
	 * 
	 * 时间复杂度 ：O(n^2)
	 * 空间复杂度 ：O(2n) -> O(n)
	 * @param position
	 * @param nums
	 * @return
	 */
	enum Index {
		GOOD, BAD, UNKNOWN
	}

	Index[] memo;

	public boolean canJumpFromPositionByDPTopDown(int position, int[] nums) {
		if (memo[position] != Index.UNKNOWN) {
			return memo[position] == Index.GOOD ? true : false;
		}

		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
			if (canJumpFromPositionByDPTopDown(nextPosition, nums)) {
				memo[position] = Index.GOOD;
				return true;
			}
		}

		memo[position] = Index.BAD;
		return false;
	}

	public boolean canJumpByDP(int[] nums) {
		memo = new Index[nums.length];
		for (int i = 0; i < memo.length; i++) {
			memo[i] = Index.UNKNOWN;
		}
		memo[memo.length - 1] = Index.GOOD;
		return canJumpFromPositionByDPTopDown(0, nums);
	}

	/**
	 * 回溯法，递归调用，每个都判断
	 * This is the inefficient solution where we try every single jump pattern that takes us from the 
	 * first position to the last. We start from the first position and jump to every index that is reachable. 
	 * We repeat the process until last index is reached. When stuck, backtrack.
	 * 
	 * 这是低效的解决方案，我们尝试从第一个位置到最后一个位置的每个单跳模式。我们从第一个位置开始，跳转到可到达的每个索引。
	 * 我们重复这个过程直到达到最后一个索引为止。当卡住，回溯。
	 * 
	 * 时间复杂度 ：O(2^n)
	 * 空间复杂度 ：O(n)
	 * @param position
	 * @param nums
	 * @return
	 */
	public boolean canJumpFromPositionByBacktracking(int position, int[] nums) {
		if (position == nums.length - 1) {
			return true;
		}

		// 取将要调的长度跟字符串长度的最小值
		int furthestJump = Math.min(position + nums[position], nums.length - 1);
		// 递归跳字游戏
		// for (int nextPosition = position + 1; nextPosition <= furthestJump;
		// nextPosition++) {
		// 优化，做最大的跳跃
		for (int nextPosition = furthestJump; nextPosition > position; nextPosition--) {
			if (canJumpFromPositionByBacktracking(nextPosition, nums)) {
				return true;
			}
		}

		return false;
	}

	public boolean canJumpByBacktracking(int[] nums) {
		return canJumpFromPositionByBacktracking(0, nums);
	}
}
