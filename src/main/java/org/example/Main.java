package org.example;

import entities.Author;
import entities.Books;
import entities.Role;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

public class Main {
    public static void main(String[] args) throws Exception {
//створення і відкриття нової сесії
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Role");
        //виділення пам'яті для створення об'єкту
        Role role=new Role();
        //присвоєння значення
        role.setName("Admin");
        // збереження значення
        session.save(role);
        //вивід даних бази даних
        System.out.println("Role id: "+ role.getId());

//Створення об'єкту  Query
        Query query1 = session.createQuery("FROM Role");
        //вивід даних бази даних
        List<Role> roles = query.list();
        for (Role r : roles) {
            System.out.println(r.getName());
        }
        session.close();



        Session session1 = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query3 = session1.createQuery("FROM Books");
        //виділення пам'яті для створення об'єкту
        Books book=new Books();
        //присвоєння значення для поля імені
        book.setName("Бедрик");

        Author a = new Author();
        //присвоєння значення для поля, що забезпечує зв'язок між таблицями бази даних
        a.setId(1);
        book.setAuthor(a);
        //збереження значень
        session1.save(book);

        Query query2 = session1.createQuery("FROM Books");
        List<Books> books = query.list();
        //вивід значень таблиць з баз даних
        for (Books b : books) {
            System.out.println(b.getAuthor().getFullName()+ " " + b.getName());
        }
        //закриття сесії
        session1.close();

    }

}