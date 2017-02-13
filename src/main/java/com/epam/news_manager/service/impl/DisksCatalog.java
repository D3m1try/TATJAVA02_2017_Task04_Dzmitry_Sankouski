package com.epam.news_manager.service.impl;

import com.epam.news_manager.bean.BeanFactory;
import com.epam.news_manager.bean.Disk;
import com.epam.news_manager.dao.exception.DAOException;
import com.epam.news_manager.dao.impl.DAOFactory;
import com.epam.news_manager.service.exception.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 01-Feb-17.
 */
public class DisksCatalog implements com.epam.news_manager.service.Catalog<Disk> {
    private static DisksCatalog instance = new DisksCatalog();
    private boolean isBooksUp = false;
    private Set<Disk> disks = new HashSet<>();
    private Set<Disk> savedDisks;

    private DisksCatalog() {

    }

    private void initDisks() throws ServiceException {
        if (BeanFactory.getInstance().getKeys().getBookIDs().size() == 0) {
            savedDisks = new HashSet<>();
        } else {
            for (String id :
                    BeanFactory.getInstance().getKeys().getBookIDs()) {
                try {
                    savedDisks.add(DAOFactory.getInstance().getDiskDAO().read(id));
                } catch (DAOException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        }
    }

    @Override
    public void add(String request) throws ServiceException {
        Disk newDisk = new Disk();
        fillDisk(newDisk, request);

        try {
            BeanFactory.getInstance().getKeys().getDiskIDs().add(
                    DAOFactory.getInstance().getDiskDAO().create(newDisk));
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
        disks.add(newDisk);
    }

    public void edit(String request) {

    }

    public void fillDisk(Disk disk, String request) throws ServiceException {
        Pattern pattern = Pattern.compile("(\\s\\-[^\\s]+\\s+)([^\\s]+)");
        Matcher matcher = pattern.matcher(request);

        while (matcher.find()) {
            if (matcher.group(1).toUpperCase().contains("T")) {
                disk.setTitle(matcher.group(2));
            }
            if (matcher.group(1).toUpperCase().contains("D")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                try {
                    disk.setDate(format.parse(matcher.group(2)));
                } catch (ParseException e) {
                    throw new ServiceException("Wrong date format");
                }
            }

            if (matcher.group(1).toUpperCase().contains("M")) {
                disk.setMessage(matcher.group(2));
            }
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Disk> find(String request) throws ServiceException {
        Pattern pattern = Pattern.compile("(\\-p)?\\s?(\\w+)\\s+(.+)");
        Matcher matcher = pattern.matcher(request);

        if (!matcher.find()) {
            throw new ServiceException("Illegal arguments");
        }
        if (matcher.group(1) != null) {
            return DAOFactory.getInstance().getDiskDAO().find(matcher.group(2), matcher.group(3), true);
        } else {
            return DAOFactory.getInstance().getDiskDAO().find(matcher.group(2), matcher.group(3), false);
        }
    }

    @Override
    public void save() throws ServiceException {
        for (Disk disk :
                disks) {
            try {
                DAOFactory.getInstance().getDiskDAO().create(disk);
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage());
            }
            savedDisks.add(disk);
        }

        try {
            DAOFactory.getInstance().getKeysDAO().update(BeanFactory.getInstance().getKeys());
            DAOFactory.getInstance().getBooksDAO().update(BeanFactory.getInstance().getBooks());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }

    }

    public static DisksCatalog getInstance() {
        return instance;
    }
}
