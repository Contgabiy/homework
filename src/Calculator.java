import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        Tools tools = new Tools();
        List<String> strings = tools.toList("(1.1+2*(3-5))/1");
        System.out.println(strings);
        List<String> transform = tools.transform(strings);
        System.out.println(transform);
        double cal = tools.Cal(transform);
        System.out.printf("%.2f", cal);
    }
}

class Tools {
    //后缀表达式计算
    public double Cal(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("(\\d+\\.\\d+)") || s.matches("\\d+")) {
                stack.push(s);
            } else {
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                double res = 0;
                switch (s) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num2 - num1;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        if (num1 == 0) {
                            throw new RuntimeException("分母不能为0！");
                        }
                        res = num2 / num1;
                        break;
                    default:
                        throw new RuntimeException("表达式符号错误！");
                }
                stack.push(res + "");
            }
        }
        if (Double.parseDouble(stack.peek()) > Integer.MAX_VALUE) {
            throw new RuntimeException("表达式计算量过大！");
        } else {
            return Double.parseDouble(stack.pop());
        }
    }

    //中缀表达式转成List
    public List<String> toList(String str) {
        List<String> list = new ArrayList<>();
        int i = 0;
        String s = null;
        char c;
        do {
            if ((c = str.charAt(i)) < 48 || (c = str.charAt(i)) > 57) {
                list.add(c + "");
                i++;
            } else {
                s = "";
                if (i + 1 < str.length() && str.charAt(i + 1) == 46) {
                    s += c;
                    s += '.';
                    i += 2;
                }
                while (i < str.length() && (c = str.charAt(i)) >= 48 && (c = str.charAt(i)) <= 57) {
                    s += c;
                    i++;
                }
                list.add(s);
            }
        } while (i < str.length());
        return list;
    }

    //中缀转后缀
    public List<String> transform(List<String> list) {
        Stack<String> s1 = new Stack<>();
        ArrayList<String> s2 = new ArrayList<>();

        for (String item : list) {
            if (item.matches("(\\d+\\.\\d+)") || item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (!s1.empty() && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (!s1.empty()) {
            s2.add(s1.pop());
        }
        return s2;
    }
}

class Operation {
    public static int getValue(String s) {
        int result = 0;
        if (s.equals("+") || s.equals("-")) {
            result = 1;
        } else if (s.equals("*") || s.equals("/")) {
            result = 2;
        }
        return result;
    }
}