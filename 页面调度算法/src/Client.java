import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Client {

    public static int[] work; // 定义页面序列
    public static int[][] page; // 定义二维数组页面
    public static List<Integer> lackPage = new ArrayList<Integer>();

    public static void main(String[] args) {
        int pagenum = 3;
        //work = new int[]{1,2,3,4,1,2,5,1,2,3,4,5};
        // 7，0，1，2，0，3，0，4，2，3，0，3，2，1，2，0，1，7，0，1
        work = new int[]{7,1,2,3,4,2,3,3,2,1,2,7,1};
        page = new int[2][3];


        System.out.println("-----------FIFO页面调度------------");
        lackPage =  FifoDispatch(pagenum, work);
        showWork();
        System.out.println("----------------------------------");
        showPage();
        System.out.println("----------------------------------");
        System.out.print("缺页页为： ");
        showLack();
        System.out.println("---------------结束----------------");

        System.out.println("\n" + "\n" + "-----------LRU页面调度------------");
        lackPage =  LruDispatch(pagenum, work);
        showWork();
        System.out.println("----------------------------------");
        showPage();
        System.out.println("----------------------------------");
        System.out.print("缺页页为： ");
        showLack();
        System.out.println("---------------结束----------------");

    }

    public static List<Integer> LruDispatch(int pagenum, int[] work1) {
        // 初始化数组为0
        work = new int[work1.length];
        page = new int[pagenum][work1.length];
        for(int i=0; i<work1.length; i++) {
            work[i] = work1[i];
        }

        for(int i=0; i<pagenum; i++) {
            for(int j=0; j<work1.length; j++) {
                page[i][j] = 0;
            }
        }
        // 初始化过后进行分页操作
        List<Integer> count = new ArrayList<Integer>(); // 缺页数
        Stack<Integer> pagestack = new Stack<Integer>(); // 定义一个栈

        page[0][0] = work[0];
        pagestack.push(work[0]);
        count.add(1);

        for(int i=1; i<work.length; i++) {
            pagestack.removeAllElements(); // 移除所有元素

            for (int k=pagenum-1; k>=0; k--) { // 初始化stack为上一页的内容
                if(page[k][i-1] > 0 ) {
                    pagestack.push(page[k][i-1]);
                    // System.out.println(pagestack.peek() + "--");
                }
            }
            //System.out.println(pagestack.size());
            int[] temp = new int[pagenum];
            if(pagestack.search(work[i]) > 0) { // 存在数据
                pagestack.removeElement(work[i]);
                pagestack.push(work[i]);
            } else{
                count.add(i+1);
                if(pagestack.size() < pagenum) { // 还有存页
                    pagestack.push(work[i]);
                } else { // 没有存页--移除最后一个元素
                    pagestack.removeElementAt(0); //移除最后一个元素
                    pagestack.push(work[i]);
                }
            }

            // 将stack中的内容存入页表
            //System.out.println(pagestack);
            for(int k=0; k<pagenum; k++) {
                if(pagestack.size() > 0) {
                    page[k][i] = pagestack.pop();
                }
            }

        }

        return count;
    }


    // 先进先出调度算法
    public static List<Integer> FifoDispatch(int pagenum, int[] work1) {
        // 初始化数组为0
        work = new int[work1.length];
        page = new int[pagenum][work1.length];
        for(int i=0; i<work1.length; i++) {
            work[i] = work1[i];
        }

        for(int i=0; i<pagenum; i++) {
            for(int j=0; j<work1.length; j++) {
                page[i][j] = 0;
            }
        }

        // 初始化过后进行分页操作
        List<Integer> count = new ArrayList<Integer>(); // 缺页数
        count.add(1);
        page[0][0] = work[0];
        int fifo = 0; // 指向最早进入的page页
        for(int i=1; i<work.length; i++) {
            // 将上一页的页面得复制到下一页
            for(int j=0; j<pagenum; j++) {
                page[j][i] = page[j][i-1];
            }

            boolean flag = false;  // 判断是否有内存已经在page中
            for(int j=0; j<pagenum; j++) {
                if(page[j][i-1] == work[i]) {
                    flag = true;
                }
            }

            if(flag == false) { // 没有存在的页表
                count.add(i+1);
                int pagex = -1;
                for(int j=0; j<pagenum; j++) { // 判断是否存在0位，表示还有页表可以进行存储
                    if(page[j][i-1] == 0) {
                        pagex = j;
                        break;
                    }
                }
                if(pagex != -1) {
                    page[pagex][i] = work[i];
                } else {
                    page[fifo][i] = work[i];
                    fifo = (fifo + 1) % pagenum;
                }
            }

        }
        return count;

    }

    // 显示数组
    public static void showWork(){
        for(int i=0; i<work.length; i++) {
            System.out.print(work[i] + "  ");
        }
        System.out.println();
    }
    // 显示二维数组
    public static void showPage() {
        for(int i=0; i< page.length; i++){
            for(int j=0; j<page[0].length; j++) {
                System.out.print(page[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // 显示缺页List
    public static void showLack() {
        for(int i=0; i<lackPage.size(); i++) {
            System.out.print(lackPage.get(i) + "  ");
        }
        System.out.println();
    }
}
