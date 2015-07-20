package com.test.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by zyinyan on 2015/7/15.
 */
public class KryoTest {

    public static void main(String[] args) throws FileNotFoundException {

        Kryo kryo = new Kryo();
        Output output = new Output(new FileOutputStream("file.bin"));

        kryo.writeObject(output, new String("the message"));
        output.close();

        Input input = new Input(new FileInputStream("file.bin"));
        String msg = kryo.readObject(input, String.class);
        input.close();
        System.out.println("--"+msg);

    }

}
