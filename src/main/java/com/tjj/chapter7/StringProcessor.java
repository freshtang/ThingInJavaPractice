package com.tjj.chapter7;

/**
 * @description: 9.3 (11)  创建一个类，里有方法可以将字符串互换位置，适配process
 * @author: tangjunjian
 * @create: 2018-07-20 14:01
 **/

import java.util.*;
import static net.mindview.util.Print.*;

class Apply {
    public static void process(Processor p, Object s) {
        print("Using Processor " + p.name());
        print(p.process(s));
    }
}

interface Processor {
    String name();
    Object process(Object input);
}

public abstract class StringProcessor implements Processor{
    public String name() {
        return getClass().getSimpleName();
    }
    public abstract String process(Object input);
    public static String s =
            "If she weighs the same as a duck, she's made of wood";
    public static void main(String[] args) {
        Apply.process(new Upcase(), s);
        Apply.process(new Downcase(), s);
        Apply.process(new Splitter(), s);
        Apply.process(new Reverse(), s);
    }
}

class Reverse extends StringProcessor {
    public String process(Object input) {
        StringBuffer sb =  new StringBuffer((String)input);
        return sb.reverse().toString();
    }
}

class Upcase extends StringProcessor {
    public String process(Object input) { // Covariant return
        return ((String)input).toUpperCase();
    }
}

class Downcase extends StringProcessor {
    public String process(Object input) {
        return ((String)input).toLowerCase();
    }
}

class Splitter extends StringProcessor {
    public String process(Object input) {
        return Arrays.toString(((String)input).split(" "));
    }
}
