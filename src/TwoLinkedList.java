import java.util.ArrayList;

class TwoLinkedList<T> {
    public static void main(String[] args) {
        int i = 1;
        ArrayList<LinkedList> arr = new ArrayList<>();
        while (i != 6) {
            arr.add(new LinkedList<>(i));
            i++;
        }
        i = arr.size() - 1;
        while (i != 0) {
            arr.get(i).preData = arr.get(i - 1);
            arr.get(i - 1).nextData = arr.get(i);
            i--;
        }

        LinkedList<Object> tools = new LinkedList<>();
        LinkedList head = arr.get(0);
        tools.traverse(head);//原始数据
        tools.insert(head, new LinkedList(6));
        tools.traverse(head);
        tools.delete(head, new LinkedList(2));
        tools.traverse(head);
        System.out.println(tools.find(head, new LinkedList(8)));
        LinkedList flip = tools.flip(head);
        tools.traverse(flip);
    }
}

class LinkedList<T> {

    T data;
    LinkedList preData;
    LinkedList nextData;

    public LinkedList(T data) {
        this.data = data;
    }

    public LinkedList() {

    }

    public void insert(LinkedList head, LinkedList data) {
        LinkedList i = head;
        while (i.nextData != null) {
            i = i.nextData;
        }
        i.nextData = data;
        System.out.println("添加成功!");
    }

    public void delete(LinkedList head, LinkedList data) {
        if (!find(head, data)) {
            throw new RuntimeException("该数据不存在！");
        }
        LinkedList i = head;
        while (i.nextData != null) {
            if (i.data.equals(data.data)) {
                if (i.preData != null) {
                    i.preData.nextData = i.nextData;
                }
                if (i.nextData != null) {
                    i.nextData.preData = i.preData;
                }
                System.out.println("删除成功!");
                break;
            }
            i = i.nextData;
        }
    }

    public boolean find(LinkedList head, LinkedList data) {
        LinkedList i = head;
        int s = 0;
        while (i.nextData != null) {
            if (i.data.equals(data.data)) {
                return true;
            }
            i = i.nextData;
        }
        return false;
    }

    public void traverse(LinkedList head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.nextData;
        }
        System.out.println();
    }

    public LinkedList flip(LinkedList head) {
        LinkedList pre = null;
        LinkedList next = null;
        while (head != null) {
            next = head.nextData;
            head.nextData = pre;
            head.preData = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}