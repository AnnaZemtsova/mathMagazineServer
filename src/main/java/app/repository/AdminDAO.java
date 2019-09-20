package app.repository;

import app.model.Admin;

public interface AdminDAO {
    public Admin singin(String login, String password);
}
