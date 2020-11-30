package com.arjun.projects.database;

import com.arjun.projects.entity.WishlistItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class WishlistDatabase {

    @PersistenceContext
    EntityManager entityManager;

    public List<WishlistItem> findUserWishlist(String user) {

        return entityManager.createQuery(
                "select w from WishlistItem w where w.user = :user", WishlistItem.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<WishlistItem> priceMatch(double price) {

        return entityManager.createQuery(
                "select w from WishlistItem w where w.price <= :price", WishlistItem.class)
                .setParameter("price", price)
                .getResultList();
    }


    public WishlistItem updateItem(WishlistItem item) {
        return entityManager.merge(item);
    }

    public WishlistItem insertItem(WishlistItem item) {
        return entityManager.merge(item);
    }
}
