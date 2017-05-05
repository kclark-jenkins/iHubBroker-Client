package org.krisbox.ihub.utils;

import java.util.Map;

public interface iHubClient {
    public void executeReport(  Map<String, Object> properties);
    public void deleteReport(   Map<String, Object> properties);
    public void uploadReport(   Map<String, Object> properties);
    public void addUser(        Map<String, Object> properties);
    public void addUsers(       Map<String, Object> properties);
    public void addGroup(       Map<String, Object> properties);
    public void addGroups(      Map<String, Object> properties);
    public void deleteGroup(    Map<String, Object> properties);
    public void deleteGroups(   Map<String, Object> properties);
    public void deleteUser(     Map<String, Object> properties);
    public void deleteUsers(    Map<String, Object> properties);
    public void addSchedule(    Map<String, Object> properties);
    public void deleteSchedule( Map<String, Object> properties);
    public void deleteSchedules(Map<String, Object> properties);

    public Map<String, Object> listReports(  Map<String, Object> properties);
    public Map<String, Object> listUsers(    Map<String, Object> properties);
    public Map<String, Object> listGroups(   Map<String, Object> properties);
    public Map<String, Object> listSchedules(Map<String, Object> properties);
}
