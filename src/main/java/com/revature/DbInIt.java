package com.revature;

import com.revature.daos.PersonDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.RoleDAO;
import com.revature.daos.StatusDAO;
import com.revature.models.Person;
import com.revature.models.Role;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DbInIt {

    PersonDAO personDAO;
    ReimbursementDAO reimbursementDAO;
    RoleDAO roleDAO;
    StatusDAO statusDAO;

    public DbInIt(PersonDAO personDAO,
                  ReimbursementDAO reimbursementDAO,
                  RoleDAO roleDAO,
                  StatusDAO statusDAO) {
        this.personDAO = personDAO;
        this.reimbursementDAO = reimbursementDAO;
        this.roleDAO = roleDAO;
        this.statusDAO = statusDAO;
    }

    @PostConstruct
    public void InItDatabase() {
        //Delete the data first...
        roleDAO.deleteAll();
        personDAO.deleteAll();
        reimbursementDAO.deleteAll();


                //Insert some dummy data...

                //ROLES table
                Role role1 = new Role(1, "Employee");
                Role role2 = new Role(2, "Finance Manager");
                roleDAO.save(role1);
                roleDAO.save(role2);

                //people table
        Person person1 = new Person(1, "billy","james", "bjames85", "password", roleDAO.getByRoleByRoleTitle("Finance Manager"));
    }

}