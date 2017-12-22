public class Work {
    private  double num; // 序列号
    private  double arrtime; // 到达时间
    private  double runtime; // 运行时间
    private  double waittime = 0; // 等待时间
    private  double starttime = 0; // 开始时间
    private  double endtime = 0; // 结束时间
    private  double turnover = 0; // 周转
    private  double aveturnover = 0; // 平均周转

    //构造函数--设置
    public Work(double num, double arrtime, double runtime) {
        this.num = num;
        this.arrtime = arrtime;
        this.runtime = runtime;
    }

    public  void setWaittime(double waittime) {
        this.waittime = waittime;
    }
    public void setStarttime(double starttime) {
        this.starttime = starttime;
    }
    public void setEndtime(double endtime) {
        this.endtime = endtime;
    }
    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }
    public void setAveturnover(double aveturnover) {
        this.aveturnover = aveturnover;
    }
    // 获取运行时间
    public double getRuntime() {
        return this.runtime;
    }

    // 获取序列号
    public  double getNum() {
        return this.num;
    }

    // 获取到达时间
    public  double getArrtime() {
        return this.arrtime;
    }

    // 获取等待时间
    public  double getWaittime() {
        return this.waittime;
    }

    // 获取结束时间
    public  double getEndtime() {
        return this.endtime;
    }

    // 获取开始时间
    public double getStarttime() {
        return this.starttime;
    }
    // 获取周转时间
    public double getTurnover() {
        return this.turnover;
    }
    // 显示所有数据
    public void showAll() {
        System.out.println(num + " " + arrtime + " " + runtime + " " + waittime + " " + starttime + " " + endtime + " " + turnover + " " + aveturnover);
    }
}
