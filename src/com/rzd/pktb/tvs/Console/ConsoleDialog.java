package com.rzd.pktb.tvs.Console;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by vtimoshenko on 07.06.2016.
 */
public class ConsoleDialog {
    private Scanner in;
    /**
     * перечень доступных команд
     */
    HashMap<String, ConsoleCommand> coms;

    public ConsoleDialog () {
        in = new Scanner(System.in);
        coms = new HashMap<String, ConsoleCommand>();
    }

    /**
     * Добавить новую команду в консоль
     * @param cmd   класс исполнтель консольной команды
     */
    public void addCmd(ConsoleCommand cmd) {
        coms.put(cmd.getClass().getSimpleName(), cmd);
    }

    /**
     * Начать обработку команд
     */
    public void run() {
        while (true){
            pr("\n> ");
            String str = in.nextLine();
            String[] args = str.split(" ");
            if      (args[0].equals(""))        continue;
            else if (args[0].equals("exit"))    break;
            else if (coms.containsKey(args[0])) pr(coms.get(args[0]).Run(args));
            else                                pr("Unknown command!");
        }
        pr("\nBye");
    }

    /**
     * Вывод на экран
     * @param str что выводим
     */
    public void pr(String str) {
        System.out.print(str);
    }
}
