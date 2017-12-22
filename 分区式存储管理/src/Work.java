public class Work {
    private String name;
    private String spacename = "";
    private int size;

    public Work(String name, int size) {
        this.name = name;
        this.size = size;
    }

    // 单独设置 spacename
    public void setSpacename(String spacename) {
        this.spacename = spacename;
    }

    public int getSize() {
        return this.size;
    }
    public String getName() {
        return name;
    }
    public  String getSpacename() {
        return this.spacename;
    }
}
