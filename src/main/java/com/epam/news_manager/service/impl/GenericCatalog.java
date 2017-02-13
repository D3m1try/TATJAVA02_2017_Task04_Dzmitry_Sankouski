package com.epam.news_manager.service.impl;

import com.epam.news_manager.bean.Bean;
import com.epam.news_manager.service.Catalog;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dzmitry_Sankouski on 04-Feb-17.
 */
public abstract class GenericCatalog<T extends Bean> implements Catalog<T> {
    //not finished yet
    private Class<T> type;
    private boolean isBooksUp = false;
    private Set<Bean> books = new HashSet<>();
    private Set<Bean> savedBooks;

//    private static BooksCatalog instance = new BooksCatalog();
//
    public GenericCatalog(Class<T> type){
        this.type = type;
    }

//    private void initBeans(){
//        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0){
//            savedBooks = new HashSet<>();
//        } else {
//            for (String id:
//                    BeanFactory.getInstance().getKeys().getBookIDs()) {
//                try {
//                    savedBooks.add(DAOFactory.getInstance().getBookDAO().read(id));
//                } catch (DAOException e) {
//                    e.printStackTrace();//TODO throw exception
//                }
//            }
//        }
//    }
//
//    @Override
//    public void add(String request) {
//        Bean newBean = BeanFactory.getInstance().;
//        fillBook(newBook,request);
//
//        books.add(newBook);
//        BeanFactory.getInstance().getKeys().getBookIDs().add(
//                DAOFactory.getInstance().getBookDAO().create(newBook));
//        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
//    }
//
//    public void edit(String request) {
//
//    }
//
//    public void fillBook(Book book, String request){
//        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
//        Matcher matcher = pattern.matcher(request);
//
//        while (matcher.find()){
//            if (matcher.group(1).toUpperCase().contains("ISBN")){
//                book.setISBN(matcher.group(2));
//            } if (matcher.group(1).toUpperCase().contains("T")){
//                book.setTitle(matcher.group(2));
//            }
//            if (matcher.group(1).toUpperCase().contains("D")){
//                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                try {
//                    book.setDate(format.parse(matcher.group(2)));
//                } catch (ParseException e) {
//                    e.printStackTrace();//TODO throw exception
//                }
//            }
//
//            if (matcher.group(1).toUpperCase().contains("M")){
//                book.setMessage(matcher.group(2));
//            }
//            if (matcher.group(1).toUpperCase().contains("P")){
//                try {
//                    book.setPageCount(Integer.valueOf(matcher.group(2)));
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();// TODO throw exception
//                }
//            }
//        }
//    }
//
//    @Override
//    public void delete() {
//
//    }
//
//    @Override
//    public List<Book> find(String request) {
//        Pattern pattern = Pattern.compile("(\\-p)?\\s?(\\w+)\\s+(.+)");
//        Matcher matcher = pattern.matcher(request);
//
//        if (!matcher.find()){
//            // TODO illegal arguments exception
//        }
//        if (matcher.group(1) != null) {
//            return DAOFactory.getInstance().getBookDAO().find(matcher.group(2), matcher.group(3), true);
//        } else {
//            return DAOFactory.getInstance().getBookDAO().find(matcher.group(2), matcher.group(3), false);
//        }
//    }
//
//    @Override
//    public void save() {
//        for (Book book:
//                books) {
//            DAOFactory.getInstance().getBookDAO().create(book);
//            savedBooks.add(book);
//        }
//
//        DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
//        DAOFactory.getInstance().getBooksDAO().update(BeanFactory.getInstance().getBooks());
//
//    }
//
//    public static BooksCatalog getInstance() {
//        return instance;
//    }
}
