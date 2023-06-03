package com.example.demo;

import com.fazecast.jSerialComm.SerialPort;

public class Connector {
    private SerialPort serialPort;

    public Connector() {
    }

    public boolean connect(String com) {
        serialPort = SerialPort.getCommPort(com);
        return serialPort.openPort();
    }

    public SerialPort getSerialPort(){
        return serialPort;
    }
}
