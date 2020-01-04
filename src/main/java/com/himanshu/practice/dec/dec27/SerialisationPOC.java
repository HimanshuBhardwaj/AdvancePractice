package com.himanshu.practice.dec.dec27;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;

/**
 * @author Himanshu Bhardwaj
 * Date 27/Dec/2019
 */
public class SerialisationPOC {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//           serialise("/Users/himanshu.bhardwaj/projects/ola/AdvancePractice/src/main/resources/TaskOutput");
        desearilize("/Users/himanshu.bhardwaj/projects/ola/AdvancePractice/src/main/resources/TaskOutput");
    }

    static void serialise(String fileName) throws IOException {
        Task[] tasks = new Task[5];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task("Himanshu" + i, i);
        }
        FileOutputStream outputStream = new FileOutputStream(new File(fileName));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(tasks);
        objectOutputStream.close();

    }

    static void desearilize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(filename));
        ObjectInputStream stream = new ObjectInputStream(fileInputStream);
        for (Task emp : (Task[]) stream.readObject()) {
            System.out.println(emp);
        }
    }
}

@AllArgsConstructor
@ToString
class Task implements Serializable {
    String name;
    int vaue;
    private static final long serialVersionUID = 1L;
}
