package com.tao.tmall_springboot.service;

import com.tao.tmall_springboot.dao.CategoryDAO;
import com.tao.tmall_springboot.dao.PropertyDAO;
import com.tao.tmall_springboot.pojo.Category;
import com.tao.tmall_springboot.pojo.Property;
import com.tao.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    PropertyDAO propertyDAO;

    @Autowired
    CategoryDAO categoryDAO;

    public void add(Property bean) {
        propertyDAO.save(bean);
    }

    public void delete(int id) {
        propertyDAO.delete(id);
    }

    public Property get(int id) {
        Property one = propertyDAO.findOne(id);
        return one;
    }

    public void update(Property bean) {
        propertyDAO.save(bean);
    }

    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryDAO.getOne(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Property> pageFromJpa = propertyDAO.findByCategory(category, pageable);

        return new Page4Navigator<>(pageFromJpa, navigatePages);
    }

    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}
