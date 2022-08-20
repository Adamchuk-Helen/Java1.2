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

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Books");
        List<Books> books = query.list();
        System.out.println("All books: Author Id    Author Name    Book Name");
        for (Books b : books) {
            System.out.println(b.getAuthor().getId() + " " + b.getAuthor().getFullName() + " " + b.getId() + " " + b.getName());
        }
        session.close();

        System.out.println("Add new book=> press 1 ");
        System.out.println("Delete book => press 2 ");
        System.out.println("Edit book => press 3 ");

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        switch (num) {
            case 1:
                add_book();
                break;
            case 2:
                delete_book();
                break;
            case 3:
                edit_book();
                break;
            default:
                System.out.println("Press correct number");
        }

    }

    public static void add_book() {
        Session session2 = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Books books1 = new Books();
        System.out.println("Enter book name");
        Scanner in = new Scanner(System.in);
        String new_book = in.nextLine();
        books1.setName(new_book);
        System.out.println("Enter author id");
        Scanner in_author_id = new Scanner(System.in);
        int author_id = in.nextInt();
        Author a = new Author();
        a.setId(author_id);
        books1.setAuthor(a);
        session2.save(books1);
        session2.close();
    }

    public static void delete_book() {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.print("Input book id you want to delete ");
            Scanner in = new Scanner(System.in);
            int id_book = in.nextInt();
            Books book = session.get(Books.class, id_book);
            session.delete(book);
            session.flush();
            session.close();
        }
    }

    public static void edit_book() {
        Transaction transaction_edit = null;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction_edit = session.beginTransaction();

            System.out.print("Input book id you want to edit ");
            Scanner in = new Scanner(System.in);
            int id_book = in.nextInt();
            Books book = session.get(Books.class, id_book);
            System.out.print("Input book name you want to edit ");
            Scanner in_name = new Scanner(System.in);
            String new_name_book = in_name.nextLine();
            book.setName(new_name_book);

            System.out.print("Input author id you want to edit ");
            Scanner in_author_id = new Scanner(System.in);
            int new_author_id = in_name.nextInt();
            Author a = new Author();
            a.setId(new_author_id);
            book.setAuthor(a);
            session.update(book);
            session.flush();
            session.close();
        }

    }

}