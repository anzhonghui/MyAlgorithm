package com.qk.myleetcode.parallel;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
 
/**
 * Created by violetMoon on 2016/4/13.
 */
public class MergeSort {
 
    public static <T extends Comparable<? super T>> void sort(T[] datas) {
        sort(datas, 0, datas.length - 1);
    }
 
    public static <T extends Comparable<? super T>> void sort(T[] datas, int low, int high) {
        Object[] bufs = new Object[datas.length];
        sort(datas, low, high, bufs);
    }
 
    public static <T extends Comparable<? super T>> void parallelSort(T[] datas) {
        parallelSort(datas, 0, datas.length - 1);
    }
 
    public static <T extends Comparable<? super T>> void parallelSort(T[] datas, int low, int high) {
        ForkJoinPool pool = new ForkJoinPool();
        Object[] bufs = new Object[datas.length];
        MergeSortAction action = new MergeSortAction(datas, low, high, bufs);
        pool.invoke(action);
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }
 
    static <T extends Comparable<? super T>> void sort(T[] datas, int low, int high, Object[] bufs) {
        if (low >= high)
            return;
 
        int center = (low + high) / 2;
        sort(datas, low, center);
        sort(datas, center + 1, high);
        merge(datas, low, center, high, bufs);
    }
 
    private static <T extends Comparable<? super T>> void merge(T[] datas, int leftPos, int center, int rightPos,
                                                                Object[] bufs) {
        int cur = leftPos;
        int leftCur = leftPos;
        int rightCur = center + 1;
        while (leftCur <= center && rightCur <= rightPos) {
            if (datas[leftCur].compareTo(datas[rightCur]) < 0)
                bufs[cur++] = datas[leftCur++];
            else
                bufs[cur++] = datas[rightCur++];
        }
        while (leftCur <= center)
            bufs[cur++] = datas[leftCur++];
        while (rightCur <= rightPos)
            bufs[cur++] = datas[rightCur++];
 
        for (int i = leftPos; i <= rightPos; ++i)
            datas[i] = (T) bufs[i];
    }
 
    static class MergeSortAction<T extends Comparable<? super T>> extends RecursiveAction {
 
        private T[] mDatas;
        private int mLow;
        private int mHigh;
        private Object[] mBuf;
 
        public MergeSortAction(T[] datas, int low, int high, Object[] buf) {
            mDatas = datas;
            mLow = low;
            mHigh = high;
            mBuf = buf;
        }
 
        @Override
        protected void compute() {
            if (mLow >= mHigh)
                return;
 
            int center = (mLow + mHigh) / 2;
            if (mLow + 2 >= mHigh) {
                if (mDatas[mLow].compareTo(mDatas[center]) > 0)
                    swap(mDatas, mLow, center);
                if (mDatas[center].compareTo(mDatas[mHigh]) > 0) {
                    swap(mDatas, center, mHigh);
                    if (mDatas[mLow].compareTo(mDatas[center]) > 0)
                        swap(mDatas, mLow, center);
                }
                return;
            }
            invokeAll(new MergeSortAction<>(mDatas, mLow, center, mBuf), new MergeSortAction<T>(mDatas, center + 1,
                    mHigh, mBuf));
            merge(mDatas, mLow, center, mHigh, mBuf);
        }
 
        private void swap(Object[] datas, int a, int b) {
            Object tmp = datas[a];
            datas[a] = datas[b];
            datas[b] = tmp;
        }
    }
}

