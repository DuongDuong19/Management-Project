package com.myteam.work.management.handler;

import java.sql.Connection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TeacherHandler {

    private Connection connection;

    public TeacherHandler() {
        this.connection = SQLHandler.getConnection();
    }


}