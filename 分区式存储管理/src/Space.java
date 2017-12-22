import java.util.ArrayList;
import java.util.List;

public class Space {
    private String name;
    private int firstAddress = 0;
    private  int size;
    private List<Work> list = new ArrayList<Work>();

    public Space(String name, int firstAddress, int size) {
        this.name = name;
        this.firstAddress = firstAddress;
        this.size = size;
    }

    // 添加作业到某个分区
    public void setList(Work work) {
        list.add(work);
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return name;
    }
}
