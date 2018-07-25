package cn.ken.questionansweringsystem.model.common;

/**
 * author: shangkun <br/>
 * date: 2018/7/24 <br/>
 * what: 服务器信息 <br/>
 */
public class ServerInfo {
    //用户名
    private String userName;
    //计算机名
    private String computerName;
    //计算机域名
    private String userDomain;
    //本地ip地址
    private String ip;
    //本地主机名
    private String hostName;
    //JVM可以使用的总内存
    private long totalMemory;
    //JVM可以使用的剩余内存
    private long freeMemory;
    //JVM可以使用的处理器个数
    private String availableProcessors;
    //Java的运行环境版本
    private String javaVersion;
    //Java的运行环境供应商
    private String javaVendor;
    //Java供应商的URL
    private String javaVendorUrl;
    //Java的安装路径
    private String javaHome;
    //操作系统的名称
    private String osName;
    //操作系统的构架
    private String osArch;
    //操作系统的版本
    private String osVersion;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public String getAvailableProcessors() {
        return availableProcessors;
    }

    public void setAvailableProcessors(String availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getJavaVendor() {
        return javaVendor;
    }

    public void setJavaVendor(String javaVendor) {
        this.javaVendor = javaVendor;
    }

    public String getJavaVendorUrl() {
        return javaVendorUrl;
    }

    public void setJavaVendorUrl(String javaVendorUrl) {
        this.javaVendorUrl = javaVendorUrl;
    }

    public String getJavaHome() {
        return javaHome;
    }

    public void setJavaHome(String javaHome) {
        this.javaHome = javaHome;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
