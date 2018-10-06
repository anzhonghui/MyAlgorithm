package com.qk.myleetcode.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class Dijkstra {

	/**
	 * 定义两个节点之间的权重 start -> a -> 1(权重); start -> b -> 2
	 */
	private Map<String, Map<String, Integer>> nodes = new HashMap<>();

	/**
	 * 存放节点的关系
	 */
	private Map<String, String> parents = new HashMap<>();

	/**
	 * 存放节点的最短开销.除了起始点的邻居节点有初始值，其他的初始值都为无穷大
	 */
	private Map<String, Integer> costs = new HashMap<>();

	/**
	 * 存放哪些节点被处理过，顺序为最终的路径
	 */
	private List<String> processed = new ArrayList<>();

	@Test
	public void MyTest() {
		Map<String, Integer> maps = new HashMap<>();
		maps.put("a", 6);
		maps.put("b", 2);
		nodes.put("start", maps);

		Map<String, Integer> maps2 = new HashMap<>();
		maps2.put("end", 1);
		nodes.put("a", maps2);

		Map<String, Integer> maps3 = new HashMap<>();
		maps3.put("a", 3);
		maps3.put("end", 5);
		nodes.put("b", maps3);

		Map<String, Integer> maps4 = new HashMap<>();
		nodes.put("end", maps4);

		costs.put("a", 6);
		costs.put("b", 2);
		costs.put("end", Integer.MAX_VALUE);

		parents.put("a", "start");
		parents.put("b", "start");
		parents.put("end", null);

		findShortestPath();

		System.out.println(processed.toString());
	}

	private void findShortestPath() {
		processed.add("start");
		// 查找最低消费的节点
		String node = findLowestCostNode();
		// 递归条件
		while (node != null) {
			// 获取当前节点的最低消费
			Integer cost = costs.get(node);
			// 获取当前节点的所有邻居
			Map<String, Integer> neighbors = nodes.get(node);
			// 遍历邻居
			for (Entry<String, Integer> map : neighbors.entrySet()) {
				// 当前节点到邻居的权重+当前节点的权重
				Integer newCost = cost + map.getValue();
				// 更新最低权重
				if (costs.get(map.getKey()) > newCost) {
					costs.put(map.getKey(), newCost);
					parents.put(map.getKey(), node);
				}
			}
			// 添加已经处理的点
			processed.add(node);
			// 继续查找
			node = findLowestCostNode();
		}
	}

	/**
	 * 查找消费最低的节点，遍历消费里面所有的记录，找出最低消费的节点
	 * 
	 * @return
	 */
	private String findLowestCostNode() {
		Integer lowestCost = Integer.MAX_VALUE;
		String lowestCostNode = null;

		for (Entry<String, Integer> map : costs.entrySet()) {
			Integer currCost = map.getValue();
			if (currCost < lowestCost && !processed.contains(map.getKey())) {
				lowestCost = currCost;
				lowestCostNode = map.getKey();
			}
		}

		return lowestCostNode;
	}
}
