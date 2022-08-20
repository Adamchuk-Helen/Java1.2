package org.example;

import entities.Role;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        //Role role = new Role();

        Query query = session.createQuery("From Role");

        List<Role> roles = query.list();
        System.out.println("All roles: ");
        for (Role r : roles) {
            System.out.println(r.getId() +" "+ r.getName());
        }
        System.out.println("Add new role => press 1 ");
        System.out.println("Delete role => press 2 ");
        System.out.println("Edit role => press 3 ");

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        switch (num) {
            case 1:
                add_role();
                break;
            case 2:
                delete_role();
                break;
            case 3:
                edit_role();
                break;
            default:
                System.out.println("Press correct number");
        }

        session.close();

    }
    public static void add_role(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Role role = new Role();
        System.out.print("Input new role ");
        Scanner in = new Scanner(System.in);
        String new_role = in.nextLine();
        role.setName(new_role);
        session.save(role);
        session.close();

    }

    public static void delete_role(){
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.print("Input role id you want to delete ");
            Scanner in = new Scanner(System.in);
            int id_role = in.nextInt();
            Role role = session.get(Role.class, id_role);
            session.delete(role);
            session.flush();
            session.close();
        }
    }
    public static void edit_role(){
        Transaction transaction_edit = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction_edit = session.beginTransaction();

            System.out.print("Input role id you want to edit ");
            Scanner in = new Scanner(System.in);
            int id_role = in.nextInt();
            Role role = session.get(Role.class, id_role);
            System.out.print("Input role id you want to edit ");
            Scanner in_name = new Scanner(System.in);
            String new_name_role = in_name.nextLine();
            role.setName(new_name_role);
            session.update(role);
            System.out.print(role);
            session.flush();
            session.close();
        }
    }
}