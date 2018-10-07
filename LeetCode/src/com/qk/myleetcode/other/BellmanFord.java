package com.qk.myleetcode.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @Description : 贝尔曼-福特算法（英语：Bellman–Ford algorithm），求解单源最短路径问题的一种算法
 * 迪科斯彻算法以贪心法选取未被处理的具有最小权值的节点，然后对其的出边进行松弛操作；
 * 而贝尔曼-福特算法简单地对所有边进行松弛操作，共|V|-1次，其中|V|是图的点的数量。
 * 在重复地计算中，已计算得到正确的距离的边的数量不断增加，直到所有边都计算得到了正确的路径。
 * 贝尔曼-福特算法还需要检测负权环，负权环可以无限制的降低消费，所以如果第N次仍可以降低花销，就一定存在负权环
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月7日
 */
public class BellmanFord {

	@Test
	public void MyTest() {
		Edge edge1 = new Edge(0, 1, -1);
		Edge edge2 = new Edge(1, 3, 3);
		Edge edge3 = new Edge(2, 2, 3);
		Edge edge4 = new Edge(3, 2, -1);

		List<Edge> edges = new ArrayList<>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);

		Graph graph = new Graph(4, 4, edges);
		getBellmanFord(graph);
	}

	@Test
	public void MyTest2() {
		Edge edge1 = new Edge(0, 1, 5);
		Edge edge2 = new Edge(1, 2, -7);
		Edge edge3 = new Edge(0, 2, 0);
		Edge edge4 = new Edge(1, 3, 15);
		Edge edge5 = new Edge(1, 4, 20);
		Edge edge6 = new Edge(2, 3, 30);
		Edge edge7 = new Edge(2, 4, 15);
		Edge edge8 = new Edge(3, 5, 20);
		Edge edge9 = new Edge(4, 5, 10);

		List<Edge> edges = new ArrayList<>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);

		Graph graph = new Graph(6, 9, edges);
		getBellmanFord(graph);
	}

	/**
	 * 测试负权回路
	 */
	@Test
	public void MyTest3() {
		Edge edge1 = new Edge(0, 1, 3);
		Edge edge2 = new Edge(0, 2, 3);
		Edge edge3 = new Edge(0, 3, 3);
		Edge edge4 = new Edge(1, 3, -1);
		Edge edge5 = new Edge(3, 2, -1);
		Edge edge6 = new Edge(2, 1, -1);

		List<Edge> edges = new ArrayList<>();
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);

		Graph graph = new Graph(4, 6, edges);
		getBellmanFord(graph);
	}

	public void getBellmanFord(Graph graph) {
		// 点的数量
		int V = graph.getV();
		// 边的数量
		int E = graph.getE();
		// 点的最短路径
		int[] dist = new int[V];

		// 第一步:初始化
		for (int i = 0; i < V; i++)
			dist[i] = Integer.MAX_VALUE;
		// 起点的路径为0
		dist[0] = 0;

		// 第二步：松弛操作
		for (int i = 1; i <= V - 1; i++) {
			for (int j = 0; j < E; j++) {
				int u = graph.getEdges().get(j).getSrc();
				int v = graph.getEdges().get(j).getDest();
				int weight = graph.getEdges().get(j).getWeight();
				if (dist[u] + weight < dist[v])
					dist[v] = dist[u] + weight;
			}
		}

		// 第三步： 检测负权回路. 上面的操作保证没有负权回路的存在，
		// 如果找到了更短的路径，则说明存在负权回路
		for (int i = 0; i < E; i++) {
			int u = graph.getEdges().get(i).getSrc();
			int v = graph.getEdges().get(i).getDest();
			int weight = graph.getEdges().get(i).getWeight();
			if (dist[u] + weight < dist[v])
				System.out.println("Graph contains negative weight cycle");
		}

		System.out.println(Arrays.toString(dist));
	}
}

/**
 * 表示一条边
 */
class Edge {
	private int src, dest, weight;

	public Edge(int src, int dest, int weight) {
		super();
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}

	public int getSrc() {
		return src;
	}

	public void setSrc(int src) {
		this.src = src;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}

/**
 * 带权值的有向图
 */
class Graph {
	// V 顶点的数量， E 边的数量
	private int V, E;

	// 用边的集合 表示一个图
	private List<Edge> edges = new ArrayList<>();

	public Graph(int v, int e, List<Edge> edges) {
		super();
		V = v;
		E = e;
		this.edges = edges;
	}

	public int getV() {
		return V;
	}

	public void setV(int v) {
		V = v;
	}

	public int getE() {
		return E;
	}

	public void setE(int e) {
		E = e;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

}
