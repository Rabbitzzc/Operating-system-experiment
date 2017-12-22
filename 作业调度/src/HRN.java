import java.util.ArrayList;
import java.util.List;

public class HRN {
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
    public static void main (String[] args) {
        First_HRN(parr);

        for(int i=0; i<list.size(); i++) {
            Second_HRN(list);
        }
        System.out.println("序列 到达 运行 等待 开始 结束 周转 平均周转");
        for(Work work : runList) {
            work.showAll();
        }
    }
    public static void First_HRN(double[][] a) {
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
    public static void Second_HRN(List list) {
        int size = list.size(); // 得到长度
        double flag = 0;
        Work flagwork = new Work(0,0,0);
        for(int i=0; i<size; i++) {
            Work work = (Work) list.get(i);
            if(runList.contains(work) == false) {
                Work work1 = runList.get(runList.size()-1);
                double endtime = work1.getEndtime();
                double hrn = (endtime - work.getArrtime()) / work.getRuntime();
                if(hrn > flag) {
                    flag = hrn;
                    flagwork =work;
                    flagwork.setWaittime(endtime - work.getArrtime());
                    flagwork.setStarttime(work.getArrtime() + flagwork.getWaittime());
                    flagwork.setEndtime(work.getStarttime() + work.getRuntime());
                    flagwork.setTurnover(work.getEndtime() -work.getArrtime());
                    flagwork.setAveturnover(work.getTurnover() / work.getRuntime());
                }
            }
        }
        runList.add(flagwork);

    }
}
