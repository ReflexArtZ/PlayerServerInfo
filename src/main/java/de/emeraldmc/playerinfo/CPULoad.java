package de.emeraldmc.playerinfo;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class CPULoad {

    private final OperatingSystemMXBean osMXBean = ManagementFactory.getOperatingSystemMXBean();

    public double getProcessCPULoad() {
        com.sun.management.OperatingSystemMXBean nativeOsMXBean = (com.sun.management.OperatingSystemMXBean) osMXBean;
        double load = nativeOsMXBean.getProcessCpuLoad();
        return shorten(load)*100;   // *100 -> because of the %
    }

    public double getCPULoad() {
        com.sun.management.OperatingSystemMXBean nativeOsMXBean = (com.sun.management.OperatingSystemMXBean) osMXBean;
        double load =  nativeOsMXBean.getSystemCpuLoad();
        return shorten(load)*100;   // *100 -> because of the %
    }

    /**
     * shorts a double to the third comma position
     * @param x the double to short
     * @return shorted double
     */
    private double shorten(double x) {
        x *= 1000;
        x = (int)x;
        x /= 1000;
        return x;
    }
}
