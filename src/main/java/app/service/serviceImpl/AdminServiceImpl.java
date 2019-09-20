package app.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.model.Admin;
import app.repository.AdminDAO;
import app.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {


    private AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO)
    {
        this.adminDAO = adminDAO;
    }

    @Override
    @Transactional
    public Admin singin(String login, String password) {
        return adminDAO.singin(login, password);
    }
}
