package cmd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;

public class Cmds {
    public File wd;

    protected void exit(String[] cmd) {
        System.out.print("Bye!\n");
        System.exit(0);
    }

    protected void hello(String[] cmd) {
        System.out.print("Hello world, hello ");
        for (int i = 1; i < cmd.length; i++) {
            System.out.print(cmd[i] + " ");
        }
        System.out.print("\n");
    }

    protected void pwd(String[] cmd) throws IOException {
        System.out.println(wd.getCanonicalPath());
        File[] ds = wd.listFiles(File::isDirectory);
        File[] fs = wd.listFiles(File::isFile);
        System.out.print("Almappák száma: " + ds.length + ", fájlok száma: " + fs.length + "\n");
    }

    protected void ls(String[] cmd) {
        try {
            if (cmd[1].equals("-l")) {
                File[] ds = wd.listFiles(File::isDirectory);
                File[] fs = wd.listFiles(File::isFile);
                for (int i = 0; i < ds.length; i++){
                    System.out.println("<DIR>\t" + ds[i].getName());
                }
                if (cmd.length > 2 && cmd[2].equals("-h")) {
                    for (int i = 0; i < fs.length; i++) {
                        System.out.println(fs[i].length() > 1024 ? fs[i].length()/1024 + "K" : fs[i].length() + "\t" + fs[i].getName());
                    }
                } else {
                    for (int i = 0; i < fs.length; i++) {
                        System.out.println(fs[i].length() + "\t" + fs[i].getName());
                    }
                }
                
            }
        } catch (java.lang.ArrayIndexOutOfBoundsException a) {
            for(File f : wd.listFiles()) {
                if (f.isDirectory()) {
                    System.out.print("\u001B[34m" + f.getName() + "\t");
                } else {
                    System.out.print("\u001B[37m" + f.getName() + "\t");
                }
            }
        }
        System.out.print("\u001B[37m");
    }

    protected void cd(String[] cmd) throws IOException {
        try {
            if (cmd[1].equals(".")) {
                System.out.println(wd.getCanonicalPath());
            } else if (cmd[1].equals("..")) {
                wd = wd.getParentFile();
                System.out.println(wd.getCanonicalPath());
            } else {
                File n = new File(wd, cmd[1]);
                if (n.exists()) {
                    wd = n;
                    System.out.println(wd.getCanonicalPath());
                } else {
                    System.err.println("Nem létezik a megadott mappa!" + cmd[1]);
                }
            }   
        } catch (java.lang.ArrayIndexOutOfBoundsException a) {
            System.out.println(wd.getCanonicalPath());
        }
    }

    protected void rm(String[] cmd) {
        if (cmd.length > 1) {
            if (cmd[1].equals("-f")) {
                for (int i = 2; i < cmd.length; i++) {
                    try {
                        File del = new File(wd, cmd[i]);
                        if (del.exists()) {
                            del.delete();
                        } else {
                            System.out.println("Nem létezik a fájl: " + cmd[i]);
                        } 
                    } catch (SecurityException  s) {
                        System.err.println("Engedély megtagadva: " + cmd[i]);
                    }
                }
            } else {
                if (!new File(wd, cmd[1]).exists()) {
                    System.err.println("Nem létezik a fájl!");
                    return;
                }
                System.out.print("Biztosan törölni akarod? I/N  ");
                @SuppressWarnings("resource") //idk mi ez csak a vs sípolt, hogy nincs kezelve a hibás bemenet, pedig van...
                Scanner in = new Scanner(System.in);
                String v = in.nextLine();
                if (v.equals("Y") || v.equals("y") || v.equals("I") || v.equals("i")) {
                    for (int i = 1; i < cmd.length; i++) {
                        try {
                            File del = new File(wd, cmd[i]);
                            if (del.exists()) {
                                del.delete();
                            } else {
                                System.out.println("Nem létezik a fájl: " + cmd[i]);
                            }
                        } catch (SecurityException  s) {
                            System.err.println("Engedély megtagadva: " + cmd[i]);
                        }
                    }
                } else if (v.equals("N") || v.equals("n")) {
                    return;
                } else {
                    System.err.println("Hibás bemenet!");
                }
            }
        }
    }

    protected void mkdir(String[] cmd) {
        if (cmd.length > 1) {
            for (int i = 1; i < cmd.length; i++) {
                try {
                    File ndir = new File(wd, cmd[i]);
                    if (!ndir.exists()) {
                        ndir.mkdir();
                    } else {
                        System.out.println("Már létezik a mappa: " + ndir.getName());
                    }
                } catch (SecurityException  s) {
                    System.err.println("Engedély megtagadva!");
                }
            }
        }
    }

    protected void mv(String[] cmd) {
        if (cmd.length > 1) {
            if (cmd.length > 2) {
                File f1 = new File(wd, cmd[1]);
                File f2 = new File(wd, cmd[2]);
                try {
                    f1.renameTo(f2);
                } catch (SecurityException s) {
                    System.err.println("Engedély megtagadva!");
                }
            } else {
                System.err.println("Hiányzik a cél!");
            }
        }
    }

    protected void cp(String[] cmd) {
        if(!new File(wd, cmd[1]).exists()) {
            System.out.println("Nem létezik a másolandó fálj: " + cmd[1]);
            return;
        }
        if (cmd.length > 1) {
            try {
                for (int i = 2; i < cmd.length; i++) {
                    try {
                        InputStream is = new FileInputStream(new File(wd, cmd[1]));
                        OutputStream os = new FileOutputStream(new File(wd, cmd[i]));
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                        is.close();
                        os.close();
                    } catch (IOException e) {
                        System.err.println("Hiba a fájl másolása folyamán: " + cmd[i]);
                    }
                }
            } catch (Exception e) {
                System.err.println("Hiba.");
            }
        }
    }

