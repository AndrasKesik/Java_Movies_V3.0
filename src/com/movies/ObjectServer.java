package com.movies;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

    static ServerMode mode;

    public static void main(String[] args) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server started");
        } catch (IOException e) {
            System.out.println("Can't listen to 4444");
            System.exit(1);
        }

        while (true) {

            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(1);
            }
            System.out.println("--Connection successful--");


            try {
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Stream initialization failed");
            }

            while (true) {
                Object inputObject = null;


                try {
                    inputObject = ois.readObject();
                } catch (Exception e) {
                    System.out.println("Can't read from ObjectInputStream");
                }

                if (inputObject instanceof Command) {
                    Command command = (Command) inputObject;

                    if (command.equals(Command.GET)) {
                        mode = ServerMode.LOAD;
                        System.out.println("Server Mode changed to: LOAD");
                        try {
                            oos.writeObject(load());
                        } catch (IOException e) {
                            System.out.println("get branch loading problem");
                        }
                    } else if (command.equals(Command.PUT)) {
                        mode = ServerMode.SAVE;
                        System.out.println("Server Mode changed to: SAVE");
                        try {
                            FileOutputStream fos = new FileOutputStream("C:\\Workspace\\Java\\Week11A_Movies_V3.0\\file.ser");
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
                            save(ois.readObject(), objectOutputStream);

                        } catch (IOException e) {
                            System.out.println("put branch io problem");
                        } catch (ClassNotFoundException e) {
                            System.out.println("put branch classnotfound problem");
                        }

                        System.out.println("success");
                    } else if (command.equals(Command.EXIT)) {
                        System.out.println("byebyebye");
                        break;
                    }

                }

            }


            try {
                clientSocket.close();
                System.out.println("--Disconnected--");
            } catch (IOException e) {
                System.out.println("Clientsocket close problem");
            }

        }
    }


    public static Object load() {
        Object o = null;
        try (FileInputStream fis = new FileInputStream("C:\\Workspace\\Java\\Week11A_Movies_V3.0\\file.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fis))
        {
            o = objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("load method error, cant read from file");
        }
        return o;
    }


    public static void save(Object o, ObjectOutputStream objectOutputStream) {
        try {
            objectOutputStream.writeObject(o);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}