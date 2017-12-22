import java.util.ArrayList;
import java.util.List;

public class Client {

    public static List<Work> workList = new ArrayList<Work>(); // 存储作业
    public static List<Space> spaceList = new ArrayList<Space>(); // 存储分区

    public static void  main(String[] args) {

        InitList();
        System.out.println("-----------------------首次适应分区前-----------------------");
        showSpaceName();
        showSpaceSize();
        FirstAdapt();
        System.out.println("--------------------------分区后---------------------------");
        showSpaceSize();
        System.out.println("------------------------分区结果为--------------------------");
        showWorkInSpace();

        InitList();
        System.out.println("\n" + "\n" + "-----------------------最佳适应分区前-----------------------");
        showSpaceName();
        showSpaceSize();
        BestAdapt();
        System.out.println("---------------------------分区后---------------------------");
        showSpaceSize();
        System.out.println("-------------------------分区结果为--------------------------");
        showWorkInSpace();

        InitList();
        System.out.println("\n" + "\n" + "-----------------------最坏适应分区前-----------------------");
        showSpaceName();
        showSpaceSize();
        WorstAdapt();
        System.out.println("---------------------------分区后---------------------------");
        showSpaceSize();
        System.out.println("-------------------------分区结果为--------------------------");
        showWorkInSpace();
    }

    public static void InitList(){
        workList.clear(); // 首先将初始化为空
        spaceList.clear();

        getSpaceInit(1,16);
        getSpaceInit(2,16);
        getSpaceInit(3,8);
        getSpaceInit(4,32);
        getSpaceInit(5,64);
        getSpaceInit(6,32);
        getSpaceInit(7,8);
        getSpaceInit(8,16);
        getSpaceInit(9,64);

        getWorkInit("A", 7);
        getWorkInit("B", 18);
        getWorkInit("C", 9);
        getWorkInit("D", 20);
        getWorkInit("E", 35);
        getWorkInit("F", 8);
    }
    // 得到SpaceList
    public static void getSpaceInit(int num, int size){
        Space space = new Space("分区-"+num,0, size);
        spaceList.add(space);
    }

    // 得到WorkList
    public static void getWorkInit(String name, int size){
        Work work = new Work(name, size);
        workList.add(work);
    }

    // 显示分区名字
    public static void showSpaceName() {
        for(int i=0; i<spaceList.size(); i++) {
            System.out.print(spaceList.get(i).getName() + " ");
        }
        System.out.println();
    }


    // 显示分区大小
    public static void showSpaceSize() {
        for(int i=0; i<spaceList.size(); i++) {
            System.out.print(spaceList.get(i).getSize() + "     ");
        }
        System.out.println();
    }

    // 显示作业所在分区
    public static void  showWorkInSpace() {
        for(int i=0; i<workList.size(); i++) {
            System.out.println(workList.get(i).getName() + "  -->  " + workList.get(i).getSpacename());
        }
    }

    // 首次适应
    public static void FirstAdapt() {
        for(int i=0; i<workList.size(); i++) {
            for(int j=0; j<spaceList.size(); j++) {
                // System.out.println(workList.get(i).getSize() + "          " + spaceList.get(j).getSize());
                if(workList.get(i).getSize() <= spaceList.get(j).getSize()) { // 满足空间条件的时候
                    workList.get(i).setSpacename(spaceList.get(j).getName()); // 将分区名添加到work中
                    spaceList.get(j).setList(workList.get(i)); // 将作业添加到分区中
                    spaceList.get(j).setSize(spaceList.get(j).getSize() - workList.get(i).getSize());
                    break;
                }
            }
        }
    }

    // 最佳适应
    public static void BestAdapt() {
        for(int i=0; i<workList.size(); i++) {
            int temp = -1;
            int size = 9999; // 设置size比workList的大
            for(int j=0; j<spaceList.size(); j++) {
                if(spaceList.get(j).getSize() >= workList.get(i).getSize() && spaceList.get(j).getSize() < size) {
                    // System.out.println(temp);
                    temp = j;
                    size = spaceList.get(j).getSize();
                }
            }
            if(temp != -1) {
                workList.get(i).setSpacename(spaceList.get(temp).getName());
                spaceList.get(temp).setList(workList.get(i)); // 将作业添加到分区中
                spaceList.get(temp).setSize(spaceList.get(temp).getSize() - workList.get(i).getSize());
            }

        }
    }

    // 最坏适应
    public static void WorstAdapt() {
        for(int i=0; i<workList.size(); i++) {
            int temp = -1;
            int size = 0; // 设置size比workList的大
            for(int j=0; j<spaceList.size(); j++) {
                if(spaceList.get(j).getSize() >= workList.get(i).getSize() && spaceList.get(j).getSize() > size) {
                    // System.out.println(temp);
                    temp = j;
                    size = spaceList.get(j).getSize();
                }
            }
            if(temp != -1) {
                workList.get(i).setSpacename(spaceList.get(temp).getName());
                spaceList.get(temp).setList(workList.get(i)); // 将作业添加到分区中
                spaceList.get(temp).setSize(spaceList.get(temp).getSize() - workList.get(i).getSize());
            }

        }
    }
}
