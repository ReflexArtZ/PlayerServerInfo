package de.emeraldmc.playerinfo;


import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class SystemInfos {

    private final OperatingSystemMXBean osMxBean = ManagementFactory.getOperatingSystemMXBean();

    public String getOsName() {
        return osMxBean.getName();
    }
    public String getOsVersion() {
        return osMxBean.getVersion();
    }
    public String getOsArch() {
        return osMxBean.getArch();
    }
    public int getCores() {
        return osMxBean.getAvailableProcessors();
    }
    public String getCpuName() {
        return System.getenv("PROCESSOR_IDENTIFIER");
    }
    public double getAverageLoad() {
        return osMxBean.getSystemLoadAverage();
    }
}
