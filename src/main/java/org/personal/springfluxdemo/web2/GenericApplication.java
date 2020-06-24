package org.personal.springfluxdemo.web2;

import java.io.IOException;

public class GenericApplication
{
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }
}
