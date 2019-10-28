package com.productivity.bytehalf.bindr.util.user;

public class DetermineOS {
    public OS getOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return OS.WINDOWS;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return OS.LINUX;
        } else if (os.contains("mac")) {
            return OS.MAC;
        } else if (os.contains("sunos")) {
            return OS.SOLARIS;
        }

        return null;
    }

    public enum OS {
        WINDOWS,
        LINUX,
        MAC,
        SOLARIS
    }
}
