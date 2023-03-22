package com.indra.chat.model;

import java.util.HashSet;
import java.util.Set;

public class ChatRoom {
    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private Set<String> users = new HashSet<>();

    // Constructors, getters and setters
    public ChatRoom() {

    }

    public ChatRoom(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public void removeUser(String user) {
        this.users.remove(user);
    }
}
