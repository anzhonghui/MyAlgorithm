package com.qk.myleetcode.parallel;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Test;

public class QuickSort {
	/**
	 * 快速排序
	 * @param datas
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] datas) {
		quickSort(datas, true);
	}

	public static <T extends Comparable<? super T>> void quickSort(T[] datas, boolean async) {
		quickSort(datas, 0, datas.length - 1, async);
	}

	/**
	 * 并发快速排序
	 * @param datas
	 */
	public static <T extends Comparable<? super T>> void parallelQuickSort(T[] datas) {
		// 创建进程池
		ForkJoinPool pool = new ForkJoinPool();
		// 创建任务
		QuickSortAction<T> action = new QuickSortAction<>(datas, 0, datas.length - 1, true);
		// 向进程池中加入任务，执行任务
		pool.invoke(action);
		// 停止线程池
		pool.shutdown();
		try {
			// 进程池中所有任务结束后，关闭进程池
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}

	public static <T extends Comparable<? super T>> void quickSort(T[] datas, int low, int high) {
		quickSort(datas, low, high, true);
	}

	/**
	 * 快速排序，递归
	 * @param datas
	 * @param low
	 * @param high
	 * @param async
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] datas, int low, int high, boolean async) {
		if (low >= high)
			return;

		int pivot;
		if (async)
			pivot = partitionAsync(datas, low, high);
		else
			pivot = partitionDesc(datas, low, high);

		quickSort(datas, low, pivot - 1, async);
		quickSort(datas, pivot + 1, high, async);
	}

	/**
	 * 单线程快速排序正序
	 * @param datas
	 * @param low
	 * @param high
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partitionAsync(T[] datas, int low, int high) {
		T key = datas[low];
		while (low < high) {
			while (low < high && datas[high].compareTo(key) >= 0)
				high--;
			datas[low] = datas[high];
			while (low < high && datas[low].compareTo(key) <= 0)
				low++;
			datas[high] = datas[low];
		}
		datas[low] = key;
		return low;
	}

	/**
	 * 单线程快速排序倒叙
	 * @param datas
	 * @param low
	 * @param high
	 * @return
	 */
	private static <T extends Comparable<? super T>> int partitionDesc(T[] datas, int low, int high) {
		T key = datas[low];
		while (low < high) {
			while (low < high && datas[high].compareTo(key) <= 0)
				high--;
			datas[low] = datas[high];
			while (low < high && datas[low].compareTo(key) >= 0)
				low++;
			datas[high] = datas[low];
		}
		datas[low] = key;
		return low;
	}

	/**
	 * @Description : 并发编程的快速排序任务
	 * ---------------------------------
	 * @Author : huihui
	 * @Date : Create in 2018年11月5日
	 */
	static class QuickSortAction<T extends Comparable<? super T>> extends RecursiveAction {

		private T[] datas;
		private int low;
		private int high;
		private boolean async;

		public QuickSortAction(T[] datas, int low, int high, boolean async) {
			this.datas = datas;
			this.low = low;
			this.high = high;
			this.async = async;
		}

		@Override
		protected void compute() {
			if (low >= high)
				return;

			if (low + 2 >= high) {
				int center = (low + high) / 2;
				if (async) {
					if (datas[low].compareTo(datas[center]) > 0)
						swap(datas, low, center);
					if (datas[center].compareTo(datas[high]) > 0) {
						swap(datas, center, high);
						if (datas[low].compareTo(datas[center]) > 0)
							swap(datas, low, center);
					}
				} else {
					if (datas[low].compareTo(datas[center]) < 0)
						swap(datas, low, center);
					if (datas[center].compareTo(datas[high]) < 0) {
						swap(datas, center, high);
						if (datas[low].compareTo(datas[center]) < 0)
							swap(datas, low, center);
					}
				}
				return;
			}

			int pivot;
			if (async)
				pivot = partitionAsync(datas, low, high);
			else
				pivot = partitionDesc(datas, low, high);

			// 将分组后的内容继续加入进程池
			QuickSortAction<T> sortLeft = new QuickSortAction<>(datas, low, pivot - 1, async);
			QuickSortAction<T> sortRight = new QuickSortAction<>(datas, pivot + 1, high, async);
			invokeAll(sortLeft, sortRight);
		}

		private static void swap(Object[] datas, int a, int b) {
			Object tmp = datas[a];
			datas[a] = datas[b];
			datas[b] = tmp;
		}
	}

	static Integer[] getRandomData(int length) {
		Integer[] datas = new Integer[length];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < length; ++i)
			datas[i] = random.nextInt();
		return datas;
	}

	static <T extends Comparable<? super T>> void evaluteSortMethod(Consumer<T[]> function, T[] datas,
			String funcName) {
		long beginMillis = System.currentTimeMillis();
		function.accept(datas);
		long usedMillis = System.currentTimeMillis() - beginMillis;
		System.out.println(
				funcName + " 使用了" + (usedMillis / 1000) + "秒" + (usedMillis % 1000) + "毫秒来排序" + datas.length + "个数据");
		if (!isSorted(datas))
			System.err.println(funcName + "排序错误");
	}

	static <T extends Comparable<? super T>> long evaluteSortMethod(Consumer<T[]> function, T[] datas) {
		long beginMillis = System.currentTimeMillis();
		function.accept(datas);
		long usedMillis = System.currentTimeMillis() - beginMillis;
		if (!isSorted(datas))
			throw new IllegalStateException("算法排序错误");
		return usedMillis;
	}

	static void test(int testDataLen) {
		Integer[] src = getRandomData(testDataLen);

		Integer[] srcCopy = new Integer[src.length];
		for (int i = 0; i < src.length; ++i)
			srcCopy[i] = src[i];

		Integer[] srcCopy2 = new Integer[src.length];
		for (int i = 0; i < src.length; ++i)
			srcCopy2[i] = src[i];

		Integer[] srcCopy3 = new Integer[src.length];
		for (int i = 0; i < src.length; ++i)
			srcCopy3[i] = src[i];

		Integer[] srcCopy4 = new Integer[src.length];
		for (int i = 0; i < src.length; ++i)
			srcCopy4[i] = src[i];

		// evaluteSortMethod(MergeSort::sort, srcCopy, "mergeSort");
		evaluteSortMethod(Arrays::sort, srcCopy2, "Arrays::sort");
		evaluteSortMethod(Arrays::parallelSort, srcCopy, "Arrays::parallelSort");
		evaluteSortMethod(QuickSort::quickSort, srcCopy3, "QuickSort:quickSort");
		evaluteSortMethod(QuickSort::parallelQuickSort, srcCopy4, "QuickSort:parallelQuickSort");

	}

	static <T extends Comparable<? super T>> boolean isSorted(T[] datas) {
		for (int i = 0; i < datas.length - 1; ++i)
			if (datas[i].compareTo(datas[i + 1]) > 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		test(10);
		System.out.println("-- runtime environment --");
		System.getProperties().list(System.out);
		System.out.println("available processor num=" + Runtime.getRuntime().availableProcessors());
		System.out.println("-------------");
		test(10000000);
	}

}
