package cmd;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("\n> ");
            String input = null;

            Cmds comrun = new Cmds();

            comrun.wd = new File(System.getProperty("user.dir"));

            while ((input = in.nextLine()) != null) {
                String[] cmd = input.split(" ");

                if (cmd[0].equals("exit")) {
                    comrun.exit(cmd);
                } else if (cmd[0].equals("hello")) {
                    comrun.hello(cmd);
                } else if (cmd[0].equals("pwd")) {
                    comrun.pwd(cmd);
                } else if (cmd[0].equals("ls")) {
                    comrun.ls(cmd);
                } else if (cmd[0].equals("cd")) {
                    comrun.cd(cmd);
                } else if (cmd[0].equals("rm")) {
                    comrun.rm(cmd);
                } else if (cmd[0].equals("mkdir")) {
                    comrun.mkdir(cmd);
                } else if (cmd[0].equals("mv")) {
                    comrun.mv(cmd);
                } else if (cmd[0].equals("cp")) {
                    comrun.cp(cmd);
                } else if (cmd[0].equals("cat")) {
                    comrun.cat(cmd);
                } else if (cmd[0].equals("wc")) {
                    comrun.wc(cmd);
                } else if (cmd[0].equals("clear")) {
                    comrun.cls(cmd);
                } else if (cmd[0].equals("length")) {
                    comrun.length(cmd);
                } else if (cmd[0].equals("head")) {
                    comrun.head(cmd);
                } else if (cmd[0].equals("tail")) {
                    comrun.tail(cmd);
                } else if (cmd[0].equals("grep")) {
                    comrun.grep(cmd);
                } else {
                    System.err.print("A parancs nem létezik! ");
                    for(String c : cmd) {
                        System.out.print(c + " ");
                    }
                    System.out.print("\n");
                }

                System.out.print("\n> ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
