package MaxPQ;

/**
 * note 1: the meaning of <T extends Comparable<T>> :
 *     This means that the type parameter must support comparison with
 *     other instances of its own type, via the Comparable interface.
 *     1) Comparable <T> is a generic interface.
 *     2) extends means inheritance from a class or an interface.
 *
 * note 2: the meaning of (T[]) new Comparable[N] :
 *     1) new Comparable[N] creates an array with N elements which can hold
 *     Comparable or any subtype of Comparable. No Comparable is created, just an array.
 *     2) The important point is that the array is only allowed to hold Comparable objects;
 *     new Object[capacity] would be allowed to hold anything.
 */

/**
 * 实现大根堆（优先队列）
 */
public class MaxPQ <T extends Comparable<T>> {
    //存储元素的数组
    private T[] heapArr;
    //当前队列中元素的个数
    private int size;

    public MaxPQ(int cap) {
        //因为索引0不用，所以需要多分配一个空间
        heapArr = (T[]) new Comparable[cap+1];
        size = 0;
    }
    //返回当前队列中的最大元素
    public T getMax() {
        return heapArr[1];
    }
    //插入元素val
    public void insert(T val) {
        //将元素val插入堆底
        size++;
        heapArr[size] = val;
        //然后将节点size上浮到正确位置
        swim(size);
    }

    //删除并返回当前队列中的最大元素(即堆顶元素）
    public T removeMax() {
        T maxVal = heapArr[1];
        //先将堆顶元素与堆底最后一个元素互换
        swap(1,size);
        //再将堆底最后一个元素删除
        heapArr[size] = null;
        size--;
        //最后将此时的堆顶元素下沉到正确位置
        sink(1);
        //返回最大元素的值
        return maxVal;
    }

    /**
     * 以下methods都是为了实现最大堆
     * swim(i)：上浮heapArr中索引为i的元素。
     * sink(i):下沉heapArr中索引为i的元素。
     * swap(index1, index2): 辅助函数，即交换索引为index1和index2的两个元素的值
     * less(index1, index2):辅助函数，当heapArr[index1] < heapArr[index2]时，返回true，否则返回false
     * indexOfLeft(i):返回某节点（索引为i）的左孩子的索引。
     * indexOfRight(i):返回某节点（索引为i）的右孩子的索引。
     * indexOfParent(i):返回某节点（索引为i）的父节点的索引。
     */
    private void swim(int i) {
        //如果尚未到达堆顶（即i > 1)且大于父节点的值，则持续上浮
        while (i > 1 && less(indexOfParent(i), i)) {
            swap(indexOfParent(i), i);
            i = indexOfParent(i);
        }
    }

    private void sink(int i) {
        //判断是否达到堆底：当节点i的左节点索引 > size时，说明节点i位于堆底
        while (indexOfLeft(i) <= size) {
            //先找出左右节点谁的值较大
            int max = indexOfLeft(i);
            //如果右节点存在且右节点的值大于左节点，则将max更新为右节点的索引
            if (indexOfRight(i) <= size && less(max,indexOfRight(i))) {
                max = indexOfRight(i);
            }
            //如果节点i的值大于左右节点，则不再下沉
            if (less(max, i)) {
                break;
            }
            //否则节点i和节点max交换
            swap(i, max);
            i = max;
        }
    }

    private void swap(int index1, int index2) {
        T temp = heapArr[index1];
        heapArr[index1] = heapArr[index2];
        heapArr[index2] = temp;
    }

    private boolean less(int index1, int index2) {
        return heapArr[index1].compareTo(heapArr[index2]) < 0;

    }

    private int indexOfLeft(int i) {
        return i*2;
    }

    private int indexOfRight(int i) {
        return i*2 + 1;
    }

    private int indexOfParent(int i) {
        return i/2;
    }
}
