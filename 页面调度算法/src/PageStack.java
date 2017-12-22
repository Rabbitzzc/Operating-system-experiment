public class PageStack {
    private int[] page;
    private int length;

    public PageStack(int[] page, int length) {
        page = new int[length];
        page = page;
        length= length;
    }

    public int[] getPage() {
        return this.page;
    }

    public int getLength() {
        return  this.length;
    }

    public void addPage(int num) {

        this.length += 1; //长度加1

    }
}
