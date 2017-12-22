import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF {
    public static List<Work> list = new ArrayList<Work>();
    public static List<Work> runList = new ArrayList<Work>();
    public static double parr[][] = {
            {1,800,50},
            {2,815,30},
            {3,830,25},
            {4,835,20},
            {5,845,15},
            {6,900,10},
            {7,920,5}
    };
    public static void main(String[] args) {

        First_SJS(parr);
        sortListWork(list);

        for(int i=0; i<list.size(); i++) {
            addRunList(list);
        }

        for(Work work : runList) {
            work.showAll();
        }

    }
    public static void First_SJS(double[][] a) {
        Work work = new Work(a[0][0], a[0][1], a[0][2]);
        work.setWaittime(0);
        work.setStarttime(work.getWaittime() + work.getArrtime());
        work.setEndtime(work.getStarttime() + work.getRuntime());
        work.setTurnover(work.getEndtime() - work.getArrtime());
        work.setAveturnover(work.getTurnover() / work.getRuntime());
        runList.add(work);
        int sizeArr = a.length;
        for(int i=1; i<sizeArr; i++) {
            work = new Work(a[i][0], a[i][1], a[i][2]);
            list.add(work);
        }
    }

    // 数组排序---根据运行时间进行排序
    public static  void sortListWork(List list) {
        Collections.sort(list, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Work work1 = (Work)o1;
                Work work2 = (Work)o2;
                if(work1.getRuntime() > work2.getRuntime()) {
                    return 1;
                } else if(work1.getRuntime() == work2.getRuntime()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    // 添加进程--根据达到时间与结束时间的比较进行添加
    public static void addRunList(List list) {
        int size = list.size();
        for(int i=0; i<size; i++) { // 遍历List
            Work work = (Work) list.get(i);
            double arrivetime = work.getArrtime();
            Work work1 = runList.get(runList.size()-1);
            double endtime = work1.getEndtime();
            if(runList.contains(work) == false && arrivetime <= endtime) {

                work.setWaittime(work1.getEndtime()-work.getArrtime());
                work.setStarttime(work.getArrtime() + work.getWaittime());
                work.setEndtime(work.getStarttime() + work.getRuntime());
                work.setTurnover(work.getEndtime() - work.getArrtime());
                // System.out.println(work.getTurnover() / work.getRuntime());
                work.setAveturnover(work.getTurnover() / work.getRuntime());
                runList.add(work);

            }
        }
    }
    // 将每一行的数组添加到程序中
}