    protected void cat(String[] cmd) {
        if (cmd.length > 1) {
            for (int i = 1; i < cmd.length; i++) {
                File read = new File(wd, cmd[i]);
                try (Scanner s = new Scanner(read)) {
                    while (s.hasNextLine()) {
                        System.out.println(s.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    System.err.println("A fájl nem található: " + cmd[i]);
                }
            }
        }
    }

    protected void length(String[] cmd) {
        try {
            if (cmd.length > 1) {
                if (cmd[1].equals("-h")) {
                    if (cmd.length > 2) {
                        for (int i = 2; i < cmd.length; i++) {
                            File fl = new File(wd, cmd[i]);
                            System.out.println(cmd[i] + " hossza: " + (fl.length() > 1024 ? fl.length()/1024 + "K" : fl.length()));
                        }   
                    } else {return;}
                    return;
                }
                for (int i = 1; i < cmd.length; i++) {
                    File fl = new File(wd, cmd[i]);
                    System.out.println(cmd[i] + " hossza: " + fl.length());
                }   
            }
        } catch (SecurityException e) {
            System.err.println("Engedély megtagadva!");
        }
    }

    protected void head(String[] cmd) {
        int n = 10;
        if (cmd.length > 1) {
            if (cmd[1].equals("-n")) {
                if (cmd.length > 2) {
                    n = Integer.parseInt(cmd[2]);
                    if (cmd.length > 3) {
                        for (int i = 3; i < cmd.length; i++) {
                            File read = new File(wd, cmd[i]);
                            try (Scanner s = new Scanner(read)) {
                                while (s.hasNextLine() && --n > 0) {
                                    System.out.println(s.nextLine());
                                }
                            } catch (FileNotFoundException e) {
                                System.err.println("A fájl nem található: " + cmd[i]);
                            }
                        }
                    } else {return;}
                } else {return;}
            } else {
                for (int i = 1; i < cmd.length; i++) {
                    File read = new File(wd, cmd[i]);
                    try (Scanner s = new Scanner(read)) {
                        while (s.hasNextLine() && --n > 0) {
                            System.out.println(s.nextLine());
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("A fájl nem található: " + cmd[i]);
                    }
                }
            }
        }
    }

    protected void tail(String[] cmd) {
        int n = 10;
        if (cmd.length > 1) {
            if (cmd[1].equals("-n")) {
                if (cmd.length > 2) {
                    n = Integer.parseInt(cmd[2]);
                    if (cmd.length > 3) {
                        for (int i = 3; i < cmd.length; i++) {
                            File read = new File(wd, cmd[i]);
                            LinkedList<String> fl = new LinkedList<>();
                            try (Scanner s = new Scanner(read)) {
                                while (s.hasNextLine()) {
                                    fl.add(s.nextLine());
                                }
                                for (int j = ((fl.size() - n > 0) ? fl.size()-n : 0); j < fl.size(); j++) {
                                    System.out.println(fl.get(j));
                                }
                            } catch (FileNotFoundException e) {
                                System.err.println("A fájl nem található: " + cmd[i]);
                            }
                        }
                    } else {return;}
                } else {return;}
            } else {
                for (int i = 1; i < cmd.length; i++) {
                    File read = new File(wd, cmd[i]);
                    LinkedList<String> fl = new LinkedList<>();
                    try (Scanner s = new Scanner(read)) {
                        while (s.hasNextLine()) {
                            fl.add(s.nextLine());
                        }
                        for (int j = ((fl.size() - n > 0) ? fl.size()-n : 0); j < fl.size(); j++) {
                            System.out.println(fl.get(j));
                        }
                    } catch (FileNotFoundException e) {
                        System.err.println("A fájl nem található: " + cmd[i]);
                    }
                }
            }
        }
    }

    protected void wc(String[] cmd) {
        if (cmd.length > 1) {
            for (int i = 1; i < cmd.length; i++) {
                File read = new File(wd, cmd[i]);
                try (Scanner s = new Scanner(read)) {
                    int lines = 0;
                    int words = 0;
                    int chars = 0;
                    String tmp = null;
                    while (s.hasNextLine()) {
                        lines++;
                        tmp = s.nextLine();
                        chars += tmp.length();
                        words += Arrays.asList(tmp.split(" ")).size();
                    }
                    System.out.print("\"" + cmd[i] + "\" statisztikái:\n------------\n");
                    System.out.println("Sorok száma:\t\t" + lines);
                    System.out.println("Szavak száma:\t\t" + words);
                    System.out.print("Karakterek száma:\t" + chars + "\n------------\n");
                } catch (FileNotFoundException e) {
                    System.err.println("A fájl nem található: " + cmd[i]);
                }
            }
        }
    }

    protected void grep(String[] cmd) {
        try {
            String ptr = ".*" + cmd[1] + ".*";
            File gf = new File(wd, cmd[2]);
            Scanner s = new Scanner(gf);
            while (s.hasNextLine()) {
                String ln = s.nextLine();
                if (ln.matches(ptr)) {
                    System.out.println(ln);
                }
            }
            s.close();
        } catch (FileNotFoundException f) {
            System.err.println("A fájl nem található.");
        } catch (java.lang.ArrayIndexOutOfBoundsException a) {
            return;
        }
    }

    protected void cls(String[] cmd) {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

}